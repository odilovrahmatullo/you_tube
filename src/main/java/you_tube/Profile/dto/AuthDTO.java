package you_tube.Profile.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    @NotBlank(message = "email  not blank")
    private String email;
    @NotBlank(message = "Password not blank")
    private String password;
}
