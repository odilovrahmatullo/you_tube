package you_tube.Playlist_video.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import you_tube.Playlist_video.entity.PlaylistVideoEntity;
import you_tube.category.entity.CategoryEntity;

@Repository
public interface PlaylistVideoRepository extends CrudRepository<PlaylistVideoEntity,Integer> {

    @Query("from PlaylistVideoEntity where playlistId=?1 and visible = true")
    CategoryEntity findByIdAndVisibleTrue(int id);

}
