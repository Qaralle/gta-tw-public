package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.CharacteristicType;
import itmo.p33101.gtav_territorywar.model.ItemtypeEntity;
import itmo.p33101.gtav_territorywar.repository.ItemTypeRepository;
import itmo.p33101.gtav_territorywar.service.TypeService;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    private final ItemTypeRepository repository;

    public TypeServiceImpl(ItemTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public Integer getByCharacteristictype(String characteristicType) {
        return repository.getByCharacteristictype(characteristicType);
    }

    @Override
    public ItemtypeEntity getByIdBlyat(int id) {
        return repository.getByIdBlyat(id);
    }
}
