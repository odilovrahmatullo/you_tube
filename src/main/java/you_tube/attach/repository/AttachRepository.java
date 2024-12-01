package you_tube.attach.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import you_tube.attach.entity.AttachEntity;

import java.util.Optional;

public interface AttachRepository extends CrudRepository<AttachEntity, String>,
                                          PagingAndSortingRepository<AttachEntity,String> {

    @Query("from AttachEntity where id = ?1 and visible = true")
    Optional<AttachEntity> findByIdAndVisibleTrue(String id);

    @Modifying
    @Transactional
    @Query("Update AttachEntity Set visible = false where id = ?1 ")
    void deleteItem(String attachId);

    @Query("from AttachEntity where visible = true")
    Page<AttachEntity> getAll(Pageable pageable);

    @Query("SELECT originName FROM AttachEntity where id = ?1")
    String getOriginName(String id);
}
