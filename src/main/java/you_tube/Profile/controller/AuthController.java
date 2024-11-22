package you_tube.Profile.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.ExceptionHandler.AppBadException;
import you_tube.Profile.dto.AuthDTO;
import you_tube.Profile.dto.ProfileDTO;
import you_tube.Profile.dto.RegistrationDTO;
import you_tube.Profile.service.AuthService;

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
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDTO dto){
        ProfileDTO login = authService.login(dto);
        return ResponseEntity.ok().body(login);
    }




    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
