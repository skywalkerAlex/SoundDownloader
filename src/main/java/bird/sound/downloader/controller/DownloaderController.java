package bird.sound.downloader.controller;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class DownloaderController {

    private static RestTemplate restTemplate = new RestTemplate();

    public static void callTemp() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

        ResponseEntity<String> exchange = restTemplate.exchange(
                "https://www.xeno-canto.org/api/2/recordings?query=cnt:brazil", HttpMethod.GET, entity, String.class);

        System.out.println(exchange);
    }

}
