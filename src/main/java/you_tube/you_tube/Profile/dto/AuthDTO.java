package you_tube.you_tube.Profile.dto;

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
    @NotBlank(message = "Username  not blank")
    private String username;
    @NotBlank(message = "Password not blank")
    private String password;
}
