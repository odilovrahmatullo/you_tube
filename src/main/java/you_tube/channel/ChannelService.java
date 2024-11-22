package you_tube.channel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.profile.service.ProfileService;

import java.time.LocalDateTime;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ProfileService profileService;

    public ChannelDTO create(ChannelDTO dto, String email) {
        ChannelEntity entity = new ChannelEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setBannerId(dto.getBannerId());
        entity.setPhotoId(dto.getPhotoId());
        entity.setProfileId(profileService.getByEmail(email).getId());
        entity.setStatus(ChannelStatus.ACTIVE);
        entity.setCreatedDate(LocalDateTime.now());
        channelRepository.save(entity);
        return toDTO(entity);
    }

    private ChannelDTO toDTO(ChannelEntity entity) {
        ChannelDTO dto = new ChannelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setProfileId(entity.getProfileId());
        dto.setPhotoId(entity.getPhotoId());
        dto.setBannerId(entity.getBannerId());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }


    public Boolean updatePhoto(String id, String photoId) {
        return channelRepository.updatePhoto(id,photoId)==1;
    }

    public Boolean updateBanner(String id, String photoId) {
        return channelRepository.updateBanner(id,photoId)==1;
    }

    public Boolean updateInfo(String id, UpdateChannelDTO dto) {
        return channelRepository.updateInfo(id,dto.getName(),dto.getDescription())==1;
    }

}
