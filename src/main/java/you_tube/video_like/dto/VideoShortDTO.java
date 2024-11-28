package you_tube.video_like.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoShortDTO {
    private String id;
    private String name;
    private ChannelDTO channel;
}
