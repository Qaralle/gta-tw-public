package itmo.p33101.gtav_territorywar.util.strategy;

import itmo.p33101.gtav_territorywar.model.AreaunitEntity;
import itmo.p33101.gtav_territorywar.util.AreaUnitJsonView;
import itmo.p33101.gtav_territorywar.util.MapJsonView;
import itmo.p33101.gtav_territorywar.util.WorkshopJsonView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MapPrepareStrategy implements PrepareStrategy<List<AreaunitEntity>, MapJsonView> {
    private final AreaUnitPrepareStrategy areaUnitPrepareStrategy;

    public MapPrepareStrategy(AreaUnitPrepareStrategy areaUnitPrepareStrategy) {
        this.areaUnitPrepareStrategy = areaUnitPrepareStrategy;
    }

    @Override
    public Map<Object, MapJsonView> prepare(List<AreaunitEntity> a) {

        List<AreaUnitJsonView> rawdata = new ArrayList<>();
        a.forEach(v->rawdata.add(areaUnitPrepareStrategy.prepare(v).get("data")));
        MapJsonView view = new MapJsonView(rawdata);
        Map<Object, MapJsonView> response = new HashMap<>();

        response.put("data",view);
        return response;
    }
}
