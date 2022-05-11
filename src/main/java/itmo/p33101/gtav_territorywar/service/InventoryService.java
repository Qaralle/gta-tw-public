package itmo.p33101.gtav_territorywar.service;

import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.model.ItemEntity;

import java.util.List;

public interface InventoryService {
    void save(BanditEntity a, ItemEntity b);
    void saveList(BanditEntity a, List<ItemEntity> lb);
}
