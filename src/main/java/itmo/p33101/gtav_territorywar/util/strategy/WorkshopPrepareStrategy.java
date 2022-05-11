package itmo.p33101.gtav_territorywar.util.strategy;

import itmo.p33101.gtav_territorywar.model.WorkshopEntity;
import itmo.p33101.gtav_territorywar.service.WorkshopService;
import itmo.p33101.gtav_territorywar.util.BlockJsonView;
import itmo.p33101.gtav_territorywar.util.WorkshopJsonView;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class WorkshopPrepareStrategy implements PrepareStrategy<WorkshopEntity, WorkshopJsonView>{

    private final WorkshopService workshopService;

    public WorkshopPrepareStrategy(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @Override
    public Map<Object, WorkshopJsonView> prepare(WorkshopEntity a) {
        Map<Object, WorkshopJsonView> response = new HashMap<>();
        WorkshopJsonView view = new WorkshopJsonView(a);
        response.put("data", view);
        return response;
    }
}
