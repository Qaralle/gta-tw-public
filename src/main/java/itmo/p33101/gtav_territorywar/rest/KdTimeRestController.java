package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.service.BanditService;
import itmo.p33101.gtav_territorywar.service.CaptureService;
import itmo.p33101.gtav_territorywar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/")
public class KdTimeRestController {
    private final CaptureService captureService;
    private final UserService userService;
    private final BanditService banditService;

    public KdTimeRestController(CaptureService captureService, UserService userService, BanditService banditService) {
        this.captureService = captureService;
        this.userService = userService;
        this.banditService = banditService;
    }

    @GetMapping("kd")
    public ResponseEntity getKdTime(){
        return ResponseEntity.ok(captureService.prepare(banditService.getById(userService.getCurrentUser().getBanditId())));
    }

}
