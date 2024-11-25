package you_tube.PlayList.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;
import you_tube.PlayList.entity.PlayListEntity;
import you_tube.PlayList.enums.PlaylistStatus;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<PlayListEntity, Integer>, PagingAndSortingRepository<PlayListEntity,Integer> {

    @Query(" FROM PlayListEntity p WHERE p.channelId=?1 AND p.name=?2")
    PlayListEntity exists(String id,String name);

    @Modifying
    @Transactional
    @Query("UPDATE PlayListEntity p SET p.status = ?2 WHERE p.id = ?1")
    int updateStatus(Integer id, PlaylistStatus status);

    @Modifying
    @Transactional
    @Query("UPDATE PlayListEntity p SET p.visible = false WHERE p.id = ?1")
    int deletedVisible(Integer id);

    @Query("FROM PlayListEntity s ")
    Page<PlayListEntity> getAll6(PageRequest pageRequest);
}
