package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.controller.WebSocketNotificationsController;
import itmo.p33101.gtav_territorywar.dto.CaptureDto;
import itmo.p33101.gtav_territorywar.dto.IdDto;
import itmo.p33101.gtav_territorywar.dto.ProbabilityDto;
import itmo.p33101.gtav_territorywar.dto.TestDto;
import itmo.p33101.gtav_territorywar.model.UserEntity;
import itmo.p33101.gtav_territorywar.security.jwt.JwtTokenProvider;
import itmo.p33101.gtav_territorywar.service.*;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
public class TestEndRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final BlockService blockService;
    private final BanditService banditService;
    private final AreaUnitService areaUnitService;
    private final WorkshopService workshopService;
    private final GangService gangService;
    private final WebSocketNotificationsController controller;
    private final MapService mapService;

    public TestEndRestController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, BlockService blockService, BanditService banditService, AreaUnitService areaUnitService, WorkshopService workshopService, GangService gangService, WebSocketNotificationsController controller, MapService mapService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.blockService = blockService;
        this.banditService = banditService;
        this.areaUnitService = areaUnitService;
        this.workshopService = workshopService;
        this.gangService = gangService;
        this.controller = controller;
        this.mapService = mapService;
    }


    @PostMapping("test")
    public ResponseEntity check(@RequestBody TestDto req){
        UserEntity person = userService.getCurrentUser();
        return ResponseEntity.ok(banditService.prepare(banditService.getById(req.getId())));
    }

    @GetMapping("areaUnitById")
    public ResponseEntity getAreaUnit(@RequestBody IdDto req){
        return ResponseEntity.ok(areaUnitService.prepare(areaUnitService.findById(req.getId())));
    }

    @GetMapping("map")
    public ResponseEntity getUser(){
        return ResponseEntity.ok(mapService.prepare());
    }

    @GetMapping("gangs")
    public ResponseEntity getGangs(){
        return ResponseEntity.ok(gangService.prepare());
    }




    @SneakyThrows
    @GetMapping("nigga")
    public ResponseEntity nigga(){
        controller.map();
        return ResponseEntity.ok(true);
    }



//    @GetMapping("areaUnitByXY")
//    public ResponseEntity getAreaUnit(@RequestBody XYDto req){
//        int id = req.getX()+ req.getY()*8;
//        return ResponseEntity.ok();
//    }
}
