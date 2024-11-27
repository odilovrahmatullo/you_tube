package you_tube.playlistvideo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import you_tube.playlist.entity.PlayListEntity;
import you_tube.video.VideoEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "playlist_video")
public class PlaylistVideoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "playlist_id")
    private Integer playlistId;
    @ManyToOne
    @JoinColumn(name = "playlist_id", insertable = false, updatable = false)
    private PlayListEntity playlist;

    @Column(name = "video_id")
    private String videoId;
    @ManyToOne
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private VideoEntity video;

    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "visible")
    private Boolean visible;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
