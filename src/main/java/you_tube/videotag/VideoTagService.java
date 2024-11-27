package you_tube.videotag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VideoTagService {
    @Autowired
    private VideoTagRepository videoTagRepository;

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
}
