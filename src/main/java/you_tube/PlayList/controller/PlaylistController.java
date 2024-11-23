package you_tube.PlayList.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.PlayList.dto.CreatePlaylistDTO;
import you_tube.PlayList.dto.UpdateDTO;
import you_tube.PlayList.enums.PlaylistStatus;
import you_tube.PlayList.service.PlaylistService;

@RestController
@RequestMapping("/api/playlist")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

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

}
