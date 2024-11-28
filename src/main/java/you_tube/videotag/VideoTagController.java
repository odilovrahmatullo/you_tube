package you_tube.videotag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video-tag")
public class VideoTagController {
    @Autowired
    private VideoTagService videoTagService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?>create(@RequestBody VideoTagDTO dto){
        return ResponseEntity.ok(videoTagService.create(dto));
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?>delete(@RequestParam(name = "tagId") Integer tagId,
                                   @RequestParam(name = "videoId") String videoId){
        return ResponseEntity.ok(videoTagService.delete(tagId,videoId));
    }

    @GetMapping("/{videoId}")
    public ResponseEntity<?> get(@PathVariable String videoId){
        return ResponseEntity.ok(videoTagService.get(videoId));
    }
}
