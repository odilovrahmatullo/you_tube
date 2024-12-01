package you_tube.attach.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import you_tube.attach.dtos.AttachDTO;
import you_tube.attach.service.AttachService;
import you_tube.enums.AppLanguage;

@RequestMapping("/attach")
@RestController
@Tag(name = "Attach controller", description = "photo and video add")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @PostMapping("/upload")
    @Operation(summary = "Api for attach", description = "This api used for video and photo upload")
    public ResponseEntity<AttachDTO> upload(@RequestParam("file") MultipartFile file,
                                            @RequestHeader(value = "Accepted-Language", defaultValue = "uz") AppLanguage lang){
       return ResponseEntity.ok(attachService.upload(file,lang.name()));
    }
    @GetMapping("/open/{id}")
    @Operation(summary = "Api for attach", description = "This api used for video and photo open (id)")

    public ResponseEntity<Resource> open(@PathVariable String id,
                                         @RequestHeader(value = "Accept-Language", defaultValue = "uz") AppLanguage lang){
        return attachService.open(id,lang.name());
    }
   /* */

    @GetMapping("/download/{id}")
    @Operation(summary = "Api for attach", description = "This api used for video and photo download (id)")
    public ResponseEntity<Resource> download(@PathVariable String id,
                                             @RequestHeader(value = "Accept-Language", defaultValue = "uz") AppLanguage lang) {
        return attachService.download(id, lang.name());
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Api for attach", description = "This api admin for video and photo open all")
    public ResponseEntity<?> all(@RequestParam int page,
                                 @RequestParam int size) {
            page = Math.max(page - 1, 0);
            return ResponseEntity.ok(attachService.getAll(page, size));
    }

    @PutMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Api for attach", description = "This api admin for video and photo delete (id)")
    public ResponseEntity<?> delete(@PathVariable String id){
        attachService.delete(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }
}
