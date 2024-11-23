package you_tube.channel;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ChannelRepository extends CrudRepository<ChannelEntity,Integer> {

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
}
