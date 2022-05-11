package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.service.AreaUnitService;
import itmo.p33101.gtav_territorywar.service.MapService;
import itmo.p33101.gtav_territorywar.util.strategy.JsonContext;
import itmo.p33101.gtav_territorywar.util.strategy.MapPrepareStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Service
public class MapServiceImpl  implements MapService {

    private final MapPrepareStrategy strategy;
    private final AreaUnitService service;
    private JsonContext context;

    public MapServiceImpl(MapPrepareStrategy strategy, AreaUnitService service) {
        this.strategy = strategy;
        this.service = service;
    }

    @PostConstruct
    public void  __init__(){
        context = new JsonContext();
        context.setStrategy(strategy);
    }


    @Override
    public Map<Object, Object> prepare() {
        return context.prepare(service.getAll());
    }
}
