package you_tube.channel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> create(@Valid @RequestBody ChannelDTO dto,
                                    @RequestHeader("Authorization") String token) {
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        return ResponseEntity.ok(channelService.create(dto, jwtDTO.getEmail()));
    }

    @PutMapping("/update/photo/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?> updatePhoto(@PathVariable String id,
                                         @NotNull @RequestParam String photoId,
                                         @RequestHeader("Authorization") String token) {
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_USER.name()) || jwtDTO.getRole().equals(ProfileRole.ROLE_OWNER.name())) {
            return ResponseEntity.ok(channelService.updatePhoto(id, photoId));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/update/banner/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?> updateBanner(@PathVariable String id,
                                          @NotNull @RequestParam String bannerId,
                                          @RequestHeader("Authorization") String token) {
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        if (jwtDTO.getRole().equals(ProfileRole.ROLE_USER.name()) || jwtDTO.getRole().equals(ProfileRole.ROLE_OWNER.name())) {
            return ResponseEntity.ok(channelService.updateBanner(id, bannerId));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/update/chanel-info/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?> updateChannel(@PathVariable String id,
                                           @RequestBody UpdateChannelDTO dto) {
            return ResponseEntity.ok(channelService.updateInfo(id, dto));
    }

    @GetMapping("/pagination")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getPagination(@RequestParam Integer page,
                                           @RequestParam Integer size) {
        page = Math.max(page - 1, 0);
            return ResponseEntity.ok(channelService.pagination(page, size));
    }

    @GetMapping("by/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ResponseEntity.ok(channelService.getById(id));
    }

    @PutMapping("change-status/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER') or hasRole('ADMIN')")
    public ResponseEntity<?> changeStatus(@PathVariable String id,
                                          @RequestParam ChannelStatus status) {
        return ResponseEntity.ok(channelService.changeStatus(id,status));
    }

    @GetMapping("users-channel")
    public ResponseEntity<?> getUsersChannelList(@RequestHeader("Authorization") String token){
        JwtDTO jwtDTO = JwtUtil.decode(token.substring(7));
        return ResponseEntity.ok(channelService.getUsersChannel(jwtDTO.getEmail()));
    }

}
