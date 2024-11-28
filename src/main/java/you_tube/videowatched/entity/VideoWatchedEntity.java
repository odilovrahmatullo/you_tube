package you_tube.videowatched.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import you_tube.profile.entity.ProfileEntity;
import you_tube.video.VideoEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class VideoWatchedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "profile_id")
    private Integer profile_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "video_id")
    private String video_id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "video_id", insertable = false, updatable = false)
    private VideoEntity video;

    @Column(name = "create_date")
    private LocalDateTime createdDate;
}
