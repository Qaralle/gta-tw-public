package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.model.CharacteristicType;
import itmo.p33101.gtav_territorywar.repository.BanditRepository;
import itmo.p33101.gtav_territorywar.service.BanditBlockService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


//TODO: change logic between block and bandit
@Service
public class BanditBlockServiceImpl implements BanditBlockService {

    private final BanditRepository repository;

    public BanditBlockServiceImpl(BanditRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean resetKnockDown(BanditEntity bandit) {

        bandit.setIsknockdown(new Time(0, 0, 0));
        repository.save(bandit);
        return true;
    }
    @Override
    public BanditEntity getById(int id) {
        return repository.findById(id);
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
