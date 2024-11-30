package you_tube.video.dto;

import lombok.Getter;
import lombok.Setter;
import you_tube.attach.dtos.AttachSimpleDTO;
import you_tube.category.dto.SimpleCategoryDTO;
import you_tube.channel.dto.ChannelShortInfoDTO;
import you_tube.tag.dto.TagShortInfo;
import you_tube.video_like.dto.LikeInfoDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class VideoFullInfoDTO {
    private String id;
    private String title;
    private String description;
    private AttachSimpleDTO previewAttach;
    private AttachSimpleDTO attach;
    private SimpleCategoryDTO category;
    private List<TagShortInfo> tag;
    private LikeInfoDTO like;
    private ChannelShortInfoDTO channel;
    private long viewCount;
    private long sharedCount;
    private LocalDateTime publishedDate;
}
