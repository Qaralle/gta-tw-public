package itmo.p33101.gtav_territorywar.util.strategy;

import itmo.p33101.gtav_territorywar.model.GangEntity;
import itmo.p33101.gtav_territorywar.util.GangJsonView;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GangPrepareStrategy implements PrepareStrategy<GangEntity, GangJsonView> {
    @Override
    public Map<Object, GangJsonView> prepare(GangEntity a) {
        Map<Object, GangJsonView> response = new HashMap<>();
        GangJsonView view = new GangJsonView(a);
        response.put("data", view);
        return response;
    }
}
