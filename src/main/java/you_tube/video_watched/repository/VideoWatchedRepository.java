package you_tube.video_watched.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import you_tube.video_watched.entity.VideoWatchedEntity;

@Repository
public interface VideoWatchedRepository extends JpaRepository<VideoWatchedEntity, String> {
    VideoWatchedEntity findByProfile_id(Integer videoId);
}
