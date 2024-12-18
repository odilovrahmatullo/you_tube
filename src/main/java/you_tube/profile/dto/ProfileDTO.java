package you_tube.profile.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import you_tube.attach.dtos.AttachDTO;
import you_tube.attach.dtos.AttachSimpleDTO;
import you_tube.profile.enums.ProfileRole;
import you_tube.profile.enums.ProfileStatus;


@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {

    private Integer id;
    @NotBlank(message = "Name not found")
    private String name;
    @NotBlank(message = "Surname not found")
    private String surname;
    @NotBlank(message = "Email not found")
    private String email;
    @NotBlank(message = "Password not found")
    private String password;
    private String photo;
    private AttachSimpleDTO photoInDTO;
    private ProfileStatus status;
    private ProfileRole role;
    private String JwtToken;
    private String refreshToken;


}

