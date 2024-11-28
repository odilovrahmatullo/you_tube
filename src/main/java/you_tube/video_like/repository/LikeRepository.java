package you_tube.video_like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import you_tube.video_like.dto.VideoLikeInfoDTO;
import you_tube.video_like.entity.LikeEntity;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity,Integer> {

    @Query("FROM LikeEntity AS l WHERE l.profileId = ?1 AND l.videoId = ?2 ")
    LikeEntity findByProfileIdAndChannelId(Integer profileId,String videoId);

    @Query("FROM LikeEntity as l WHERE l.profileId = ?1 ")
    List<LikeEntity> getAllVideoLike(Integer profileId);
}
