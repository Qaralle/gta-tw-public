package itmo.p33101.gtav_territorywar.rest;

import itmo.p33101.gtav_territorywar.dto.ProbabilityDto;
import itmo.p33101.gtav_territorywar.service.BlockService;
import itmo.p33101.gtav_territorywar.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/app/")
public class ProbRestController {
    private final BlockService blockService;
    private final UserService userService;

    public ProbRestController(BlockService blockService, UserService userService) {
        this.blockService = blockService;
        this.userService = userService;
    }

    @GetMapping("count_prob/{req}")
    public ResponseEntity getProb(@PathVariable Integer req){
        Map<Object, Object> response = new HashMap<>();
        response.put("data",blockService.getProbability(blockService.getBanditBlock(userService.getCurrentUser().getBanditId()),req));
        return ResponseEntity.ok(response);
    }

}
