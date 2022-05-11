package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.controller.WebSocketNotificationsController;
import itmo.p33101.gtav_territorywar.service.UserService;
import itmo.p33101.gtav_territorywar.service.WorkService;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/app/")
public class WorkRestController {

    private final WorkService workService;
    private final UserService userService;
    private final WebSocketNotificationsController notifications;

    public WorkRestController(WorkService workService, UserService userService, WebSocketNotificationsController notifications) {
        this.workService = workService;
        this.userService = userService;
        this.notifications = notifications;
    }

    @PostMapping("work")
    @SneakyThrows
    public ResponseEntity check(){
        Map<Object, Object> response = new HashMap<>();
        boolean result = workService.workMed(userService.getCurrentUser().getBanditId());
        response.put("result", result);

        if (result) {notifications.characteristic();}
        return ResponseEntity.ok(response);
    }
}
