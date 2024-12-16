package you_tube.subscription;

import lombok.Data;

@Data
public class SubscriptionDTO {
    private String channelId;
    private NotificationType type;

}
