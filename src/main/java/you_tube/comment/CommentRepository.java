package you_tube.comment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<CommentEntity,Integer>,
                                           PagingAndSortingRepository<CommentEntity,Integer> {
    @Query("FROM CommentEntity as c where c.id = ?1 and c.profileId = ?2 and c.visible = true ")
    CommentEntity isBelong(Integer commentId, Integer userId);

    @Query("FROM CommentEntity where visible = true order by createdDate asc ")
    Page<CommentEntity> getAllComments(Pageable pageable);

    @Query("FROM CommentEntity where profileId = ?1 and visible = true ")
    List<CommentEntity> getByProfileId(Integer id);

}
