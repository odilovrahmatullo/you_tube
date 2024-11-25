package you_tube.PlayList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import you_tube.PlayList.dto.PlayListInfoDTO;
import you_tube.attach.dtos.PhotoDTO;
import you_tube.attach.service.AttachService;
import you_tube.attach.service.ResourceBundleService;
import you_tube.exceptionHandler.AppBadException;
import you_tube.PlayList.dto.CreatePlaylistDTO;
import you_tube.PlayList.dto.UpdateDTO;
import you_tube.PlayList.entity.PlayListEntity;
import you_tube.PlayList.enums.PlaylistStatus;
import you_tube.PlayList.repository.PlaylistRepository;
import you_tube.profile.dto.GetProfileDTO;
import you_tube.profile.enums.ProfileStatus;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;
    @Autowired
    private AttachService attachService;
    @Autowired
    private ResourceBundleService resourceBundleService;


    public CreatePlaylistDTO create(CreatePlaylistDTO dto) {
        PlayListEntity playlistEntity = new PlayListEntity();
        PlayListEntity exists = playlistRepository.exists(dto.getChannel_id(), dto.getName());
        if (exists != null) {
            throw new AppBadException("Does such a playlist exist?");
        }
        playlistEntity.setName(dto.getName());
        playlistEntity.setChannelId(dto.getChannel_id());
        playlistEntity.setDescription(dto.getDescription());
        playlistEntity.setStatus(dto.getStatus());
        playlistEntity.setOrderNum(dto.getOrderNum());
        playlistEntity.setVisible(Boolean.TRUE);
        playlistEntity.setCreatedDate(LocalDate.now());
        playlistRepository.save(playlistEntity);
        dto.setId(playlistEntity.getId());
        return dto;
    }

    public CreatePlaylistDTO update(Integer id, UpdateDTO dto) {
        Optional<PlayListEntity> byId = playlistRepository.findById(id);
        if(byId.isPresent()){
            PlayListEntity playlistEntity = byId.get();
            playlistEntity.setName(dto.getName());
            playlistEntity.setDescription(dto.getDescription());
            playlistEntity.setOrderNum(dto.getOrderNum());
            playlistRepository.save(playlistEntity);

            CreatePlaylistDTO dto1 = new CreatePlaylistDTO();
            dto1.setId(id);
            dto1.setName(dto.getName());
            dto1.setDescription(dto.getDescription());
            dto1.setOrderNum(dto.getOrderNum());
            dto1.setChannel_id(playlistEntity.getChannelId());
            return dto1;
        }
        throw new AppBadException(resourceBundleService.getMessage("playlist.not.found","uz"));
    }
    public CreatePlaylistDTO updateStatus(Integer id, PlaylistStatus status) {
        int result = playlistRepository.updateStatus(id, status);
        if (result == 1) {
            Optional<PlayListEntity> byId = playlistRepository.findById(id);
            PlayListEntity playListEntity = byId.get();
            CreatePlaylistDTO dto1 = new CreatePlaylistDTO();
            dto1.setId(id);
            dto1.setName(playListEntity.getName());
            dto1.setDescription(playListEntity.getDescription());
            dto1.setOrderNum(playListEntity.getOrderNum());
            dto1.setChannel_id(playListEntity.getChannelId());
            dto1.setStatus(playListEntity.getStatus());
            return dto1;
        }
        throw new AppBadException("Does such a playlist exist?");
    }

    public Boolean deleted(Integer id) {
        int i = playlistRepository.deletedVisible(id);
        if (i == 1) {
            return true;
        }
        throw new AppBadException("Does such a playlist exist?");
    }

    public Page<PlayListInfoDTO> AllPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());

        Page<PlayListEntity> entityList = playlistRepository.getAll6(pageRequest);
        Long total = entityList.getTotalElements();
        List<PlayListInfoDTO> dtoList = new LinkedList<>();
        for (PlayListEntity entity : entityList) {
            PlayListInfoDTO playListInfoDTO = new PlayListInfoDTO();
            playListInfoDTO.setId(entity.getId());
            playListInfoDTO.setName(entity.getName());
            playListInfoDTO.setDescription(entity.getDescription());
            playListInfoDTO.setStatus(entity.getStatus());

            GetProfileDTO getProfileDTO =new GetProfileDTO();
            getProfileDTO.setId(entity.getChannel().getProfile().getId());
            getProfileDTO.setName(entity.getChannel().getProfile().getName());
            getProfileDTO.setSurname(entity.getChannel().getProfile().getSurname());
            PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setId(entity.getChannel().getProfile().getPhoto());
            photoDTO.setUrl(attachService.getUrl(entity.getChannel().getProfile().getPhoto()));
            getProfileDTO.setPhoto(photoDTO);

            playListInfoDTO.setProfile(getProfileDTO);
            playListInfoDTO.setStatus(entity.getStatus());
            playListInfoDTO.setChannel(entity.getChannel());
            playListInfoDTO.setOrder_num(entity.getOrderNum());
            dtoList.add(playListInfoDTO);
        }
        PageImpl page1 = new PageImpl<>(dtoList, pageRequest, total);

        return page1;
    }
}
