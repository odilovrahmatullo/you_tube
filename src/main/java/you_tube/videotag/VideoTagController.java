package you_tube.videotag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video-tag")
public class VideoTagController {
    @Autowired
    private VideoTagService videoTagService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?> create (@RequestBody VideoTagDTO dto){
        return ResponseEntity.ok(videoTagService.create(dto));
    }

}
