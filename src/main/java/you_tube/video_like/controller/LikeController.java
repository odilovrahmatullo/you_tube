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

    @PostMapping("/")
    public ResponseEntity<?> createLike(@RequestBody LikeDTO dto,
                                        @RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(likeService.createLike(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> deleteLike(@PathVariable String id) {
        return ResponseEntity.ok(likeService.deleteLike(id));
    }

    @GetMapping("/")
    public ResponseEntity<?> getLike(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(likeService.gelAllLike());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllLike(@PathVariable Integer id) {
        return ResponseEntity.ok(likeService.getAdminAll(id));
    }

    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
