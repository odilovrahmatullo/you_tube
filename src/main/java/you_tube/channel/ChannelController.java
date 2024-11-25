package you_tube.channel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.profile.dto.JwtDTO;
import you_tube.profile.enums.ProfileRole;
import you_tube.utils.JwtUtil;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ChannelDTO dto,
                                    @RequestHeader("Authorization") String token){
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_USER.name())) {
            return ResponseEntity.ok(channelService.create(dto,jwtDTO.getEmail()));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/update/photo/{id}")
    public ResponseEntity<?> updatePhoto(@PathVariable String id,
                                         @NotNull @RequestParam String photoId,
                                         @RequestHeader("Authorization") String token){
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_USER.name()) || jwtDTO.getRole().equals(ProfileRole.ROLE_OWNER.name())) {
            return ResponseEntity.ok(channelService.updatePhoto(id,photoId));
        } else {
            return ResponseEntity.status(403).build();
        }
    }
    @PutMapping("/update/banner/{id}")
    public ResponseEntity<?> updateBanner(@PathVariable String id,
                                          @NotNull  @RequestParam String bannerId,
                                          @RequestHeader("Authorization") String token){
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_USER.name()) || jwtDTO.getRole().equals(ProfileRole.ROLE_OWNER.name())) {
            return ResponseEntity.ok(channelService.updateBanner(id,bannerId));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/update/chanel-info/{id}")
    public ResponseEntity<?> updateChannel(@PathVariable String id,
                                           @RequestBody UpdateChannelDTO dto,
                                           @RequestHeader("Authorization") String token){
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_USER.name()) || jwtDTO.getRole().equals(ProfileRole.ROLE_OWNER.name())) {
            return ResponseEntity.ok(channelService.updateInfo(id,dto));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @GetMapping("/pagination")
    public ResponseEntity<?> getPagination(@RequestParam Integer page,
                                           @RequestParam Integer size,
                                           @RequestHeader("Authorization") String token){
        page = Math.max(page-1,0);
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_ADMIN.name())) {
            return ResponseEntity.ok(channelService.pagination(page,size));
        } else {
            return ResponseEntity.status(403).build();
        }
    }
}
