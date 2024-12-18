package you_tube.subscription;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subscribe")
public class SubscriptionController {
    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SubscriptionDTO> create(@RequestBody SubscriptionDTO dto){
        return ResponseEntity.ok(subscriptionService.create(dto));
    }

    @PutMapping("/status")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ChangeSubscriptionStatusDTO> changeStatus(@RequestBody ChangeSubscriptionStatusDTO dto){
        return ResponseEntity.ok(subscriptionService.changeStatus(dto));
    }


    @PutMapping("/notification")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<SubscriptionDTO> changeNotificationType(@RequestBody SubscriptionDTO dto){
        return ResponseEntity.ok(subscriptionService.changeNotificationType(dto));
    }

    @GetMapping("/list")
    public ResponseEntity<List<SubscriptionInfo>>getUserSubscriptionList() {
        return ResponseEntity.ok(subscriptionService.getUserSubscriptionList());
    }

}
