package you_tube.PlayList.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.ExceptionHandler.AppBadException;
import you_tube.PlayList.dto.CreatePlaylistDTO;
import you_tube.PlayList.dto.UpdateDTO;
import you_tube.PlayList.entity.PlayListEntity;
import you_tube.PlayList.enums.PlaylistStatus;
import you_tube.PlayList.repository.PlaylistRepository;
import you_tube.Profile.enums.ProfileStatus;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    private PlaylistRepository playlistRepository;


    public CreatePlaylistDTO create(CreatePlaylistDTO dto) {
        PlayListEntity playlistEntity = new PlayListEntity();
        PlayListEntity exists = playlistRepository.exists(dto.getChannel_id(), dto.getName());
        if (exists != null) {
            throw new AppBadException("Does such a playlist exist?");
        }
        playlistEntity.setName(dto.getName());
        playlistEntity.setChannel_id(dto.getChannel_id());
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
            dto1.setChannel_id(playlistEntity.getChannel_id());
            return dto1;
        }
        throw new AppBadException("Does such a playlist exist?");
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
            dto1.setChannel_id(playListEntity.getChannel_id());
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
}
