package you_tube.Playlist_video.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PlaylistVideoDTO {

    private Integer playlistId;
    private List<String> videoId;
    private Integer orderNumber;
    private Boolean visible;
}
