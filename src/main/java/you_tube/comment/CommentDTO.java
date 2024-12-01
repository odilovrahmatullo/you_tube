package you_tube.comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import you_tube.profile.dto.GetProfileDTO;
import you_tube.profile.dto.ProfileDTO;
import you_tube.profile.entity.ProfileEntity;
import you_tube.video.dto.VideoDTO;
import you_tube.video.entity.VideoEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {

    private Integer id;
    @NotNull(message = "content is required")
    private String content;
    private Integer profileId;
    private GetProfileDTO profile;
    @NotNull(message = "videoId is required")
    private String videoId;
    private VideoDTO video;
    private Integer replyId;
    private CommentDTO reply;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
}
