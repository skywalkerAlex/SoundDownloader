package bird.sound.downloader.helper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;

import bird.sound.downloader.model.XenoCantoDto;
import bird.sound.downloader.model.http.StoreData;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CsvHelper {

    public static ByteArrayInputStream createDataForCSV(XenoCantoDto cantoDto) {
        List<StoreData> databind = new ArrayList<>();

        cantoDto.getRecordings().forEach(recording -> {
            StoreData data = new StoreData();
            data.setCountry(recording.getCnt());
            data.setGenericName(recording.getGen());
            data.setSpecificName(recording.getSp());
            // data.setLocationFound(recording.getLoc());
            data.setRecordId(recording.getId());
            data.setRecordLength(recording.getLength());
            String tempType = recording.getType().toLowerCase();
            // prioritizing the type 'song'
            if (tempType.contains("song")) {
                data.setTypeOfSong("song");
            } else if (tempType.contains("call")) {
                data.setTypeOfSong("call");
            } else if (tempType.contains("alarm")) {
                data.setTypeOfSong("alarm");
            } else {
                data.setTypeOfSong("other");
            }
            log.info("Adding the following StoreData object to a list => " + data);
            databind.add(data);
        });

        return convertDataToString(databind);
    }

    private static ByteArrayInputStream convertDataToString(List<StoreData> storeDatas) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

            for (StoreData storeData : storeDatas) {
                List<String> data = Arrays.asList(storeData.getRecordId(), storeData.getCountry(),
                        storeData.getGenericName(), storeData.getSpecificName(),
                        // storeData.getLocationFound(),
                        storeData.getTypeOfSong(), storeData.getRecordLength());
                log.info("Converting the StoreData object to a String => " + storeData);
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream csvHeadersToString() {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {

            List<String> headers = Arrays.asList("recordId", "country", "genericName", "specificName", "typeOfSong",
                    "recordLength");
            log.info("Headers to a String => " + headers);
            csvPrinter.printRecord(headers);

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import headers to CSV file: " + e.getMessage());
        }
    }

}
