package you_tube.comment.commentLike.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.comment.commentLike.dto.CommentLikeInfoDTO;
import you_tube.comment.commentLike.entity.CommentLikeEntity;
import you_tube.comment.commentLike.enums.CommentLikeType;
import you_tube.comment.commentLike.repository.CommentLikeRepository;
import you_tube.exceptionhandler.AppBadException;
import you_tube.profile.entity.ProfileEntity;
import you_tube.profile.enums.ProfileRole;
import you_tube.profile.repository.ProfileRepository;
import you_tube.security.CustomUserDetails;
import you_tube.utils.SpringSecurityUtil;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public String createLike(Integer commentId) {
        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();
        Optional<ProfileEntity> byId = profileRepository.findById(currentUser.getId());
        if (byId.isEmpty()) {
            throw new AppBadException("video not exist");
        }
        CommentLikeEntity byVideoIdAndAndProfileId = commentLikeRepository.findByCommentIdAndProfileIdAndVisibleTrue(commentId, currentUser.getId());
        if (!(byVideoIdAndAndProfileId == null)){
            byVideoIdAndAndProfileId.setType(CommentLikeType.REMOVED);
            commentLikeRepository.save(byVideoIdAndAndProfileId);
            return "STATUS REMOVED";
        }
        CommentLikeEntity commentLikeEntity = new CommentLikeEntity();
        commentLikeEntity.setProfileId(currentUser.getId());
        commentLikeEntity.setCommentId(commentId);
        commentLikeEntity.setType(CommentLikeType.LIKE);
        commentLikeEntity.setVisible(Boolean.TRUE);
        commentLikeEntity.setCreatedDate(LocalDateTime.now());
        commentLikeRepository.save(commentLikeEntity);
        return "STATUS LIKE";
    }
    public String dislike(Integer commentId) {
        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();
        Optional<ProfileEntity> byId = profileRepository.findById(currentUser.getId());
        if (byId.isEmpty()) {
            throw new AppBadException("video not exist");
        }
        CommentLikeEntity byVideoIdAndAndProfileId = commentLikeRepository.findByCommentIdAndProfileIdAndVisibleTrue(commentId, currentUser.getId());
        if (!(byVideoIdAndAndProfileId == null)){
            byVideoIdAndAndProfileId.setType(CommentLikeType.REMOVED);
            commentLikeRepository.save(byVideoIdAndAndProfileId);
            return "STATUS REMOVED";
        }
        CommentLikeEntity commentLikeEntity = new CommentLikeEntity();
        commentLikeEntity.setProfileId(currentUser.getId());
        commentLikeEntity.setCommentId(commentId);
        commentLikeEntity.setType(CommentLikeType.LIKE);
        commentLikeEntity.setVisible(Boolean.TRUE);
        commentLikeEntity.setCreatedDate(LocalDateTime.now());
        commentLikeRepository.save(commentLikeEntity);
        return "STATUS DISLIKE";
    }

    public List<CommentLikeEntity> getAllCommentLikeInfo() {
        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();
        Optional<ProfileEntity> byId = profileRepository.findById(currentUser.getId());
        if (byId.isEmpty()) {
            throw new AppBadException("video not exist");
        } else return commentLikeRepository.getAllByCommentId(byId.get().getId());
    }

    public List<CommentLikeEntity> getAllCommentLikeInfoAdmin(Integer profileId) {
        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();
        Optional<ProfileEntity> byId = profileRepository.findById(currentUser.getId());
        if (byId.isEmpty() || !byId.get().getRole().equals(ProfileRole.ROLE_ADMIN)) {
            throw new AppBadException("video not exist");
        } else return commentLikeRepository.getAllByCommentId(profileId);
    }

}
