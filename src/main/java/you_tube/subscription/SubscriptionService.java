package you_tube.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.utils.SpringSecurityUtil;

import java.time.LocalDateTime;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    public SubscriptionDTO create(SubscriptionDTO dto) {
        SubscriptionEntity entity = new SubscriptionEntity();
        entity.setProfileId(SpringSecurityUtil.getCurrentUserId());
        entity.setChannelId(dto.getChannelId());
        entity.setType(dto.getType());
        entity.setStatus(SubscriptionStatus.ACTIVE);
        entity.setCreatedDate(LocalDateTime.now());
        subscriptionRepository.save(entity);
        return dto;
    }

    public ChangeSubscriptionStatusDTO changeStatus(ChangeSubscriptionStatusDTO dto) {
        subscriptionRepository.changeStatus(dto.getChannelId(),dto.getStatus(),SpringSecurityUtil.getCurrentUserId(),LocalDateTime.now());
        return dto;
    }

    public SubscriptionDTO changeNotificationType(SubscriptionDTO dto) {
        subscriptionRepository.changeNotificationType(dto.getChannelId(),dto.getType(),SpringSecurityUtil.getCurrentUserId());
        return dto;
    }
}
