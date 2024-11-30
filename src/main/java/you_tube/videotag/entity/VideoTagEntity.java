package you_tube.videotag.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import you_tube.tag.entity.TagEntity;
import you_tube.video.entity.VideoEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "video_tag")
public class VideoTagEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "video_id")
    private String videoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id",insertable = false,updatable = false)
    private VideoEntity video;

    @Column(name = "tag_id")
    private Integer tagId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id",insertable = false,updatable = false)
    private TagEntity tag;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
