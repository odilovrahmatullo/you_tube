package you_tube.video;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import you_tube.attach.dtos.AttachSimpleDTO;
import you_tube.channel.ChannelDTO;
import you_tube.channel.ChannelShortInfoDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoShortInfoDTO {
    private String id;
    private String title;
    private AttachSimpleDTO previewAttach;
    private ChannelShortInfoDTO channel;
    private long viewCount;
    private LocalDateTime publishedDate;
}
