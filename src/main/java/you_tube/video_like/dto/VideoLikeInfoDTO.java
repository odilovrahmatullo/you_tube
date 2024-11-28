package you_tube.video_like.dto;

import lombok.Getter;
import lombok.Setter;
import you_tube.attach.dtos.PhotoDTO;

@Getter
@Setter
public class VideoLikeInfoDTO {
    private Integer id;
    private VideoShortDTO video;
    private PhotoDTO preview_attach;
}
