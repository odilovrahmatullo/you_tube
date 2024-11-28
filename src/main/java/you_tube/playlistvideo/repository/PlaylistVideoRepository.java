package you_tube.playlistvideo.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import you_tube.playlistvideo.entity.PlaylistVideoEntity;
import you_tube.video.VideoEntity;

import java.util.List;

@Repository
public interface PlaylistVideoRepository extends CrudRepository<PlaylistVideoEntity,Integer> {

    @Query("from PlaylistVideoEntity a where a.playlistId=?1 and a.visible = true")
    PlaylistVideoEntity findByIdAndVisibleTrue(Integer id);



    @Modifying
    @Transactional
    @Query("UPDATE PlaylistVideoEntity p SET p.visible = false WHERE p.playlistId = ?1 and p.videoId = ?2")
    int deletedVideoByPlaylis(Integer playlisId, String videoId);

    @Query("select a.video from PlaylistVideoEntity a where a.playlistId = ?1 and a.visible = true")
    List<VideoEntity> findAllVideosByPlaylistId(Integer playlistId);

}
