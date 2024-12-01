package you_tube.playlistvideo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import you_tube.playlistvideo.dto.PlaylistVideoDTO;
import you_tube.playlistvideo.service.PlaylistVideoService;

@RestController
@RequestMapping("/playlist-video")
@Tag(name = "Playlist-video controller", description = "Playlist`s videos")
public class PlaylistVideoController {
    @Autowired
    private PlaylistVideoService playlistVideoService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Api for playlist`s videos", description = "This api is for playlist video")
    public ResponseEntity<?> crate(@RequestBody PlaylistVideoDTO playlistVideoDTO){
        PlaylistVideoDTO dto = playlistVideoService.addVideoByPlaylist(playlistVideoDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{playlistId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(summary = "Api for playlist`s videos", description = "This api update the video's playlist membership")
    public ResponseEntity<?> update(@PathVariable Integer playlistId,
                                    @RequestBody PlaylistVideoDTO playlistVideoDTO){
        PlaylistVideoDTO dto = playlistVideoService.updatePlaylistVideo(playlistId, playlistVideoDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{playlistId}/{videoId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @Operation(summary = "Api for playlist`s videos", description = "This api delete a video from a playlist")
    public ResponseEntity<?> delete(@PathVariable Integer playlistId,
                                    @PathVariable String videoId){
        playlistVideoService.deletePlaylistVideo(playlistId, videoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getAll/{playlistId}")
    @Operation(summary = "Api for playlist`s videos", description = "This api shows all videos in the playlist")
    public ResponseEntity<?> getAll(@PathVariable Integer playlistId){
        return ResponseEntity.ok(playlistVideoService.getPlaylistVideos(playlistId));
    }


}
