package you_tube.category.repository;

import you_tube.category.entity.CategoryEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

    @Query("from TagEntity where id=?1 and visible = true")
    CategoryEntity findByIdAndVisibleTrue(int id);


}
