package you_tube.PlayList.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import you_tube.exceptionHandler.AppBadException;
import you_tube.PlayList.dto.CreatePlaylistDTO;
import you_tube.PlayList.dto.UpdateDTO;
import you_tube.PlayList.enums.PlaylistStatus;
import you_tube.PlayList.service.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create/")
    public ResponseEntity<?> createPlaylist(@RequestBody @Valid CreatePlaylistDTO dto){
        return ResponseEntity.ok(playlistService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePlaylist(@PathVariable Integer id,
                                            @RequestBody @Valid UpdateDTO dto){
        return ResponseEntity.ok(playlistService.update(id,dto));
    }
    @PutMapping("/change-status/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Integer id,
                                          @RequestBody @NotBlank PlaylistStatus status){
        return ResponseEntity.ok(playlistService.updateStatus(id,status));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePlaylist(@PathVariable Integer id){
        return ResponseEntity.ok(playlistService.deleted(id));
    }

    @GetMapping("/all-playlist/")
    private ResponseEntity<?> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(playlistService.AllPage(page - 1, size));
    }
    
    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
