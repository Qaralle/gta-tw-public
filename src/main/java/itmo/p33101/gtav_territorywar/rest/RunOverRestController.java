package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.controller.WebSocketNotificationsController;
import itmo.p33101.gtav_territorywar.dto.RunOverDto;
import itmo.p33101.gtav_territorywar.model.UserEntity;
import itmo.p33101.gtav_territorywar.repository.AreaUnitRepository;
import itmo.p33101.gtav_territorywar.service.BanditService;
import itmo.p33101.gtav_territorywar.service.UserService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/app/")
public class RunOverRestController {

    private final BanditService banditService;
    private final UserService userService;
    private final WebSocketNotificationsController notifications;

    public RunOverRestController(BanditService banditService, UserService userService,  WebSocketNotificationsController notifications) {
        this.banditService = banditService;
        this.userService = userService;
        this.notifications = notifications;
    }

    @SneakyThrows
    @PostMapping("run_over")
    public ResponseEntity runOver(@RequestBody RunOverDto req){

        UserEntity person = userService.getCurrentUser();
        Boolean result = banditService.runOver(req.getDestination(),person.getBanditId());

        System.out.println(result);

        Map<Object, Object> response = new HashMap<>();

        if (result){
            notifications.map();
            notifications.characteristic();
        }

        response.put("result",result);

        return ResponseEntity.ok(response);

    }
}
