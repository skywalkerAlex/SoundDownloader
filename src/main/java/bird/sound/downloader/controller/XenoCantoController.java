package bird.sound.downloader.controller;

import java.io.ByteArrayInputStream;
import java.io.SequenceInputStream;
import java.util.Enumeration;
import java.util.Vector;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bird.sound.downloader.model.EnumeratedCountries;
import bird.sound.downloader.model.http.RequestForm;
import bird.sound.downloader.service.XenoCantoDownloaderServiceImpl;

@RestController
@RequestMapping("/api/v1/sound-downloader")
public class XenoCantoController {

    private static final String filename = "XenoCantoBirdSoundDataSet.csv";

    @Autowired
    XenoCantoDownloaderServiceImpl downloaderService;

    @PostMapping("soundDetails")
    public ResponseEntity<InputStreamResource> retrieveSoundDetails(@Valid @RequestBody RequestForm requestForm) {

        ByteArrayInputStream input = downloaderService.soundRetriever(requestForm.getCountry(),
                requestForm.getGenericNameOfSpecies());
        if (input == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new InputStreamResource(input));
        }
        InputStreamResource file = new InputStreamResource(input);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv")).body(file);
    }

    @GetMapping("allCountriesDetails")
    public ResponseEntity<InputStreamResource> retrieveAllData() {
        ByteArrayInputStream input = downloaderService.csvHeaders();
        Vector<ByteArrayInputStream> vector = new Vector<>();
        SequenceInputStream allInputs = null;

        vector.add(input);
        Stream.of(EnumeratedCountries.values())
                .forEach(country -> vector.add(downloaderService.soundRetriever(country.toString(), null)));

        Enumeration<ByteArrayInputStream> enumeration = vector.elements();
        allInputs = new SequenceInputStream(enumeration);

        InputStreamResource file = new InputStreamResource(allInputs);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv")).body(file);
    }
}
