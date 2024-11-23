package you_tube.profile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import you_tube.profile.entity.ProfileEntity;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

    @Query("from ProfileEntity where email = ?1 and visible = true ")
    ProfileEntity findByEmail(String email);

    @Query("FROM ProfileEntity p WHERE p.visible  = true ")
    Page<ProfileEntity> findAllPage(Pageable pageable);

    Optional<ProfileEntity> findByIdAndVisibleTrue(Integer id);
    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);

    @Modifying
    @Transactional
    @Query("UPDATE ProfileEntity p SET p.visible = false WHERE p.id=?1 ")
    int deleted(Integer id);
}
