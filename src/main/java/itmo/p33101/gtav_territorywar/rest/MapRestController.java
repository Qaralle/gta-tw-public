package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.service.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/app/")
public class MapRestController {
    private final MapService mapService;

    public MapRestController(MapService mapService) {
        this.mapService = mapService;
    }

    @GetMapping("map")
    public ResponseEntity getUser(){
        return ResponseEntity.ok(mapService.prepare());
    }

}
