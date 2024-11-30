package you_tube.comment.commentLike.dto;

import lombok.Getter;
import lombok.Setter;
import you_tube.comment.commentLike.enums.CommentLikeType;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentLikeInfoDTO {
    private Integer profile_id;
    private Integer commentId;
    private LocalDateTime created_date;
    private CommentLikeType commentLikeType;
}
