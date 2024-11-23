package you_tube.Playlist_video.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "playlist_video")
public class PlaylistVideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "playlist_id")
    private Integer playlistId;

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "order_number")
    private Integer orderNumber;

    @Column(name = "visible")
    private Boolean visible;

}
