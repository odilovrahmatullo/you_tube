package you_tube.videowatched.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.videowatched.entity.VideoWatchedEntity;
import you_tube.videowatched.repository.VideoWatchedRepository;

import java.time.LocalDateTime;

@Service
public class VideoWatchedService {
    @Autowired
    private VideoWatchedRepository videoWatchedRepository;

    public void createVideoWatched(Integer profileId,String videoId){
        VideoWatchedEntity videoWatchedEntity = new VideoWatchedEntity();
        videoWatchedEntity.setVideo_id(videoId);
        videoWatchedEntity.setProfile_id(profileId);
        videoWatchedEntity.setCreatedDate(LocalDateTime.now());
        videoWatchedRepository.save(videoWatchedEntity);
    }

//    public VideoWatchedEntity getProfileId(Integer id){
//        videoWatchedRepository.fi
//    }
}
