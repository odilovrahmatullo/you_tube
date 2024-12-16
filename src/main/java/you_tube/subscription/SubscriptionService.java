package you_tube.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.attach.service.AttachService;
import you_tube.channel.dto.ChannelShortInfoDTO;
import you_tube.utils.SpringSecurityUtil;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;
    @Autowired
    private AttachService attachService;
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

    public List<SubscriptionInfo> getUserSubscriptionList() {
        List<SubscriptionEntity> userSubscriptionList = subscriptionRepository
                .getUserSubscriptionList(SpringSecurityUtil.getCurrentUserId(),SubscriptionStatus.ACTIVE);
        return userSubscriptionList.stream().map(item -> (mapperToInfo(item))).toList();
    }

    public SubscriptionInfo mapperToInfo(SubscriptionEntity entity){
        SubscriptionInfo info = new SubscriptionInfo();
        info.setSubscribeId(entity.getId());
        info.setNotificationType(entity.getType());
        ChannelShortInfoDTO channelShortInfoDTO = new ChannelShortInfoDTO();
        channelShortInfoDTO.setId(entity.getChannelId());
        channelShortInfoDTO.setName(entity.getChannel().getName());
        channelShortInfoDTO.setAttach(attachService.getDTO(entity.getChannelId()));
        info.setChannelShortInfoDTO(channelShortInfoDTO);

        return info;
    }
}
