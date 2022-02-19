package bird.sound.downloader.model.http;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class RequestForm {

    @NotBlank
    @Pattern(regexp = "^$|^[\\w\\D]+$")
    private String country;

    @Pattern(regexp = "^$|^[\\w\\D]+$")
    private String genericNameOfSpecies;

}
