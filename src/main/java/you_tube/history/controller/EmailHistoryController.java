package you_tube.history.controller;

;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import you_tube.exceptionhandler.AppBadException;
import you_tube.history.entity.EmailHistoryEntity;
import you_tube.history.service.EmailHistoryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/email-history")
public class EmailHistoryController {
    @Autowired
    EmailHistoryService emailHistoryService;

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getEmailHistoryService(@PathVariable String email) {
        List<EmailHistoryEntity> entity =  emailHistoryService.getByEmail(email);
        return ResponseEntity.ok(entity);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<?> getEmailHistoryService(@PathVariable LocalDate date) {
        return ResponseEntity.ok(emailHistoryService.getAllGiven(date));
    }

    @ExceptionHandler(AppBadException.class)
    public ResponseEntity<?> handle(AppBadException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
