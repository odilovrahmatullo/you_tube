package you_tube.you_tube.Profile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import you_tube.you_tube.Profile.entity.ProfileEntity;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

    ProfileEntity findByEmail(String email);

//    @Query("FROM ProfileEntity p WHERE  = true ")
//    Page<ProfileEntity> findAllPage(Pageable pageable);
}
