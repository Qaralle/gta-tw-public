package itmo.p33101.gtav_territorywar.util.strategy;

import itmo.p33101.gtav_territorywar.model.AreaunitEntity;
import itmo.p33101.gtav_territorywar.model.WorkshopEntity;
import itmo.p33101.gtav_territorywar.service.BlockService;
import itmo.p33101.gtav_territorywar.service.WorkshopService;
import itmo.p33101.gtav_territorywar.util.AreaUnitJsonView;
import itmo.p33101.gtav_territorywar.util.BlockJsonView;
import itmo.p33101.gtav_territorywar.util.WorkshopJsonView;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class AreaUnitPrepareStrategy implements PrepareStrategy<AreaunitEntity, AreaUnitJsonView>{

    private final BlockService blockService;
    private final WorkshopService workshopService;
    private final BlockPrepareStrategy prepareStrategy;
    private final WorkshopPrepareStrategy workshopPrepareStrategy;

    public AreaUnitPrepareStrategy(BlockService blockService, WorkshopService workshopService, BlockPrepareStrategy prepareStrategy, WorkshopPrepareStrategy workshopPrepareStrategy) {
        this.blockService = blockService;
        this.workshopService = workshopService;
        this.prepareStrategy = prepareStrategy;
        this.workshopPrepareStrategy = workshopPrepareStrategy;
    }


    @Override
    public Map<Object, AreaUnitJsonView> prepare(AreaunitEntity a) {
        Map<Object, AreaUnitJsonView> response = new HashMap<>();
        WorkshopJsonView workshop = null;

        BlockJsonView block = prepareStrategy.prepare(blockService.findByAreaUnitId(a.getId())).get("data");
        WorkshopEntity ent = workshopService.findById(a.getId());
        if (ent != null) {
            workshop = workshopPrepareStrategy.prepare(ent).get("data");
        }

        AreaUnitJsonView view = new AreaUnitJsonView(a,workshop,block);
        response.put("data",view);
        return response;
    }
}
