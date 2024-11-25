package you_tube.video_watched.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class VideoWatchedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "profile_id")
    private Integer profile_id;
    @Column(name = "video_id")
    private String video_id;
    @Column(name = "create_date")
    private LocalDateTime createdDate;
}
