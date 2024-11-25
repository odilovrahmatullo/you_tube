package you_tube.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.exceptionHandler.AppBadRequest;

import java.time.LocalDateTime;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public VideoDTO create(VideoDTO dto) {
        VideoEntity entity = new VideoEntity();
        Boolean exists = videoRepository.existsByVideoIdAndCategoryId(dto.getId(), dto.getCategoryId());
        if (Boolean.TRUE.equals(exists)) {
            throw new AppBadRequest("This video already exists in the selected category.");
        }
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setAttachId(dto.getAttachId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setChannelId(dto.getChannelId());
        entity.setPreviewAttachId(dto.getPreviewAttachId());
        entity.setVideoType(dto.getVideoType());
        entity.setVideoStatus(dto.getVideoStatus());
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());
        videoRepository.save(entity);
        return dto;
    }

}
