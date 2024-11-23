package you_tube.Playlist_video.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaylistVideoDTO {
    private Integer playlistId;
    private String videoId;
    private Integer orderNumber;
    private Boolean visible;
}
