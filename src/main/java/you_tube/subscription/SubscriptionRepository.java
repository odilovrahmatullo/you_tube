package you_tube.subscription;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface SubscriptionRepository extends CrudRepository<SubscriptionEntity,Integer> {

    @Modifying
    @Transactional
    @Query("Update SubscriptionEntity Set status = ?2,unSubscribeDate = ?4 where channelId = ?1 and profileId = ?3")
    void changeStatus(String channelId, SubscriptionStatus status, Integer profileId, LocalDateTime now);
}
