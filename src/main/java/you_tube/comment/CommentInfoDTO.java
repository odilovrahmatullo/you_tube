package you_tube.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import you_tube.profile.dto.GetProfileDTO;
import you_tube.video.dto.VideoSimpleDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentInfoDTO {

    private Integer id;
    private String content;
    private LocalDateTime createdDate;
    private GetProfileDTO profile;
    private VideoSimpleDTO video;

}
