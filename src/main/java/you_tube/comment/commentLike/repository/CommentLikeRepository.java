package you_tube.comment.commentLike.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import you_tube.comment.commentLike.entity.CommentLikeEntity;

import java.util.List;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity,Integer> {

    CommentLikeEntity findByCommentIdAndProfileIdAndVisibleTrue(Integer commentId, Integer commentLike);

    @Query("FROM CommentLikeEntity AS c WHERE c.profileId = ?1 AND c.visible=TRUE")
    List<CommentLikeEntity> getAllByCommentId(Integer profileId);
}
