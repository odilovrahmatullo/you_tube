package you_tube.tag.repository;

import you_tube.tag.dto.TagDTO;
import you_tube.tag.entity.TagEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends CrudRepository<TagEntity, Integer> {

    @Query("from TagEntity where id=?1 and visible = true")
    TagEntity findByIdAndVisibleTrue(int id);

}
