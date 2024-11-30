package you_tube.channel.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import you_tube.attach.dtos.AttachSimpleDTO;
import you_tube.channel.enums.ChannelStatus;
import you_tube.profile.dto.ProfileDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChannelDTO {
    private String id;
    @NotBlank(message = "Name cannot be empty or null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @Size(max = 500, message = "Description must be up to 500 characters")
    private String description;
    @NotBlank(message = "Photo ID cannot be empty or null")
    private String photoId;


    @NotBlank(message = "Banner ID cannot be empty or null")
    private String bannerId;


    private AttachSimpleDTO photo;
    private AttachSimpleDTO banner;
    private LocalDateTime createdDate;
    private Integer profileId;
    private ChannelStatus status;
    private ProfileDTO profile;
}
