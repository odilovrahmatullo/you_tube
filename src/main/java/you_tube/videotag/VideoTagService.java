package you_tube.videotag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.tag.service.TagService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VideoTagService {
    @Autowired
    private VideoTagRepository videoTagRepository;
    @Autowired
    private TagService tagService;

    public VideoTagDTO create(VideoTagDTO dto) {
        for (Integer i : dto.getTagIds()) {
            VideoTagEntity entity = new VideoTagEntity();
            entity.setVideoId(dto.getVideoId());
            entity.setTagId(i);
            entity.setCreatedDate(LocalDateTime.now());
            videoTagRepository.save(entity);
        }
        return dto;
    }

    public String delete(Integer tagId, String videoId) {
        return "DELETED "+videoTagRepository.deleteVideoTag(tagId,videoId);
    }

    public List<VideoTagInfoDTO> get(String videoId) {
        List<VideoTagEntity> entityList = videoTagRepository.getById(videoId);
        return entityList.stream().map(item -> mapper(item)).toList();
    }

    public VideoTagInfoDTO mapper(VideoTagEntity entity){
        VideoTagInfoDTO infoDTO = new VideoTagInfoDTO();
        infoDTO.setVideoTagId(entity.getId());
        infoDTO.setVideoId(entity.getVideoId());
        infoDTO.setTag(tagService.getDTO(entity.getTagId()));
        return infoDTO;
    }
}
