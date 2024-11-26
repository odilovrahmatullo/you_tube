package you_tube.video_watched.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import you_tube.video_watched.service.VideoWatchedService;

@RestController
@RequestMapping("/api/video-watched")
public class VideoWatchedController {
    @Autowired
    private VideoWatchedService videoWatchedService;

}
