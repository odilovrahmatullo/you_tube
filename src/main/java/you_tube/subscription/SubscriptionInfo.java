package you_tube.subscription;

import lombok.Getter;
import lombok.Setter;
import you_tube.channel.dto.ChannelShortInfoDTO;
@Getter
@Setter
public class SubscriptionInfo {
    private Integer subscribeId;
    private ChannelShortInfoDTO channelShortInfoDTO;
    private NotificationType notificationType;
}
