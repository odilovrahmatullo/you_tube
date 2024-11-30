package you_tube.profile.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.exceptionhandler.AppBadException;
import you_tube.profile.dto.AuthDTO;
import you_tube.profile.dto.ProfileDTO;
import you_tube.profile.dto.RegistrationDTO;
import you_tube.profile.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authorization controller", description = "For users to be authorized")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    @Operation(summary = "Api for authorization", description = "This api for users to register")
    public ResponseEntity<?> registration(@Valid @RequestBody RegistrationDTO registration) {
        return ResponseEntity.ok(authService.create(registration));
    }

    @GetMapping("/registration/confirm/{id}")
    @Operation(summary = "Api for authorization", description = "This api user registration confirmation")
    public ResponseEntity<String> registration(@PathVariable Integer id){
        return ResponseEntity.ok(authService.registrationConfirm(id));
    }

    @PostMapping("/login")
    @Operation(summary = "Api for authorization", description = "This api used for users to log in to the system")
    public ResponseEntity<?> login(@Valid @RequestBody AuthDTO dto){
        ProfileDTO login = authService.login(dto);
        return ResponseEntity.ok().body(login);
    }

    @ExceptionHandler({AppBadException.class, IllegalArgumentException.class})
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}