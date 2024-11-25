package you_tube.video;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VideoRepository extends CrudRepository<VideoEntity,String> {
    @Query("select count(v) > 0 from VideoEntity v where v.id = ?1 and v.categoryId = ?2 and v.visible = true")
    Boolean existsByVideoIdAndCategoryId(String id, Integer categoryId);
}
