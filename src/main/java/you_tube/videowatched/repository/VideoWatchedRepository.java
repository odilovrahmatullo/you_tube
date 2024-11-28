package you_tube.videowatched.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import you_tube.video.VideoEntity;
import you_tube.videowatched.entity.VideoWatchedEntity;

import java.util.List;

@Repository
public interface VideoWatchedRepository extends JpaRepository<VideoWatchedEntity, String> {
    @Query("select v.video FROM VideoWatchedEntity as v WHERE v.profile_id=?1")
    List<VideoEntity> findByProfile_id(Integer videoId);
}
