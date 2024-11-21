package you_tube.you_tube.Profile.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.you_tube.ExceptionHandler.AppBadException;
import you_tube.you_tube.Profile.dto.RegistrationDTO;
import you_tube.you_tube.Profile.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/registration")
    public ResponseEntity<?> registration(@Valid @RequestBody RegistrationDTO registration) {
        return ResponseEntity.ok(authService.create(registration));
    }
    @GetMapping("/registration/confirm/{id}")
    public ResponseEntity<String> registration(@PathVariable Integer id){
        return ResponseEntity.ok(authService.registrationConfirm(id));
    }



    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
