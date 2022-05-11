package itmo.p33101.gtav_territorywar.service;

import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.model.GangEntity;

import java.util.List;
import java.util.Map;

public interface GangService {
    List<GangEntity> getAll();
    Map<Object, Object> prepare();
    GangEntity getById(int id);
}
