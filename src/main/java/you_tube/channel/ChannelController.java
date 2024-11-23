package you_tube.channel;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import you_tube.profile.dto.JwtDTO;
import you_tube.profile.enums.ProfileRole;
import you_tube.utils.JwtUtil;

@RestController
@RequestMapping("/controller")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    public ResponseEntity<?> create(@Valid @RequestBody ChannelDTO dto,
                                    @RequestHeader("Authorization") String token){
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_ADMIN.name())) {
            return ResponseEntity.ok(channelService.create(dto,jwtDTO.getEmail()));
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
