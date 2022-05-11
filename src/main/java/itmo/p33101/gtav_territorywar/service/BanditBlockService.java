package itmo.p33101.gtav_territorywar.service;

import itmo.p33101.gtav_territorywar.model.BanditEntity;
import org.springframework.data.repository.query.Param;

import java.util.Map;

public interface BanditBlockService  {
    boolean resetKnockDown(BanditEntity bandit);
    BanditEntity getById(int id);
    Map<String, Integer> getAllCharacteristics(int person);
}
