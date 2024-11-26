package you_tube.video;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?> update(@PathVariable String id,
                                    @RequestBody VideoDTO dto){
        return ResponseEntity.ok(videoService.update(id,dto));
    }
}
