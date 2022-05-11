package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.model.InventoryEntity;
import itmo.p33101.gtav_territorywar.model.ItemEntity;
import itmo.p33101.gtav_territorywar.repository.InventoryRepository;
import itmo.p33101.gtav_territorywar.service.InventoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository repository;

    public InventoryServiceImpl(InventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(BanditEntity a, ItemEntity b) {
        repository.saveNormalWay(a.getId(),b.getId());
    }

    @Override
    public void saveList(BanditEntity a, List<ItemEntity> lb) {
        lb.forEach(i->save(a,i));
    }
}
