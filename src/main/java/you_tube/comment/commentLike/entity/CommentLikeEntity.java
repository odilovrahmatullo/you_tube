package you_tube.comment.commentLike.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import you_tube.comment.commentLike.enums.CommentLikeType;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @Column(name = "comment_id")
    private Integer commentId;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private CommentLikeType type;
    @Column(name = "visible")
    private Boolean visible;
}
