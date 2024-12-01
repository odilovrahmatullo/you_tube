package you_tube.comment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import you_tube.profile.service.ProfileService;
import you_tube.security.CustomUserDetails;
import you_tube.utils.SpringSecurityUtil;
import you_tube.video.service.VideoService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private VideoService videoService;

    @Transactional
    public CommentDTO create(CommentDTO dto) {
        CustomUserDetails user = SpringSecurityUtil.getCurrentUser();
        CommentEntity entity = new CommentEntity();
        entity.setContent(dto.getContent());
        entity.setVideoId(dto.getVideoId());
        entity.setProfileId(user.getId());
        entity.setVisible(Boolean.TRUE);
        if(dto.getReplyId()!=null){
            entity.setReplyId(dto.getReplyId());
        }
        entity.setCreatedDate(LocalDateTime.now());
        commentRepository.save(entity);
        dto.setId(entity.getId());
        dto.setProfileId(user.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;

    }

    public String update(Integer commentId, CommentDTO dto) {
        CustomUserDetails user = SpringSecurityUtil.getCurrentUser();
        CommentEntity commentEntity = isOwner(commentId,user);
        commentEntity.setContent(dto.getContent());
        commentEntity.setUpdateDate(LocalDateTime.now());
        commentRepository.save(commentEntity);
        return "UPDATED 1";
    }

    private CommentEntity isOwner(Integer commentId, CustomUserDetails user) {
        CommentEntity comment = commentRepository.isBelong(commentId,user.getId());
        if(comment!=null){
            return comment;
        }
        throw new AccessDeniedException("You don't have access to update this comment ");
    }

    public String delete(Integer commentId) {
        CommentEntity comment = isOwner(commentId,SpringSecurityUtil.getCurrentUser());
        comment.setVisible(Boolean.FALSE);
        commentRepository.save(comment);
        return "DELETED 1";
    }


    public Page<CommentDTO> getAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<CommentEntity> pageList = commentRepository.getAllComments(pageable);
        List<CommentDTO> dtoList = pageList.stream().map(item -> toDTO(item)).toList();
        return new PageImpl<>(dtoList,pageable,pageList.getTotalPages());
    }

    public CommentDTO toDTO(CommentEntity entity){
        CommentDTO dto = new CommentDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setProfile(profileService.toShortDTO(entity.getProfile()));
        dto.setVideoId(entity.getVideoId());
        if(entity.getReplyId()!=null) {
            dto.setReplyId(entity.getReplyId());
            dto.setUpdateDate(entity.getUpdateDate());
        }
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }


    public List<CommentInfoDTO> getByProfileId(Integer id) {
        List<CommentEntity> profileCommentList = commentRepository.getByProfileId(id);
        return profileCommentList.stream().map(item -> mapperTo(item)).toList();
    }

    private CommentInfoDTO mapperTo(CommentEntity entity){
        CommentInfoDTO dto = new CommentInfoDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVideo(videoService.getDTO(entity.getVideo()));
        return dto;
    }

    public CommentInfoDTO mapperToInfo(CommentEntity entity){
        CommentInfoDTO dto = new CommentInfoDTO();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setProfile(profileService.toShortDTO(entity.getProfile()));
        return dto;
    }
}
