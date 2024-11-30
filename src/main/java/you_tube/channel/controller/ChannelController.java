package you_tube.channel.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import you_tube.channel.dto.ChannelDTO;
import you_tube.channel.service.ChannelService;
import you_tube.channel.enums.ChannelStatus;
import you_tube.channel.dto.UpdateChannelDTO;


@RestController
@RequestMapping("/channel")
@Tag(name = "Channel controller", description = "Manage channel")
public class ChannelController {
    @Autowired
    private ChannelService channelService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Api for channel", description = "This api for channel creates video by user")
    public ResponseEntity<?> create(@Valid @RequestBody ChannelDTO dto) {
        return ResponseEntity.ok(channelService.create(dto));
    }

    @PutMapping("/update/photo/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    @Operation(summary = "Api for channel", description = "This api for channel updates image(id) by user, (channel_id) ")
    public ResponseEntity<?> updatePhoto(@PathVariable String id,
                                         @NotNull @RequestParam String photoId) {
            return ResponseEntity.ok(channelService.updatePhoto(id, photoId));

    }

    @PutMapping("/update/banner/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    @Operation(summary = "Api for channel", description = "This api for channel updates banner(id) by user, (channel_id)")
    public ResponseEntity<?> updateBanner(@PathVariable String id,
                                          @NotNull @RequestParam String bannerId) {
            return ResponseEntity.ok(channelService.updateBanner(id, bannerId));
    }

    @PutMapping("/update/chanel-info/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('OWNER')")
    @Operation(summary = "Api for channel", description = "This api for channel updates channel info by user, (channel_id)")
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
    @Operation(summary = "Api for channel", description = "Channel for user status: private and public")
    public ResponseEntity<?> changeStatus(@PathVariable String id,
                                          @RequestParam ChannelStatus status) {
        return ResponseEntity.ok(channelService.changeStatus(id,status));
    }

    @GetMapping("users-channel")
    @Operation(summary = "Api for channel", description = "User cChannel list")
    public ResponseEntity<?> getUsersChannelList(){
        return ResponseEntity.ok(channelService.getUsersChannel());
    }

}
