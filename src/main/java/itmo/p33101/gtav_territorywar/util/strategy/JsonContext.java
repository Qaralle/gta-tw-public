package itmo.p33101.gtav_territorywar.util.strategy;

import java.util.Map;

public class JsonContext  {
    private PrepareStrategy strategy;

    public void setStrategy(PrepareStrategy strategy) {
        this.strategy = strategy;
    }

    public Map<Object, Object> prepare(Object a){
        return strategy.prepare(a);
    }
}
