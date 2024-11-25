package you_tube.video;

import jakarta.persistence.*;
import you_tube.profile.entity.ProfileEntity;

import java.time.LocalDateTime;

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
