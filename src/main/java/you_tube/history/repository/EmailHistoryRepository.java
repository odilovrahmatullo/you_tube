package you_tube.history.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import you_tube.history.entity.EmailHistoryEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity, Integer> {

    List<EmailHistoryEntity> findByEmail(String email);

    @Query("SELECT e FROM EmailHistoryEntity e WHERE DATE(e.createdData) = :date")
    List<EmailHistoryEntity> findAllByCreatedDate(@Param("date") LocalDateTime date);




}
