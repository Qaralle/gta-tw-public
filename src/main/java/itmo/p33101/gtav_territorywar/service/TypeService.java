package itmo.p33101.gtav_territorywar.service;

import itmo.p33101.gtav_territorywar.model.CharacteristicType;
import itmo.p33101.gtav_territorywar.model.ItemtypeEntity;

public interface TypeService {
    Integer getByCharacteristictype(String characteristicType);
    ItemtypeEntity getByIdBlyat(int id);
}
