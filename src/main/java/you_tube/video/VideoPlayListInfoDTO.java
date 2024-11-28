package you_tube.video;

import lombok.Getter;
import lombok.Setter;
import you_tube.attach.dtos.AttachSimpleDTO;

import java.time.LocalDateTime;
@Getter
@Setter
public class VideoPlayListInfoDTO {
    private String id;
    private String title;
    private AttachSimpleDTO previewAttach;
    private long viewCount;
    private LocalDateTime publishedDate;
}
