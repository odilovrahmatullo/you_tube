package you_tube.video;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import you_tube.attach.service.AttachService;
import you_tube.channel.ChannelService;
import you_tube.config.CustomUserDetails;
import you_tube.exceptionhandler.AppBadRequest;
import you_tube.utils.SpringSecurityUtil;
import you_tube.videowatched.service.VideoWatchedService;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private AttachService attachService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private VideoWatchedService videoWatchedService;

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

    @Transactional
    @Modifying
    public String update(String id, VideoDTO dto) {
        StringBuilder updateBuilder = new StringBuilder("Update VideoEntity as v SET ");
        StringBuilder conditions = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        boolean comma = true;
        if (dto.getTitle() != null) {
            conditions.append("v.title = :title ");
            params.put("title", dto.getTitle());
            comma = false;
        }
        if (dto.getDescription() != null) {
            if (!comma) {
                conditions.append(", ");
            }
            conditions.append("v.description = :description ");
            params.put("description", dto.getDescription());
            comma = false;
        }

        if (dto.getAttachId() != null) {
            if (!comma) {
                conditions.append(", ");
            }
            conditions.append("v.attachId = :attachId ");
            params.put("attachId", dto.getAttachId());
            comma = false;
        }
        if (dto.getVideoType() != null) {
            if (!comma) {
                conditions.append(", ");
            }
            conditions.append("v.videoType = :videoType ");
            params.put("videoType", dto.getVideoType());
            comma = false;
        }

        if (dto.getPreviewAttachId() != null) {
            if (!comma) {
                conditions.append(", ");
            }
            conditions.append("v.previewAttachId = :previewAttachId ");
            params.put("previewAttachId", dto.getPreviewAttachId());
            comma = false;
        }
        if (conditions.length() == 0) {
            return "UPDATED 0 ";
        }

        updateBuilder.append(conditions.toString());
        updateBuilder.append(" WHERE v.visible = true AND v.id = :id");
        Query updateQuery = entityManager.createQuery(updateBuilder.toString());

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            updateQuery.setParameter(entry.getKey(), entry.getValue());
        }
        updateQuery.setParameter("id", id);

        int updatedCount = updateQuery.executeUpdate();
        return "UPDATED " + updatedCount;

    }

    public String changeStatus(String videoId, VideoStatus status) {
        return "UPDATED "+videoRepository.changeStatus(videoId,status);
    }

    public void viewCount(String videoId) {
        /*VideoWatched added*/
        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();
        videoWatchedService.createVideoWatched(currentUser.getId(),videoId);
        /*View Count*/
        videoRepository.viewCount(videoId);
    }

    public Page<VideoShortInfoDTO> getByCategoryId(Integer categoryId, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<VideoEntity> pageList = videoRepository.getVideos(categoryId,pageable);
        List<VideoShortInfoDTO> infoDTOList =  pageList.stream().map(item -> mapperToInfo(item)).toList();
        return new PageImpl<>(infoDTOList,pageable,pageList.getTotalPages());
    }

    public VideoShortInfoDTO mapperToInfo(VideoEntity entity){
        VideoShortInfoDTO dto = new VideoShortInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setViewCount(entity.getViewCount());
        dto.setPreviewAttach(attachService.getDTO(entity.getPreviewAttachId()));
        dto.setChannel(channelService.getInfo(entity.getChannelId()));
        return dto;
    }
}
