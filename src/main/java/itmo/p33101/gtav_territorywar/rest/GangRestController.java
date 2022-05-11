package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.service.GangService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/")
public class GangRestController {

    private final GangService gangService;

    public GangRestController(GangService gangService) {
        this.gangService = gangService;
    }


    @GetMapping("gangs")
    public ResponseEntity getGangs(){
        return ResponseEntity.ok(gangService.prepare());
    }

}
