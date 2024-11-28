package you_tube.videowatched.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.exceptionHandler.AppBadException;
import you_tube.profile.dto.ProfileDTO;
import you_tube.profile.service.ProfileService;
import you_tube.video.*;
import you_tube.video_watched.entity.VideoWatchedEntity;
import you_tube.video_watched.repository.VideoWatchedRepository;
import you_tube.videowatched.entity.VideoWatchedEntity;
import you_tube.videowatched.repository.VideoWatchedRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class VideoWatchedService {
    @Autowired
    private VideoWatchedRepository videoWatchedRepository;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private VideoService videoService;

    public void createVideoWatched(Integer profileId,String videoId){
        VideoWatchedEntity videoWatchedEntity = new VideoWatchedEntity();
        videoWatchedEntity.setVideo_id(videoId);
        videoWatchedEntity.setProfile_id(profileId);
        videoWatchedEntity.setCreatedDate(LocalDateTime.now());
        videoWatchedRepository.save(videoWatchedEntity);
    }

    public List<VideoShortInfoDTO> getProfileId(Integer id){
        profileService.getById(id);
        List<VideoEntity> byProfileId = videoWatchedRepository.findByProfile_id(id);
        List<VideoShortInfoDTO> videoDTOList = new ArrayList<>();
        if(byProfileId.isEmpty()){
            throw new AppBadException("No videos found");
        }
        for (VideoEntity videoEntity : byProfileId) {
            VideoShortInfoDTO videoShortInfoDTO = videoService.mapperToInfo(videoEntity);
            videoDTOList.add(videoShortInfoDTO);
        }
        return videoDTOList;
    }
}
