package you_tube.videotag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import you_tube.attach.service.ResourceBundleService;
import you_tube.exceptionhandler.ResourceNotFoundException;
import you_tube.tag.dto.TagShortInfo;
import you_tube.tag.entity.TagEntity;
import you_tube.tag.service.TagService;
import you_tube.video.VideoEntity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class VideoTagService {
    @Autowired
    private VideoTagRepository videoTagRepository;
    @Autowired
    private TagService tagService;
    @Autowired
    private ResourceBundleService resourceBundleService;


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

    public List<VideoEntity> getVideosByTagId(String tagId, String lang,Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<VideoEntity> videos = videoTagRepository.getByTagId(tagId,pageable);
        if(videos.isEmpty()){
            throw new ResourceNotFoundException(resourceBundleService.getMessage("videos.not.found",lang));
        }
        return videos.get().toList();
    }

    public List<TagShortInfo> tags(String videoId){
        List<TagEntity> tagEntities = videoTagRepository.getTagsByVideoId(videoId);
        if(tagEntities.isEmpty()){
            return Collections.emptyList();
        }
        return tagEntities.stream().map(item -> tagService.getDTO(item.getId())).toList();
    }
}
