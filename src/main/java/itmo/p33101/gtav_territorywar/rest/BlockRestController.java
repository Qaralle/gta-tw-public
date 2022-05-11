package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.controller.WebSocketNotificationsController;
import itmo.p33101.gtav_territorywar.dto.CaptureDto;
import itmo.p33101.gtav_territorywar.service.BanditService;
import itmo.p33101.gtav_territorywar.service.BlockService;
import itmo.p33101.gtav_territorywar.service.UserService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/app/")
public class BlockRestController {
    private final BlockService blockService;
    private final UserService userService;
    private final WebSocketNotificationsController notifications;

    public BlockRestController(BlockService blockService, UserService userService, WebSocketNotificationsController notifications) {
        this.blockService = blockService;
        this.userService = userService;
        this.notifications = notifications;
    }


    @PostMapping("capture")
    @SneakyThrows
    public ResponseEntity capture(@RequestBody CaptureDto req){
        Map<Object, Object> response = new HashMap<>();
        boolean result = blockService.block_capture(req.getDefense_id(),userService.getCurrentUser().getBanditId());
        response.put("result",result);

        if (result){
            notifications.map();
        }

        notifications.characteristic();

        return ResponseEntity.ok(response);
    }
}
