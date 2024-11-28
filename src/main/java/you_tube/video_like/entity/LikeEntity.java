package you_tube.video_like.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import you_tube.profile.entity.ProfileEntity;
import you_tube.video.VideoEntity;
import you_tube.video_like.enums.LikeType;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "video_like")
public class LikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;


    @Column(name = "video_id")
    private String videoId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private VideoEntity video;

    @Column(name = "create_date")
    private LocalDateTime createdDate;
    @Column(name = "type")
    private LikeType type;
}
