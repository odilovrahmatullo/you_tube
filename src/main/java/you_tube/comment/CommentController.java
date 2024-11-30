package you_tube.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> create(@RequestBody CommentDTO dto){
        return ResponseEntity.ok(commentService.create(dto));
    }

    @PutMapping("/{commentId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> update(@PathVariable("commentId") Integer commentId,
                                    @RequestBody CommentDTO dto){
        return ResponseEntity.ok(commentService.update(commentId,dto));
    }

}
