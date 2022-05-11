package itmo.p33101.gtav_territorywar.service;

import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.model.CharacteristicType;
import itmo.p33101.gtav_territorywar.model.GangEntity;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface BanditService {
    boolean runOver(int destination, int person);
    BanditEntity getById(int id);
    Map<Object, Object> prepare(BanditEntity bandit);
    BanditEntity register(String name, String surname, int age, int gang);
    boolean resetKnockDown(BanditEntity bandit);
    Integer getCharacteristic(int banditId, CharacteristicType characteristic);
    Map<String, Integer> getAllCharacteristics(int person);
}
