package you_tube.PlayList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import you_tube.PlayList.entity.PlayListEntity;
import you_tube.PlayList.enums.PlaylistStatus;

import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<PlayListEntity, Integer> {

    @Query(" FROM PlayListEntity p WHERE p.channel_id=?1 AND p.name=?2")
    PlayListEntity exists(Integer id,String name);

    @Modifying
    @Transactional
    @Query("UPDATE PlayListEntity p SET p.status = ?2 WHERE p.id = ?1")
    int updateStatus(Integer id, PlaylistStatus status);

}
