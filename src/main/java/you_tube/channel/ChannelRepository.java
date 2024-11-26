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

import java.util.List;

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

    @Modifying
    @Transactional
    @Query("Update ChannelEntity Set status = ?2 where id =?1")
    Integer changeStatus(String id, ChannelStatus status);

    @Query("FROM ChannelEntity c JOIN c.profile p WHERE p.email = ?1 and c.status = ?2")
    List<ChannelEntity> getChannels(String email, ChannelStatus channelStatus);

    @Query("select ch.id as id, ch.name as name from ChannelEntity as ch")
    ChannelShortInfoDTO getShortInfo(String id);
}
