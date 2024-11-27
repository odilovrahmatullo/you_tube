package you_tube.playlist.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateDTO {
    private String name;
    private String description;
    private Integer orderNum;
}
