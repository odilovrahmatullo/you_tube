package you_tube.you_tube.Profile.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import you_tube.you_tube.Profile.enums.ProfileRole;

@Setter
@Getter
public class CreateProfile {
    private Integer id;
    @NotBlank(message = "Name not found")
    private String name;
    @NotBlank(message = "Surname not found")
    private String surname;
    @NotBlank(message = "Email not found")
    private String email;
    private ProfileRole role;
}
