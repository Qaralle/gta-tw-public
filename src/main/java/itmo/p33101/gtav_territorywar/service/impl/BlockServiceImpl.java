package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.BlockEntity;
import itmo.p33101.gtav_territorywar.model.CharacteristicType;
import itmo.p33101.gtav_territorywar.repository.BlockRepository;
import itmo.p33101.gtav_territorywar.scheduler.KnockDownScheduler;
import itmo.p33101.gtav_territorywar.service.BlockService;
import itmo.p33101.gtav_territorywar.store.Store;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.*;

@Service
public class BlockServiceImpl implements BlockService {

    private final BlockRepository repository;
    private final KnockDownScheduler scheduler;
    private final Store store;

    public BlockServiceImpl(BlockRepository repository, KnockDownScheduler scheduler, Store store) {
        this.repository = repository;
        this.scheduler = scheduler;
        this.store = store;
    }

    @Override
    public BlockEntity findByAreaUnitId(Integer id) {
        return repository.findByAreaunitId(id);
    }

    @Override
    public List<Integer> getAllbyBlock(int blockId) {
        return repository.getAllbyBlock(blockId);
    }

    @Override
    public Integer getBanditBlock(int banditId) {
        return repository.getBanditBlock(banditId);
    }

    @Override
    public Integer getCharacteristic(int block_id, CharacteristicType characteristic) {
        return repository.getCharacteristic(block_id,characteristic.toString());
    }

    @Override
    public Map<String, Integer> getAllCharacteristics(int block_id) {
        Map<String, Integer> allCharacteristics = new HashMap<>();
        Arrays.stream(CharacteristicType.values())
                .filter(v -> v!=CharacteristicType.HEAL)
                .forEach(value -> {
                    allCharacteristics.put(value.toString(),repository.getCharacteristic(block_id,value.toString()));
                });
        return allCharacteristics;
    }

    @Override
    public Float getProbability(int attack, int defense) {
        return repository.count_probability(attack,defense);
    }

    @Override
    public boolean block_capture(int defence, int person) {

        List<Integer> defense = repository.getAllbyBlock(defence);
        boolean result = repository.block_capture(defence, person);

        if (result){
            defense.forEach(scheduler::createJob);
        }
        return result;
    }

    @Override
    public Integer getRespawn(int gang) {
        return repository.getRespawn(gang);
    }


}
