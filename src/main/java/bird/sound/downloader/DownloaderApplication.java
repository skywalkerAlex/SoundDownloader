package bird.sound.downloader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import bird.sound.downloader.controller.DownloaderController;

@SpringBootApplication
public class DownloaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(DownloaderApplication.class, args);

		DownloaderController.callTemp();

	}

}
