package you_tube.channel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import you_tube.attach.dtos.AttachSimpleDTO;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelShortInfoDTO {
    private String id;
    private String name;
    private String photo;
    private AttachSimpleDTO attach;
}
