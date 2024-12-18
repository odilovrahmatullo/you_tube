package you_tube.profile.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileDTO {
    @NotBlank(message = "write your name")
    private String name;
    @NotBlank(message = "write your new surname")
    private String surname;
}
