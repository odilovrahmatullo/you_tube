package you_tube.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import you_tube.exceptionhandler.AppBadException;
import you_tube.profile.dto.CreateProfile;
import you_tube.profile.dto.JwtDTO;
import you_tube.profile.dto.ProfileDTO;
import you_tube.profile.dto.UpdateProfileDTO;
import you_tube.profile.enums.ProfileRole;
import you_tube.profile.service.ProfileService;
import you_tube.utils.JwtUtil;

@RestController
@RequestMapping("/api/profile")
@Tag(name = "Profile controller", description = "User`s info")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    @Operation(summary = "Api for profile", description = "This api to create a profile")
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
    @Operation(summary = "Api for profile", description = "This api to update email")
    public ResponseEntity<?> UpdateEmail(@RequestBody String newEmail,
                                         @RequestHeader("Authorization") String token) {
        return ResponseEntity.status(201).body(profileService.updateEmailConfirm(newEmail, token));
    }

    @GetMapping("/email/confirm/{id}")
    @Operation(summary = "Api for profile", description = "This api 'Please confirm the code sent to your email'.")
    public ResponseEntity<?> updateEmailConfirm(@PathVariable Integer id) {
        return ResponseEntity.ok(profileService.UpdateEmail(id));
    }

    @PutMapping("/update/")
    @Operation(summary = "Api for profile", description = "This api profile`s info update")
    public ResponseEntity<?> UpdateProfile(@RequestBody @Valid UpdateProfileDTO updateProfileDTO,
                                            @RequestHeader("Authorization") String token) {
        JwtDTO dto = JwtUtil.decode(token.substring(7));
        return ResponseEntity.ok(profileService.updateProfile(updateProfileDTO,dto.getEmail()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Api for profile", description = "This api find by profile, (profile_id)")
    public ResponseEntity<ProfileDTO> getProfile(@PathVariable Integer id) {
        return ResponseEntity.ok(profileService.getById(id));
    }

    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
