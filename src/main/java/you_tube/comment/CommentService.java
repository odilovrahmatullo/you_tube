package you_tube.comment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import you_tube.profile.entity.ProfileEntity;
import you_tube.profile.service.ProfileService;
import you_tube.security.CustomUserDetails;
import you_tube.utils.SpringSecurityUtil;

import java.time.LocalDateTime;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ProfileService profileService;

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
        CommentEntity commentEntity = isOwner(commentId,user, dto.getVideoId());
        commentEntity.setContent(dto.getContent());
        commentEntity.setUpdateDate(LocalDateTime.now());
        commentRepository.save(commentEntity);
        return "UPDATED 1";
    }

    private CommentEntity isOwner(Integer commentId, CustomUserDetails user,String videoId) {
        CommentEntity comment = commentRepository.isBelong(commentId,user.getId(),videoId);
        if(comment!=null){
            return comment;
        }
        throw new AccessDeniedException("You don't have access to update this comment ");
    }
}
