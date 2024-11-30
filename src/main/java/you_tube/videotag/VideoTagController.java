package you_tube.videotag;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/video-tag")
@Tag(name = "Video tag controller", description = "#music, #2024")
public class VideoTagController {
    @Autowired
    private VideoTagService videoTagService;

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    @Operation(summary = "Api for video tag", description = "This api for create tag")
    public ResponseEntity<?>create(@RequestBody VideoTagDTO dto){
        return ResponseEntity.ok(videoTagService.create(dto));
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    @Operation(summary = "Api for video tag", description = "This api for delete like")
    public ResponseEntity<?>delete(@RequestParam(name = "tagId") Integer tagId,
                                   @RequestParam(name = "videoId") String videoId){
        return ResponseEntity.ok(videoTagService.delete(tagId,videoId));
    }

    @GetMapping("/{videoId}")
    @Operation(summary = "Api for vidoe tag", description = "This api for extract tags by video, (video_id)")
    public ResponseEntity<?> get(@PathVariable String videoId){
        return ResponseEntity.ok(videoTagService.get(videoId));
    }
}
