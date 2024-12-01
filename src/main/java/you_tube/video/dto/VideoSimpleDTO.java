package you_tube.video.dto;

import lombok.Getter;
import lombok.Setter;
import you_tube.attach.dtos.AttachSimpleDTO;

@Getter
@Setter
public class VideoSimpleDTO {
    private String id;
    private String name;
    private String title;
    private AttachSimpleDTO previewAttach;
}
