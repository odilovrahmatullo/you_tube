package you_tube.you_tube.Profile.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.you_tube.Profile.dto.JwtDTO;
import you_tube.you_tube.Profile.dto.ProfileDTO;
import you_tube.you_tube.Profile.dto.UpdateProfileDetailDTO;
import you_tube.you_tube.Profile.enums.ProfileRole;
import you_tube.you_tube.Profile.service.ProfileService;
import you_tube.you_tube.Util.JwtUtil;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

//    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ProfileDTO> addProfile(@RequestBody @Valid ProfileDTO requestDTO,
                                                 @RequestHeader("Authorization") String token) {
        System.out.println(token);
        JwtDTO dto = JwtUtil.decode(token.substring(7));
        if (dto.getRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.status(201).body(profileService.create(requestDTO));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/")
    public ResponseEntity<?> getAllProfile(@RequestParam(value = "page", defaultValue = "1") int page,
                                           @RequestParam(value = "size", defaultValue = "10") int size,
                                           @RequestHeader("Authorization") String token) {
        JwtDTO dto = JwtUtil.decode(token.substring(7));
        if (dto.getRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.ok(profileService.profileAll(page - 1, size));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProfile(@PathVariable("id") Integer id,
                                           @RequestHeader("Authorization") String token) {
        JwtDTO dto = JwtUtil.decode(token.substring(7));
        if (dto.getRole().equals(ProfileRole.ADMIN)) {
            return ResponseEntity.ok().body(profileService.deleted(id));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/detail")
    public ResponseEntity<Boolean> updateDetail(@RequestBody @Valid UpdateProfileDetailDTO requestDTO) {
        return ResponseEntity.ok().body(profileService.updateDetail(requestDTO));
    }
}
