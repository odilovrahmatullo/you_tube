package you_tube.Profile.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import you_tube.ExceptionHandler.AppBadException;
import you_tube.Profile.dto.CreateProfile;
import you_tube.Profile.dto.JwtDTO;
import you_tube.Profile.dto.ProfileDTO;
import you_tube.Profile.dto.UpdateProfileDTO;
import you_tube.Profile.enums.ProfileRole;
import you_tube.Profile.service.ProfileService;
import you_tube.Util.JwtUtil;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<ProfileDTO> addProfile(@RequestBody @Valid CreateProfile requestDTO,
                                                 @RequestHeader("Authorization") String token) {
        System.out.println(token);
        JwtDTO dto = JwtUtil.decode(token.substring(7));
        if (dto.getRole().equals(ProfileRole.ROLE_ADMIN)) {
            return ResponseEntity.status(201).body(profileService.create(requestDTO));
        } else {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping("/update/email")
    public ResponseEntity<?> UpdateEmail(@RequestBody String newEmail,
                                         @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(201).body(profileService.updateEmailConfirm(newEmail, token));
    }

    @GetMapping("/email/confirm/{id}")
    public ResponseEntity<?> updateEmailConfirm(@PathVariable Integer id) {
        return ResponseEntity.ok(profileService.UpdateEmail(id));
    }

    @PutMapping("/update/")
    public ResponseEntity<?> UpdateProfile(@RequestBody @Valid UpdateProfileDTO updateProfileDTO,
                                            @RequestHeader("Authorization") String token) {
        JwtDTO dto = JwtUtil.decode(token.substring(7));
        return ResponseEntity.ok(profileService.updateProfile(updateProfileDTO,dto.getEmail()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Integer id) {
        return ResponseEntity.ok(profileService.getById(id));
    }

    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
