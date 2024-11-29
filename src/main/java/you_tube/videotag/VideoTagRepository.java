package you_tube.videotag;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import you_tube.tag.entity.TagEntity;
import you_tube.video.VideoEntity;

import java.util.List;

public interface VideoTagRepository extends CrudRepository<VideoTagEntity,String>,
                                             PagingAndSortingRepository<VideoTagEntity,String> {
    @Modifying
    @Transactional
    @Query("DELETE FROM VideoTagEntity where tagId = ?1 and videoId = ?2")
    Integer deleteVideoTag(Integer tagId, String videoId);

    @Query("FROM VideoTagEntity where videoId = ?1")
    List<VideoTagEntity> getById(String videoId);

    @Query("SELECT video FROM VideoTagEntity where tagId = ?1 order by createdDate desc")
    Page<VideoEntity> getByTagId(String tagId, Pageable pageable);

    @Query("SELECT tag FROM VideoTagEntity where videoId = ?1 order by createdDate desc")
    List<TagEntity> getTagsByVideoId(String videoId);
}
