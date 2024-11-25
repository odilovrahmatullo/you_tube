package you_tube.channel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateChannelDTO {
    @NotBlank(message = "Name cannot be empty or null")
    @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
    private String name;

    @Size(max = 500, message = "Description must be up to 500 characters")
    private String description;

}
