package you_tube.Playlist_video.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import you_tube.Playlist_video.dto.PlaylistVideoDTO;
import you_tube.Playlist_video.entity.PlaylistVideoEntity;
import you_tube.Playlist_video.repository.PlaylistVideoRepository;

@Service
@AllArgsConstructor
public class PlaylistVideoService {
    private final PlaylistVideoRepository playlistVideoRepository;

    public PlaylistVideoDTO createPlaylistVideo(PlaylistVideoDTO playlistVideoDTO) {
        PlaylistVideoEntity entity = new PlaylistVideoEntity();
        entity.setVideoId(playlistVideoDTO.getVideoId());
        entity.setOrderNumber(playlistVideoDTO.getOrderNumber());

        playlistVideoRepository.save(entity);
        playlistVideoDTO.setPlaylistId(entity.getPlaylistId());
        return playlistVideoDTO;
    }

    public PlaylistVideoDTO updatePlaylistVideo(PlaylistVideoDTO playlistVideoDTO) {

    }

}
