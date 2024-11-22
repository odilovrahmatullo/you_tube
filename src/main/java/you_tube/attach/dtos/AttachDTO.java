package you_tube.attach.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AttachDTO {
    @NotNull(message = "ID cannot be null")
    @Size(min = 36, max = 36, message = "ID must be a valid UUID")
    private String id;

    @NotNull(message = "Origin name cannot be null")
    @Size(min = 1, max = 255, message = "Origin name must be between 1 and 255 characters")
    private String originName;

    @NotNull(message = "Size cannot be null")
    @PositiveOrZero(message = "Size must be a positive number or zero")
    private Long size;

    @NotNull(message = "File extension cannot be null")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Extension must only contain alphanumeric characters")
    private String extension;

    @NotNull(message = "Creation date cannot be null")
    private LocalDateTime createdData;

    @NotNull(message = "URL cannot be null")
    @URL(message = "URL must be a valid URL")
    private String url;

    @NotNull(message = "Size cannot be null")
    @PositiveOrZero(message = "Size must be a positive number or zero")
    private long duration;


}
