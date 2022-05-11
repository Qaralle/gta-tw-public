package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.service.AreaUnitService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/app/")
public class AreaRestController {

    private final AreaUnitService areaUnitService;

    public AreaRestController(AreaUnitService areaUnitService) {
        this.areaUnitService = areaUnitService;
    }

//    @GetMapping("areaUnit")
//    public ResponseEntity getAreaUnit(@RequestBody IdDto req){
//        return ResponseEntity.ok(builderService.prepareSingleObjectInfo(areaUnitService.findById(req.getId())));
//    }
//
//    @GetMapping("areaUnit")
//    public ResponseEntity getAreaUnit(@RequestBody XYDto req){
//        int id = req.getX()+ req.getY()*8;
//        return ResponseEntity.ok(builderService.prepareSingleObjectInfo(areaUnitService.findById(id)));
//    }
}
