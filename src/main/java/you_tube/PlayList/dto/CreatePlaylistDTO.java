package you_tube.PlayList.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import you_tube.PlayList.enums.PlaylistStatus;

@Getter
@Setter
public class CreatePlaylistDTO {
    private Integer id;
    @NotNull(message = "Enter your channel")
    private Integer channel_id;
    @NotBlank(message = "Enter your name ")
    private String name;
    @NotBlank(message = "Enter your description")
    private String description;
    private PlaylistStatus status;
    private Integer orderNum;
}

