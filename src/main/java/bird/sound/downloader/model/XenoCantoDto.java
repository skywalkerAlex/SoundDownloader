package bird.sound.downloader.model;

import java.util.List;

import lombok.Data;

@Data
public class XenoCantoDto {
    public String numRecordings;
    public String numSpecies;
    public int page;
    public int numPages;
    public List<Recording> recordings;
}
