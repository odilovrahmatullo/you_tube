package you_tube.comment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
@Tag(name = "Comment controller", description = "managing comments of video ")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Api for creating comment", description = "This API is used to create when user writes comment")
    public ResponseEntity<?> create(@RequestBody CommentDTO dto){
        return ResponseEntity.ok(commentService.create(dto));
    }

    @PutMapping("/{commentId}")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Api for updating comment", description = "This API is used when user wanna update own comment")
    public ResponseEntity<?> update(@PathVariable("commentId") Integer commentId,
                                    @RequestBody CommentDTO dto){
        return ResponseEntity.ok(commentService.update(commentId,dto));
    }

    @PutMapping("/delete/{commentId}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @Operation(summary = "Api for deleting comment", description = "This API is used delete comment in has role USER and ADMIN")
    public ResponseEntity<?> delete(@PathVariable Integer commentId){
        return ResponseEntity.ok(commentService.delete(commentId));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Api for getting comments", description = "This API is used to get all comments as dividing pages")
    public ResponseEntity<?> getByPagination(@RequestParam Integer page,
                                             @RequestParam Integer size){
        page = Math.max(page-1,0);
        return ResponseEntity.ok(commentService.getAll(page,size));
    }

    @GetMapping("/by-profile/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Api for getting comment", description = "This API is used to get one profile's comments by her/his id")
    public ResponseEntity<?> getByProfileId(@PathVariable Integer id){
        return ResponseEntity.ok(commentService.getByProfileId(id));
    }

    @GetMapping("/by-own-profile")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Api for getting comment", description = "This API is used to get comments referring user")
    public ResponseEntity<?> getByOwnProfile(){
        return ResponseEntity.ok(commentService.getByOwnProfile());
    }

    @GetMapping("/by-video/{id}")
    @Operation(summary = "Api for getting comment", description = "This API is used to get comment written to one video")
    public ResponseEntity<?> getByVideoId(@PathVariable String id){
        return ResponseEntity.ok(commentService.getByVideoId(id));
    }

    @GetMapping("/by-comment/{id}")
    @Operation(summary = "Api for getting replied comments", description = "This API is used to retrieve comments written as replies to a comment.")
    public ResponseEntity<?> getByCommentId(@PathVariable Integer id){
        return ResponseEntity.ok(commentService.getByCommentId(id));
    }

}
