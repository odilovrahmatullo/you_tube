package you_tube.profile.dto;

import lombok.Getter;
import lombok.Setter;
import you_tube.attach.dtos.PhotoDTO;

@Getter
@Setter
public class GetProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private PhotoDTO photo;
}
