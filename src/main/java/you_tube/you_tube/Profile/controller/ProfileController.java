package you_tube.you_tube.Profile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.you_tube.Profile.dto.ProfileDTO;
import you_tube.you_tube.Profile.service.ProfileService;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<?> createProfile(@RequestBody ProfileDTO profile,
                                           @RequestHeader("Authorization") String token) {


        return ResponseEntity.ok(profileService.create(profile));
    }
}
