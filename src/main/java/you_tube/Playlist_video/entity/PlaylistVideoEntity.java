package you_tube.Playlist_video.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import you_tube.PlayList.entity.PlayListEntity;
import you_tube.attach.entity.AttachEntity;

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
    private AttachEntity video;

    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "visible")
    private Boolean visible;
    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
