package you_tube.video_like.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import you_tube.video_like.dto.LikeInfoDTO;
import you_tube.video_like.entity.LikeEntity;
import you_tube.video_like.enums.LikeType;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity,Integer> {

    @Query("FROM LikeEntity AS l WHERE l.profileId = ?1 AND l.videoId = ?2 ")
    LikeEntity findByProfileIdAndChannelId(Integer profileId,String videoId);

    @Query("""
            SELECT l FROM LikeEntity AS l
                JOIN FETCH l.video v
                JOIN FETCH v.channel c
                WHERE l.profileId = ?1
                AND (l.type = 'LIKE' OR l.type = 'DISLIKE')
                ORDER BY l.createdDate DESC
                """)
    List<LikeEntity> getAllVideoLike(Integer profileId);

    @Query("FROM LikeEntity AS l WHERE l.videoId=?1 AND l.profileId = ?2 AND l.type = 'LIKE' ")
    LikeEntity findByVideoIdAndAndProfileId(String videoId, Integer profileId);


    @Query(value = "SELECT " +
            "COUNT(CASE WHEN vl.type = 'LIKE' THEN 1 END) AS likeCount, " +
            "COUNT(CASE WHEN vl.type = 'DISLIKE' THEN 1 END) AS dislikeCount, " +
            "MAX(CASE WHEN vl.profile_id = ?2 THEN vl.type ELSE NULL END) AS likeType " +
            "FROM video v " +
            "LEFT JOIN video_like vl ON v.id = vl.video_id " +
            "WHERE v.id = ?1 " +
            "GROUP BY v.id ", nativeQuery = true)
    LikeInfoDTO getLikeInfo(String videoId, Integer profileId);



}
