package itmo.p33101.gtav_territorywar.service;

import itmo.p33101.gtav_territorywar.model.ItemEntity;
import itmo.p33101.gtav_territorywar.model.ItemtypeEntity;
import itmo.p33101.gtav_territorywar.model.WorkEntity;

public interface ItemService {
    ItemEntity save(String name, int characteristic, ItemtypeEntity itemtype, WorkEntity work);
}
