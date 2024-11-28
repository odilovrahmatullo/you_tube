package you_tube.video_watched.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import you_tube.exceptionHandler.AppBadException;
import you_tube.video_watched.service.VideoWatchedService;

@RestController
@RequestMapping("/api/video-watched")
public class VideoWatchedController {
    @Autowired
    private VideoWatchedService videoWatchedService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getVideoWatched(Integer id) {
        return ResponseEntity.ok(videoWatchedService.getProfileId(id));
    }
    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
