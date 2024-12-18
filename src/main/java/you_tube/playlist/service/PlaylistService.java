package you_tube.playlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import you_tube.playlist.dto.PlayListInfoDTO;
import you_tube.attach.dtos.PhotoDTO;
import you_tube.attach.service.AttachService;
import you_tube.attach.service.ResourceBundleService;
import you_tube.exceptionhandler.AppBadException;
import you_tube.playlist.dto.CreatePlaylistDTO;
import you_tube.playlist.dto.PlayListShortInfoDTO;
import you_tube.playlist.dto.UpdateDTO;
import you_tube.playlist.entity.PlayListEntity;
import you_tube.playlist.enums.PlaylistStatus;
import you_tube.playlist.repository.PlaylistRepository;
import you_tube.profile.dto.GetProfileDTO;

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

    public Page<PlayListInfoDTO> allPage(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("createdDate").descending());

        Page<PlayListEntity> entityList = playlistRepository.getAll6(pageRequest);

        Page<PlayListInfoDTO> dtoPage = entityList.map(entity -> {
            PlayListInfoDTO dto = new PlayListInfoDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setDescription(entity.getDescription());
            dto.setStatus(entity.getStatus());

            GetProfileDTO profileDTO = new GetProfileDTO();
            profileDTO.setId(entity.getChannel().getProfile().getId());
            profileDTO.setName(entity.getChannel().getProfile().getName());
            profileDTO.setSurname(entity.getChannel().getProfile().getSurname());

            PhotoDTO photoDTO = new PhotoDTO();
            photoDTO.setId(entity.getChannel().getProfile().getPhoto());
            photoDTO.setUrl(attachService.getUrl(entity.getChannel().getProfile().getPhoto()));
            profileDTO.setPhoto(photoDTO);

            dto.setProfile(profileDTO);
            dto.setChannel(entity.getChannel());
            dto.setOrder_num(entity.getOrderNum());

            return dto;
        });

        return dtoPage;
    }



    public PlayListEntity getById(Integer id){
        return playlistRepository.findById(id).get();
    }
}
