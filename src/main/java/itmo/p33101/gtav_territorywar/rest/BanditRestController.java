package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.dto.IdDto;
import itmo.p33101.gtav_territorywar.service.AreaUnitService;
import itmo.p33101.gtav_territorywar.service.BanditService;
import itmo.p33101.gtav_territorywar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/")
public class BanditRestController {
    private final BanditService banditService;
    private final UserService userService;

    public BanditRestController(BanditService banditService, UserService userService) {
        this.banditService = banditService;
        this.userService = userService;
    }

    @GetMapping("bandit")
    public ResponseEntity getBandit(@RequestBody IdDto req) {
        return ResponseEntity.ok(banditService.prepare(banditService.getById(req.getId())));
    }

    @GetMapping("you")
    public ResponseEntity getBandit() {
        int id = userService.getCurrentUser().getBanditId();
        return ResponseEntity.ok(banditService.prepare(banditService.getById(id)));
    }
}
