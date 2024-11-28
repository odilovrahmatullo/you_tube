package you_tube.video_like.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import you_tube.config.CustomUserDetails;
import you_tube.exceptionhandler.AppBadException;
import you_tube.utils.SpringSecurityUtil;
import you_tube.video.VideoEntity;
import you_tube.video.VideoRepository;
import you_tube.video_like.dto.ChannelDTO;
import you_tube.video_like.dto.LikeDTO;
import you_tube.video_like.dto.VideoLikeInfoDTO;
import you_tube.video_like.dto.VideoShortDTO;
import you_tube.video_like.entity.LikeEntity;
import you_tube.video_like.enums.LikeType;
import you_tube.video_like.repository.LikeRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeService {
    @Autowired
    private LikeRepository likeRepository;
    @Autowired
    private VideoRepository videoRepository;

    public Boolean createLike(LikeDTO dto) {
        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();
        Optional<VideoEntity> byId = videoRepository.findById(dto.getVideo_id());
        if (byId.isEmpty()) {
            throw new AppBadException("video not exist");
        }
        LikeEntity likeEntity = new LikeEntity();
        likeEntity.setProfileId(currentUser.getId());
        likeEntity.setVideoId(dto.getVideo_id());
        likeEntity.setType(LikeType.LIKE);
        likeEntity.setCreatedDate(LocalDateTime.now());
        likeRepository.save(likeEntity);
        return true;
    }
    public String deleteLike(String id) {
        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();
        LikeEntity byProfileIdAndChannelId = likeRepository.findByProfileIdAndChannelId(currentUser.getId(),id);
        if (byProfileIdAndChannelId == null) {
            throw new AppBadException("like not exist");
        }
        likeRepository.delete(byProfileIdAndChannelId);
        return "success";
    }

    public List<VideoLikeInfoDTO> gelAllLike() {
        CustomUserDetails currentUser = SpringSecurityUtil.getCurrentUser();
        List<LikeEntity> allVideoLike = likeRepository.getAllVideoLike(currentUser.getId());
        if (allVideoLike.isEmpty()) {
            throw new AppBadException("like not exist");
        }
        List<VideoLikeInfoDTO> allVideoLikeInfoDTO = new ArrayList<>();
        for (LikeEntity likeEntity : allVideoLike) {
            VideoLikeInfoDTO videoLikeInfoDTO = new VideoLikeInfoDTO();
            videoLikeInfoDTO.setId(likeEntity.getId());

            VideoShortDTO videoShortDTO = new VideoShortDTO();
            videoShortDTO.setId(likeEntity.getVideo().getId());
            videoShortDTO.setName(likeEntity.getVideo().getTitle());

            ChannelDTO channelDTO  = new ChannelDTO();
            channelDTO.setId(likeEntity.getVideo().getChannel().getId());
            channelDTO.setName(likeEntity.getVideo().getChannel().getName());
            videoShortDTO.setChannel(channelDTO);

            videoLikeInfoDTO.setVideo(videoShortDTO);
            allVideoLikeInfoDTO.add(videoLikeInfoDTO);
        }
        return allVideoLikeInfoDTO;
    }

    public Object getAdminAll(Integer id) {
        List<LikeEntity> allVideoLike = likeRepository.getAllVideoLike(id);
        if (allVideoLike.isEmpty()) {
            throw new AppBadException("like not exist");
        }
        List<VideoLikeInfoDTO> allVideoLikeInfoDTO = new ArrayList<>();
        for (LikeEntity likeEntity : allVideoLike) {
            VideoLikeInfoDTO videoLikeInfoDTO = new VideoLikeInfoDTO();
            videoLikeInfoDTO.setId(likeEntity.getId());

            VideoShortDTO videoShortDTO = new VideoShortDTO();
            videoShortDTO.setId(likeEntity.getVideo().getId());
            videoShortDTO.setName(likeEntity.getVideo().getTitle());

            ChannelDTO channelDTO  = new ChannelDTO();
            channelDTO.setId(likeEntity.getVideo().getChannel().getId());
            channelDTO.setName(likeEntity.getVideo().getChannel().getName());
            videoShortDTO.setChannel(channelDTO);

            videoLikeInfoDTO.setVideo(videoShortDTO);
            allVideoLikeInfoDTO.add(videoLikeInfoDTO);
        }
        return allVideoLikeInfoDTO;
    }
}
