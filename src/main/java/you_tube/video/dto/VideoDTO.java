package you_tube.video.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import you_tube.video.enums.VideoStatus;
import you_tube.video.enums.VideoType;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoDTO {
    private String id;
    private String title;
    private String description;
    private Integer categoryId;
    private String attachId;
    private String previewAttachId;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private VideoStatus videoStatus;
    private VideoType videoType;
    private Long viewCount;
    private Long sharedCount;
    private String channelId;
    private Long likeCount;
    private Long dislikeCount;
}
