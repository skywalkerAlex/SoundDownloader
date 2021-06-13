package bird.sound.downloader.service;

import java.io.ByteArrayInputStream;

public interface XenoCantoDownloaderService {

    public ByteArrayInputStream soundRetriever(String cnt, String gen);

    public ByteArrayInputStream csvHeaders();
}
