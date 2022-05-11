package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.*;
import itmo.p33101.gtav_territorywar.repository.BanditRepository;
import itmo.p33101.gtav_territorywar.service.*;
import itmo.p33101.gtav_territorywar.store.Store;
import itmo.p33101.gtav_territorywar.util.strategy.BanditPrepareStrategy;
import itmo.p33101.gtav_territorywar.util.strategy.JsonContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.*;

@Service
public class BanditServiceImpl implements BanditService {
    private final BanditRepository repository;
    private final BanditPrepareStrategy strategy;
    private final BlockService blockService;
    private JsonContext context;
    private final AccommodationService accommodationService;
    private final ItemService itemService;
    private final InventoryService inventoryService;
    private final TypeService itemTypeService;
    private final GangService gangService;
    private final Store store;

    public BanditServiceImpl(BanditRepository repository, BanditPrepareStrategy strategy, BlockService blockService, AccommodationService accommodationService, ItemService itemService, InventoryService inventoryService, TypeService itemTypeService, GangService gangService, Store store) {
        this.repository = repository;
        this.strategy = strategy;
        this.blockService = blockService;
        this.accommodationService = accommodationService;
        this.itemService = itemService;
        this.inventoryService = inventoryService;
        this.itemTypeService = itemTypeService;
        this.gangService = gangService;
        this.store = store;
    }

    @PostConstruct
    public void __init__(){
        context = new JsonContext();
        context.setStrategy(strategy);
        repository.getAll().forEach(i->store.getData().put(i.getId(),i.getIsknockdown()));
    }

    @Override
    public boolean runOver(int destination, int person) {
        return repository.runOver(destination,person);
    }



    @Override
    public BanditEntity getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Map<Object, Object> prepare(BanditEntity bandit) {
        return context.prepare(bandit);
    }

    @Override
    @Transactional
    public BanditEntity register(String name, String surname, int age, int gang) {
        BanditEntity newOne = new BanditEntity();
        newOne.setName(name);
        newOne.setLastname(surname);
        newOne.setAge(age);
        newOne.setGangByGangId(gangService.getById(gang));
        newOne.setIsknockdown(new Time(0, 0, 0));
        repository.save(newOne);

        int block_id = blockService.getRespawn(gang);
        BlockEntity block  =  blockService.findByAreaUnitId(block_id);
        accommodationService.save(new AccommodationEntity(new Timestamp(System.currentTimeMillis()),
                block,
                newOne));

        List<ItemEntity> starter_pack = new ArrayList<>();

        int id = itemTypeService.getByCharacteristictype(CharacteristicType.HEAL.name());
        ItemtypeEntity a = itemTypeService.getByIdBlyat( id );

        ItemEntity heal = itemService.save("стартовая аптечка", 2, a, null);
        ItemEntity dmg = itemService.save("стартовая винтовка", 2, itemTypeService.getByIdBlyat( itemTypeService.getByCharacteristictype(CharacteristicType.DAMAGE.name())), null);
        ItemEntity prtc = itemService.save("велосипед", 1, itemTypeService.getByIdBlyat( itemTypeService.getByCharacteristictype(CharacteristicType.PROTECT.name())), null);

        starter_pack.add(heal);
        starter_pack.add(dmg);
        starter_pack.add(prtc);

        inventoryService.saveList(newOne,starter_pack);
        return newOne;
    }

    @Override
    public boolean resetKnockDown(BanditEntity bandit) {
        bandit.setIsknockdown(new Time(0, 0, 0));
        store.getData().put(bandit.getId(),new Time(0, 0, 0));

        repository.save(bandit);
        return true;
    }

    @Override
    public Integer getCharacteristic(int banditId, CharacteristicType characteristic) {
        return repository.getCharacteristic(banditId,characteristic.toString());
    }

    @Override
    public Map<String, Integer> getAllCharacteristics(int person) {
        Map<String, Integer> allCharacteristics = new HashMap<>();
        Arrays.stream(CharacteristicType.values())
                .forEach(value -> {
                    allCharacteristics.put(value.toString(),repository.getCharacteristic(person,value.toString()));
                });
        return allCharacteristics;
    }

}
