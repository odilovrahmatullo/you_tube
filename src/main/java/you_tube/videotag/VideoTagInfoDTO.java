package you_tube.videotag;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import you_tube.tag.dto.TagShortInfo;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VideoTagInfoDTO {
    private String videoTagId;
    private String videoId;
    private TagShortInfo tag;
}
