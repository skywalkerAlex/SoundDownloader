package bird.sound.downloader.model.http;

import lombok.Data;

@Data
public class StoreData {

    private String recordId;
    private String country;
    private String genericName;
    private String specificName;
    // private String locationFound;
    // song, sex call, alarm call, adult, male, duet, call,
    private String typeOfSong;
    private String recordLength;
}
