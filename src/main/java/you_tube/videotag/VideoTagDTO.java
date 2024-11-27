package you_tube.videotag;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import you_tube.video.VideoDTO;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoTagDTO {

    private String videoId;
    private List<Integer> tagIds;
}
