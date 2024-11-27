package you_tube.videowatched.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import you_tube.videowatched.entity.VideoWatchedEntity;

@Repository
public interface VideoWatchedRepository extends JpaRepository<VideoWatchedEntity, String> {
    VideoWatchedEntity findByProfile_id(Integer videoId);
}
