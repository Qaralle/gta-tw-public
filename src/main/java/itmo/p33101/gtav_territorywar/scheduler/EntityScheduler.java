package itmo.p33101.gtav_territorywar.scheduler;

import itmo.p33101.gtav_territorywar.model.BanditEntity;

public interface EntityScheduler<T> {
    void createJob(T bandit);
}
