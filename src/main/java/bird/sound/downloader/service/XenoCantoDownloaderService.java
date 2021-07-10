package bird.sound.downloader.service;

import java.io.ByteArrayInputStream;
import java.util.Vector;

public interface XenoCantoDownloaderService {

    public Vector<ByteArrayInputStream> soundRetriever(String cnt, String gen);

    public ByteArrayInputStream csvHeaders();
}
