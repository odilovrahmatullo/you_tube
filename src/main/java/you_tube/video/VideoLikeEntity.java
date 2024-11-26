package you_tube.video;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import you_tube.profile.entity.ProfileEntity;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@Table(name = "video_like")
public class VideoLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "video_id")
    private String videoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private VideoEntity video;

    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Enumerated(EnumType.STRING)
    private LikeStatus status;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
