package bird.sound.downloader.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Recording {

    private String id;
    private String gen;
    private String sp;
    private String ssp;
    private String en;
    private String rec;
    private String cnt;
    private String loc;
    private String lat;
    private String lng;
    private String alt;
    private String type;
    private String url;
    private String file;
    @JsonProperty("file-name")
    private String fileName;
    private Sono sono;
    private String lic;
    private String q;
    private String length;
    private String time;
    private String date;
    private String uploaded;
    private List<String> also;
    private String rmk;
    @JsonProperty("bird-seen")
    private String birdSeen;
    @JsonProperty("playback-used")
    private String playbackUsed;
}
