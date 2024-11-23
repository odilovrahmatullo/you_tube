package you_tube.profile.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    @NotBlank(message = "Enter your name")
    private String name;
    @NotBlank(message = "Enter your surname")
    private String surname;
    @NotBlank(message = "Enter your username")
    private String email;
    @NotBlank(message = "Enter your password")
    private String password;
}
