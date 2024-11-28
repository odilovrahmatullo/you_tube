package you_tube.video;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface VideoRepository extends CrudRepository<VideoEntity, String>,
                                          PagingAndSortingRepository<VideoEntity, String> {
    @Query("select count(v) > 0 from VideoEntity v where v.id = ?1 and v.categoryId = ?2 and v.visible = true")
    Boolean existsByVideoIdAndCategoryId(String id, Integer categoryId);

    @Transactional
    @Modifying
    @Query("UPDATE VideoEntity set videoStatus = ?2 where id = ?1")
    int changeStatus(String videoId, VideoStatus status);

    @Transactional
    @Modifying
    @Query("UPDATE VideoEntity set viewCount = viewCount + 1 where id = ?1")
    void viewCount(String videoId);

    @Query("FROM VideoEntity where categoryId = ?1 order by createdDate desc")
    Page<VideoEntity> getVideos(Integer categoryId, Pageable pageable);

    @Query("FROM VideoEntity where title ILIKE ?1 and visible = true order by createdDate desc")
    List<VideoEntity> getByTitle(String title);

    @Query("FROM VideoEntity order by createdDate desc ")
    Page<VideoEntity> findAllByVisibleTrue(Pageable pageable);

    @Query("FROM VideoEntity where channelId = ?1 and visible = true order by createdDate desc ")
    Page<VideoEntity> getVideosByChannelId(String channelId,Pageable pageable);
}
