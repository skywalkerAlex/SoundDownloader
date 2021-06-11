package bird.sound.downloader.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Recording {

    public String id;
    public String gen;
    public String sp;
    public String ssp;
    public String en;
    public String rec;
    public String cnt;
    public String loc;
    public String lat;
    public String lng;
    public String alt;
    public String type;
    public String url;
    public String file;
    @JsonProperty("file-name")
    public String fileName;
    public Sono sono;
    public String lic;
    public String q;
    public String length;
    public String time;
    public String date;
    public String uploaded;
    public List<String> also;
    public String rmk;
    @JsonProperty("bird-seen")
    public String birdSeen;
    @JsonProperty("playback-used")
    public String playbackUsed;
}
