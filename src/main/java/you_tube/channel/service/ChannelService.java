package you_tube.channel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import you_tube.attach.service.AttachService;
import you_tube.channel.dto.ChannelDTO;
import you_tube.channel.dto.ChannelShortInfoDTO;
import you_tube.channel.enums.ChannelStatus;
import you_tube.channel.dto.UpdateChannelDTO;
import you_tube.channel.entity.ChannelEntity;
import you_tube.channel.repository.ChannelRepository;
import you_tube.profile.service.ProfileService;
import you_tube.utils.SpringSecurityUtil;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChannelService {
    @Autowired
    private ChannelRepository channelRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AttachService attachService;

    public ChannelDTO create(ChannelDTO dto) {
        ChannelEntity entity = new ChannelEntity();
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setBannerId(dto.getBannerId());
        entity.setPhotoId(dto.getPhotoId());
        entity.setProfileId(SpringSecurityUtil.getCurrentUserId());
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
        return channelRepository.updatePhoto(id, photoId) == 1;
    }

    public Boolean updateBanner(String id, String photoId) {
        return channelRepository.updateBanner(id, photoId) == 1;
    }

    public Boolean updateInfo(String id, UpdateChannelDTO dto) {
        return channelRepository.updateInfo(id, dto.getName(), dto.getDescription()) == 1;
    }


    public Page<ChannelDTO> pagination(Integer page, Integer size) {
        Pageable pagination = PageRequest.of(page, size);
        Page<ChannelEntity> pageList = channelRepository.getPagination(ChannelStatus.ACTIVE, pagination);
        List<ChannelDTO> channelLIst = pageList.stream().map(item -> toDTO(item)).toList();
        return new PageImpl<>(channelLIst, pagination, pageList.getTotalPages());
    }

    public ChannelDTO getById(String id) {
        ChannelEntity entity = channelRepository.getByIdAndVisibleTrue(id, ChannelStatus.ACTIVE);
        return fullMapper(entity);

    }

    public ChannelDTO fullMapper(ChannelEntity entity) {
        ChannelDTO dto = new ChannelDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setBanner(attachService.getDTO(entity.getBannerId()));
        dto.setPhoto(attachService.getDTO(entity.getPhotoId()));
        dto.setProfile(profileService.getById(entity.getProfileId()));
        return dto;
    }


    public Boolean changeStatus(String id, ChannelStatus status) {
        return channelRepository.changeStatus(id, status) == 1;

    }

    public List<ChannelDTO> getUsersChannel() {
        List<ChannelEntity> usersChannelList = channelRepository.getChannels(SpringSecurityUtil.getCurrentUser().getUsername(), ChannelStatus.ACTIVE);
        return usersChannelList.stream().map(item -> toDTO(item)).toList();
    }
    public ChannelShortInfoDTO getInfo(String id){
        ChannelDTO dto = getById(id);
        ChannelShortInfoDTO shortInfoDTO = new ChannelShortInfoDTO();
        shortInfoDTO.setId(dto.getId());
        shortInfoDTO.setName(dto.getName());
        shortInfoDTO.setPhoto(dto.getPhoto().getUrl());
        return shortInfoDTO;
    }
}
