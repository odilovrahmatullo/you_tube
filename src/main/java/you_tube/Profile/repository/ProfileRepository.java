package you_tube.Profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import you_tube.Profile.entity.ProfileEntity;

@Repository
public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer> {

    ProfileEntity findByEmail(String email);
}
