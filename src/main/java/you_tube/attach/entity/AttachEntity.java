package you_tube.attach.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;



@Entity
@Table(name = "attach")
@Getter
@Setter
public class AttachEntity {
    @Id
    private String id;
    @Column(name = "origin_name")
    private String originName;
    @Column(name = "path")
    private String path;
    @Column(name = "extension")
    private String extension;
    @Column(name = "size")
    private Long size;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "duration")
    private long duration;
    @Column(name = "visible")
    private Boolean visible = true;

}
