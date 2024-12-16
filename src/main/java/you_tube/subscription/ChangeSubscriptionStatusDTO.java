package you_tube.subscription;

import lombok.Data;

@Data
public class ChangeSubscriptionStatusDTO {
    private String channelId;
    private SubscriptionStatus status;
}
