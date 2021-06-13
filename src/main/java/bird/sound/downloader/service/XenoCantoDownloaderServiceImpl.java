package bird.sound.downloader.service;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import bird.sound.downloader.helper.CsvHelper;
import bird.sound.downloader.model.XenoCantoDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class XenoCantoDownloaderServiceImpl implements XenoCantoDownloaderService {

    private static String URL_PATH = "https://www.xeno-canto.org/api/2/recordings?query=";
    private static RestTemplate restTemplate = new RestTemplate();

    ObjectMapper mapper = new ObjectMapper();;

    public ByteArrayInputStream soundRetriever(String cnt, String gen) {
        log.info("Requested a list of details from country : " + cnt);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        var url = URL_PATH + "cnt:" + cnt;
        if (StringUtils.hasLength(gen)) {
            url += "+gen:" + gen;
        }
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        XenoCantoDto dto = new XenoCantoDto();
        if (exchange.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        try {
            dto = mapper.readValue(exchange.getBody(), XenoCantoDto.class);
        } catch (JsonProcessingException e) {
            log.error("JsonProcessingException Error => " + e);
        }
        log.info("Got number of Recordings : " + dto.getNumRecordings());

        return CsvHelper.createDataForCSV(dto);
    }

    @Override
    public ByteArrayInputStream csvHeaders() {
        return CsvHelper.csvHeadersToString();
    }
}
