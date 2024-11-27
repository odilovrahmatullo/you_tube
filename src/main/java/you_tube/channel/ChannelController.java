package you_tube.channel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> create(@Valid @RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.create(dto));
    }

    @PutMapping("/update/photo/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?> updatePhoto(@PathVariable String id,
                                         @NotNull @RequestParam String photoId) {
            return ResponseEntity.ok(channelService.updatePhoto(id, photoId));

    }

    @PutMapping("/update/banner/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    public ResponseEntity<?> updateBanner(@PathVariable String id,
                                          @NotNull @RequestParam String bannerId) {
            return ResponseEntity.ok(channelService.updateBanner(id, bannerId));
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
    public ResponseEntity<?> getUsersChannelList(){
        return ResponseEntity.ok(channelService.getUsersChannel());
    }

}
