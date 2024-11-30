package you_tube.playlistvideo.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import you_tube.playlist.dto.PlayListShortInfoDTO;
import you_tube.playlistvideo.dto.PlaylistVideoDTO;
import you_tube.playlistvideo.entity.PlaylistVideoEntity;
import you_tube.playlistvideo.repository.PlaylistVideoRepository;
import you_tube.exceptionhandler.AppBadException;
import you_tube.video.dto.VideoDTO;
import you_tube.video.entity.VideoEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistVideoService {
    private final PlaylistVideoRepository playlistVideoRepository;

    public PlaylistVideoDTO addVideoByPlaylist(PlaylistVideoDTO playlistVideoDTO) {
        PlaylistVideoEntity entity = new PlaylistVideoEntity();
        if (playlistVideoDTO.getOrderNumber() != null) {
            entity.setOrderNumber(playlistVideoDTO.getOrderNumber());
        }
        for (String videoId : playlistVideoDTO.getVideoId()) {
            entity.setPlaylistId(playlistVideoDTO.getPlaylistId());
            entity.setVideoId(videoId);
            entity.setVisible(Boolean.TRUE);
            entity.setCreatedDate(LocalDateTime.now());
            playlistVideoRepository.save(entity);
        }
        return playlistVideoDTO;
    }

    public PlaylistVideoDTO updatePlaylistVideo(Integer playlistId, PlaylistVideoDTO playlistVideoDTO) {
        PlaylistVideoEntity entity = playlistVideoRepository.findByIdAndVisibleTrue(playlistId);
        if(!entity.getPlaylistId().equals(playlistId)) {
            throw new AppBadException("Not found playlist video");
        }
        if (playlistVideoDTO.getOrderNumber() != null) {
            entity.setOrderNumber(playlistVideoDTO.getOrderNumber());
        }
        for (String videoId : playlistVideoDTO.getVideoId()) {
            entity.setPlaylistId(playlistVideoDTO.getPlaylistId());
            entity.setVideoId(videoId);
            entity.setVisible(Boolean.TRUE);
            entity.setCreatedDate(LocalDateTime.now());
            playlistVideoRepository.save(entity);
        }
        return playlistVideoDTO;
    }

    public void deletePlaylistVideo(Integer playlistId, String videoId) {
        PlaylistVideoEntity entity = playlistVideoRepository.findByIdAndVisibleTrue(playlistId);
        if(!entity.getPlaylistId().equals(playlistId)) {
            throw new AppBadException("Not found playlist video");
        }
        int deletedNumber = playlistVideoRepository.deletedVideoByPlaylis(playlistId,videoId);
        if(deletedNumber == 0) {
            throw new AppBadException("Video does not exist");
        }
    }

    public List<VideoDTO> getPlaylistVideos(Integer playlistId) {
        List<VideoEntity> entity = playlistVideoRepository.findAllVideosByPlaylistId(playlistId);
        if(entity != null){
            throw new AppBadException("No videos in the playlist");
        }
        List<VideoDTO> videos = new ArrayList<>();
        for(VideoEntity video : entity) {



        }

        return null;
    }

    public List<PlayListShortInfoDTO> getPlayList(String videoId) {
        return playlistVideoRepository.getPlayListByVideoId(videoId);
    }



}
