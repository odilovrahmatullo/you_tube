package you_tube.video_like.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.exceptionhandler.AppBadException;
import you_tube.video_like.dto.LikeDTO;
import you_tube.video_like.service.LikeService;

@RestController
@RequestMapping("/api/video-like")
public class LikeController {
    @Autowired
    private LikeService likeService;
// USER
    @PostMapping("/")
    public ResponseEntity<?> createLike(@RequestBody LikeDTO dto,
                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(likeService.createLike(dto));
    }
//  USER
    @PutMapping("/{id}")
    public ResponseEntity<?> deleteLike(@PathVariable String id) {
        return ResponseEntity.ok(likeService.deleteLike(id));
    }
// USER
    @GetMapping("/")
    public ResponseEntity<?> getLike(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(likeService.getAllLike());
    }

//    ADMIN
    @GetMapping("/admin/{id}")
    public ResponseEntity<?> getAllLike(@PathVariable Integer id) {
        return ResponseEntity.ok(likeService.getAllAdminVideoLike(id));
    }

    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
