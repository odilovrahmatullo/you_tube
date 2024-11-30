package you_tube.playlist.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import you_tube.exceptionhandler.AppBadException;
import you_tube.playlist.dto.CreatePlaylistDTO;
import you_tube.playlist.dto.UpdateDTO;
import you_tube.playlist.enums.PlaylistStatus;
import you_tube.playlist.service.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
@Tag(name = "Tag controller", description = "Playlist. Songs, lessons...")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;


    @PostMapping("/create/")
    @Operation(summary = "Api for playlist", description = "Admin creates playlist for channel")
    public ResponseEntity<?> createPlaylist(@RequestBody @Valid CreatePlaylistDTO dto){
        return ResponseEntity.ok(playlistService.create(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Api for playlist", description = "Admin updates playlist information")
    public ResponseEntity<?> updatePlaylist(@PathVariable Integer id,
                                            @RequestBody @Valid UpdateDTO dto){
        return ResponseEntity.ok(playlistService.update(id,dto));
    }

    @PutMapping("/change-status/{id}")
    @Operation(summary = "Api for playlist", description = "Playlist status for channel: private and channel, (playlist_id)")
    public ResponseEntity<?> changeStatus(@PathVariable Integer id,
                                          @RequestBody @NotBlank PlaylistStatus status){
        return ResponseEntity.ok(playlistService.updateStatus(id,status));
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Api for playlist", description = "Playlist delete by playlid_id")
    public ResponseEntity<?> deletePlaylist(@PathVariable Integer id){
        return ResponseEntity.ok(playlistService.deleted(id));
    }

    @GetMapping("/all-playlist")
    @Operation(summary = "Api for playlist", description = "Shows all playlists")
    private ResponseEntity<?> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                         @RequestParam(value = "size", defaultValue = "10") int size) {
        return ResponseEntity.ok(playlistService.allPage(page - 1, size));
    }
    
    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
