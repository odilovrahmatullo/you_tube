package you_tube.video;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<VideoEntity,String> {
    @Query("select count(v) > 0 from VideoEntity v where v.id = ?1 and v.categoryId = ?2 and v.visible = true")
    Boolean existsByVideoIdAndCategoryId(String id, Integer categoryId);

    @Transactional
    @Modifying
    @Query("UPDATE VideoEntity set videoStatus = ?2 where id = ?1")
    int changeStatus(String videoId, VideoStatus status);

    @Transactional
    @Modifying
    @Query("UPDATE VideoEntity set viewCount = viewCount + 1 where id = ?1")
    void viewCount(String videoId);
}
