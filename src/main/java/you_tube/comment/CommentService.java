package you_tube.comment;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
}
