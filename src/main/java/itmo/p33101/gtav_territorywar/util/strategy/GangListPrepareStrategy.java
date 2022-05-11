package itmo.p33101.gtav_territorywar.util.strategy;

import itmo.p33101.gtav_territorywar.model.GangEntity;
import itmo.p33101.gtav_territorywar.util.GangJsonView;
import itmo.p33101.gtav_territorywar.util.GangListJsonView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class GangListPrepareStrategy implements PrepareStrategy<List<GangEntity>, GangListJsonView> {

    private final GangPrepareStrategy prepareStrategy;

    public GangListPrepareStrategy(GangPrepareStrategy prepareStrategy) {
        this.prepareStrategy = prepareStrategy;
    }

    @Override
    public Map<Object, GangListJsonView> prepare(List<GangEntity> a) {
        Map<Object, GangListJsonView> response = new HashMap<>();
        List<GangJsonView> rawView = new ArrayList<>();
        a.forEach(value -> rawView.add(prepareStrategy.prepare(value).get("data")));

        GangListJsonView view = new GangListJsonView(rawView);
        response.put("data", view);
        return response;
    }
}
