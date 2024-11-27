package you_tube.videotag;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VideoTagRepository extends CrudRepository<VideoTagEntity,String> {
    @Modifying
    @Transactional
    @Query("DELETE FROM VideoTagEntity where tagId = ?1 and videoId = ?2")
    Integer deleteVideoTag(Integer tagId, String videoId);

}
