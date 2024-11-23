package you_tube.profile.dto;

import lombok.Getter;
import lombok.Setter;
import you_tube.profile.enums.ProfileRole;

@Getter
@Setter
public class CreateProfile {
    private ProfileRole role;
    private String email;
}
