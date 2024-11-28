package you_tube.video;

import lombok.Getter;
import lombok.Setter;
import you_tube.playlist.dto.PlayListShortInfoDTO;
import you_tube.profile.dto.GetProfileDTO;

import java.util.List;

@Getter
@Setter
public class VideoMixDTO {
    private VideoShortInfoDTO videoShortInfoDTO;
    private GetProfileDTO profileShortDTO;
    private List<PlayListShortInfoDTO> playListShortInfoDTO;
}
