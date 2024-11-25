package you_tube.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/video")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> create(@RequestBody VideoDTO dto){
        return ResponseEntity.ok(videoService.create(dto));
    }
}
