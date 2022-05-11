package itmo.p33101.gtav_territorywar.service;

import itmo.p33101.gtav_territorywar.model.AreaunitEntity;
import itmo.p33101.gtav_territorywar.model.BanditEntity;

import java.util.List;
import java.util.Map;

public interface AreaUnitService {
    AreaunitEntity findById(int id);
    List<AreaunitEntity> getAll();
    Map<Object, Object> prepare(AreaunitEntity area);
}
