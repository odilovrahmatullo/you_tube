package you_tube.channel;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChannelRepository extends CrudRepository<ChannelEntity,String>,
                                           PagingAndSortingRepository<ChannelEntity,String> {

    @Modifying
    @Transactional
    @Query("UPdate ChannelEntity set photoId = ?2 where id = ?1")
    int updatePhoto(String id, String photoId);

    @Modifying
    @Transactional
    @Query("UPdate ChannelEntity set bannerId = ?2 where id = ?1")
    int updateBanner(String id, String bannerId);

    @Modifying
    @Transactional
    @Query("Update ChannelEntity set name = ?2,description = ?3 where id = ?1")
    int updateInfo(String id,String name, String description);


    @Query("From ChannelEntity where status = ?1 ")
    Page<ChannelEntity> getPagination(ChannelStatus status,Pageable pagination);

    @Query("from ChannelEntity where id = ?1 and status = ?2")
    ChannelEntity getByIdAndVisibleTrue(String id, ChannelStatus status);
}
