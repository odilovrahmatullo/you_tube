package you_tube.subscription;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import you_tube.channel.entity.ChannelEntity;
import you_tube.profile.entity.ProfileEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "subscription")
public class SubscriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profile;

    @Column(name = "channel_id")
    private String channelId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id",insertable = false,updatable = false)
    private ChannelEntity channel;

    @Enumerated(EnumType.STRING)
    @Column(name = "subscribtion_status")
    private SubscriptionStatus status;
    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type")
    private NotificationType type;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "unsubscribe_date")
    private LocalDateTime unSubscribeDate;
}
