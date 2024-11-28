package you_tube.video;

import org.apache.coyote.Response;
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

    @PutMapping("/{videoId}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?> update(@PathVariable String videoId,
                                    @RequestBody VideoDTO dto){
        return ResponseEntity.ok(videoService.update(videoId,dto));
    }

    @PutMapping("status/{videoId}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?> changeStatus(@PathVariable String videoId,
                                          @RequestParam VideoStatus status){
        return ResponseEntity.ok(videoService.changeStatus(videoId,status));

    }

    @PutMapping("/view-count/{videoId}")
    public ResponseEntity<Void> viewCount(@PathVariable String videoId){
        videoService.viewCount(videoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("byCategory/{categoryId}")
    public ResponseEntity<?> getByCategoryId(@PathVariable Integer categoryId,
                                             @RequestParam(defaultValue="1") Integer page,
                                             @RequestParam(defaultValue = "5") Integer size){
        page = Math.max(page-1,0);
        return ResponseEntity.ok(videoService.getByCategoryId(categoryId,page,size));
    }
    @GetMapping("title/{title}")
    public ResponseEntity<?> getByTitle(@PathVariable String title){
        return ResponseEntity.ok(videoService.getByTitle(title));
    }
}
