package you_tube.comment.commentLike.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.comment.commentLike.service.CommentLikeService;
import you_tube.exceptionhandler.AppBadException;

@RestController
@RequestMapping("/api/comment-like")
public class CommentLikeController {

    @Autowired
    private CommentLikeService commentLikeService;

    @PostMapping("/like")
    @Operation(summary = "Api for comment like", description = "This api for create comment like")
    public ResponseEntity<?> createLike(@RequestBody Integer comment_id,
                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(commentLikeService.createLike(comment_id));
    }
    @PostMapping("/dislike")
    @Operation(summary = "Api for comment dislike", description = "This api for create comment dislike")
    public ResponseEntity<?> createDisLike(@RequestBody Integer comment_id,
                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(commentLikeService.dislike(comment_id));
    }

    @GetMapping("/")
    @Operation(summary = "GET All CommentLike", description = "This User  All Comment Like")
    public ResponseEntity<?> getUserAll(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(commentLikeService.getAllCommentLikeInfo());
    }


    @GetMapping("/{id}")
    @Operation(summary = "GET User Id All CommentLike", description = "This ADMIN By User Id All Comment Like")
    public ResponseEntity<?> getUserAllAdmin(@PathVariable Integer id,
                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(commentLikeService.getAllCommentLikeInfoAdmin(id));
    }




    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
