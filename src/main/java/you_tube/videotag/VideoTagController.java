package you_tube.videotag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video-tag")
public class VideoTagController {
    @Autowired
    private VideoTagService videoTagService;


}
