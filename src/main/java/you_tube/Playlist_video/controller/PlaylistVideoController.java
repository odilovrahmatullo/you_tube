package you_tube.Playlist_video.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import you_tube.Playlist_video.dto.PlaylistVideoDTO;
import you_tube.Playlist_video.service.PlaylistVideoService;

@RestController
@RequestMapping("playlist-video")
public class PlaylistVideoController {
    @Autowired
    private PlaylistVideoService playlistVideoService;

    @PostMapping("")
    public ResponseEntity<?> crate(PlaylistVideoDTO playlistVideoDTO){
        PlaylistVideoDTO dto = playlistVideoService.createPlaylistVideo(playlistVideoDTO);
        return ResponseEntity.ok(dto);
    }


}
