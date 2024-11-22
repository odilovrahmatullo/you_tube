package you_tube.Profile.dto;

import lombok.Getter;
import lombok.Setter;
import you_tube.Profile.enums.ProfileRole;

@Getter
@Setter
public class CreateProfile {
    private ProfileRole role;
    private String email;
}
