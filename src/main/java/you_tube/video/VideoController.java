package you_tube.video;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import you_tube.enums.AppLanguage;

@RestController
@RequestMapping("/video")
@Tag(name = "Video controller", description = "Videos")
public class VideoController {
    @Autowired
    private VideoService videoService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Api for video", description = "This api for create video")
    public ResponseEntity<?> create(@RequestBody VideoDTO dto){
        return ResponseEntity.ok(videoService.create(dto));
    }

    @PutMapping("/{videoId}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    @Operation(summary = "Api for video", description = "This api for video update")
    public ResponseEntity<?> update(@PathVariable String videoId,
                                    @RequestBody VideoDTO dto){
        return ResponseEntity.ok(videoService.update(videoId,dto));
    }

    @PutMapping("status/{videoId}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    @Operation(summary = "Api for video", description = "This api for video info")
    public ResponseEntity<?> changeStatus(@PathVariable String videoId,
                                          @RequestParam VideoStatus status){
        return ResponseEntity.ok(videoService.changeStatus(videoId,status));

    }

    @PutMapping("/view-count/{videoId}")
    @Operation(summary = "Api for video", description = "This api for video view count")
    public ResponseEntity<Void> viewCount(@PathVariable String videoId){
        videoService.viewCount(videoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("byCategory/{categoryId}")
    @Operation(summary = "Api for video", description = "This api for provides videos related to the category, (category_id)")
    public ResponseEntity<?> getByCategoryId(@PathVariable Integer categoryId,
                                             @RequestParam(defaultValue="1") Integer page,
                                             @RequestParam(defaultValue = "5") Integer size){
        page = Math.max(page-1,0);
        return ResponseEntity.ok(videoService.getByCategoryId(categoryId,page,size));
    }
    @GetMapping("title/{title}")
    @Operation(summary = "Api for video", description = "This api for to get by title")
    public ResponseEntity<?> getByTitle(@PathVariable String title){
        return ResponseEntity.ok(videoService.getByTitle(title));
    }

    @GetMapping("tag/{tagId}")
    @Operation(summary = "Api for video", description = "This api for get tag, (tag_id)")
    public ResponseEntity<?> getByTagId(@PathVariable String tagId,
                                        @RequestParam Integer page,
                                        @RequestParam Integer size,
                                        @RequestHeader(value = "Accepted-Language", defaultValue = "uz") AppLanguage lang){
        page = Math.max(page-1,0);
        return ResponseEntity.ok(videoService.getByTagId(tagId,lang.name(),page,size));
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Api for video", description = "This api for get video list")
    public ResponseEntity<?> getVideoList (@RequestParam Integer page,
                                           @RequestParam Integer size){
        page = Math.max(page-1,0);
        return ResponseEntity.ok(videoService.getVideoList(page,size));
    }
}
