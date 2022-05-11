package itmo.p33101.gtav_territorywar.util.factory;

public interface EntityFactory<T,D> {
    T create(D o);
}
