package itmo.p33101.gtav_territorywar.util.strategy;

import java.util.Map;

public interface PrepareStrategy<T,V> {
    Map<Object, V> prepare(T a);
}
