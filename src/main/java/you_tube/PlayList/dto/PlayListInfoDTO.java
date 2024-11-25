package you_tube.PlayList.dto;

import lombok.Getter;
import lombok.Setter;
import you_tube.PlayList.enums.PlaylistStatus;
import you_tube.channel.ChannelEntity;
import you_tube.profile.dto.GetProfileDTO;

@Getter
@Setter
public class PlayListInfoDTO {
    private Integer id;
    private String name;
    private String description;
    private PlaylistStatus status;
    private Integer order_num;
    private ChannelEntity channel;
    private GetProfileDTO profile;

}
