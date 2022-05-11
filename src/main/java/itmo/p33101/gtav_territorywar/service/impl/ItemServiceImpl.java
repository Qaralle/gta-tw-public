package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.ItemEntity;
import itmo.p33101.gtav_territorywar.model.ItemtypeEntity;
import itmo.p33101.gtav_territorywar.model.WorkEntity;
import itmo.p33101.gtav_territorywar.repository.ItemRepository;
import itmo.p33101.gtav_territorywar.service.ItemService;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository repository;

    public ItemServiceImpl(ItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public ItemEntity save(String name, int characteristic, ItemtypeEntity itemtype, WorkEntity work) {
        ItemEntity a = new ItemEntity(name, characteristic, itemtype, work);
        repository.save(a);
        return a;
    }
}
