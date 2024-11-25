package you_tube.Playlist_video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.Playlist_video.dto.PlaylistVideoDTO;
import you_tube.Playlist_video.service.PlaylistVideoService;

@RestController
@RequestMapping("playlist-video")
public class PlaylistVideoController {
    @Autowired
    private PlaylistVideoService playlistVideoService;

    @PostMapping("")
    public ResponseEntity<?> crate(PlaylistVideoDTO playlistVideoDTO){
        PlaylistVideoDTO dto = playlistVideoService.addVideoByPlaylist(playlistVideoDTO);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{playlistId}")
    public ResponseEntity<?> update(@PathVariable Integer playlistId,
                                    @RequestBody PlaylistVideoDTO playlistVideoDTO){
        PlaylistVideoDTO dto = playlistVideoService.updatePlaylistVideo(playlistId, playlistVideoDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{playlistId}/{videoId}")
    public ResponseEntity<?> delete(@PathVariable Integer playlistId,
                                    @PathVariable String videoId){
        playlistVideoService.deletePlaylistVideo(playlistId, videoId);
        return ResponseEntity.ok().build();
    }


}
