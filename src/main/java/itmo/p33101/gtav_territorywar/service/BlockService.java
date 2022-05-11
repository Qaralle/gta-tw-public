package itmo.p33101.gtav_territorywar.service;


import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.model.BlockEntity;
import itmo.p33101.gtav_territorywar.model.CharacteristicType;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;

public interface BlockService {
    BlockEntity findByAreaUnitId(Integer id);
    List<Integer> getAllbyBlock(int blockId);
    Integer getBanditBlock(int banditId);

    Integer getCharacteristic(int block_id, CharacteristicType characteristic);
    Map<String, Integer> getAllCharacteristics(int block_id);
    Float getProbability(int attack, int defense);
    boolean block_capture(int defence, int person);
    Integer getRespawn(int gang);
}
