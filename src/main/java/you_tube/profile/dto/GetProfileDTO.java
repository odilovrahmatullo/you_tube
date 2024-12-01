package you_tube.profile.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import you_tube.attach.dtos.AttachSimpleDTO;
import you_tube.attach.dtos.PhotoDTO;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private PhotoDTO photo;
    private AttachSimpleDTO attach;
}
