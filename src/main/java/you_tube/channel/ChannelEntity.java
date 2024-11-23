package you_tube.channel;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import you_tube.attach.entity.AttachEntity;
import you_tube.profile.entity.ProfileEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "channel")
public class ChannelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String description;
    @Enumerated(EnumType.STRING)
    private ChannelStatus status;

    @Column(name = "photo_id")
    private String photoId;
    @OneToOne
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity photo;

    @Column(name = "banner_id")
    private String bannerId;
    @OneToOne
    @JoinColumn(name = "banner_id", insertable = false, updatable = false)
    private AttachEntity banner;

    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
