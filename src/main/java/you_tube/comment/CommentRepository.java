package you_tube.comment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<CommentEntity,Long> {
    @Query("FROM CommentEntity as c where c.id = ?1 and c.profileId = ?2 and c.videoId = ?3 and c.visible = true ")
    CommentEntity isBelong(Integer commentId, Integer userId,String videoId);
}
