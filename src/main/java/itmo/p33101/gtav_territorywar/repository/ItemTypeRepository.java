package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.CharacteristicType;
import itmo.p33101.gtav_territorywar.model.ItemEntity;
import itmo.p33101.gtav_territorywar.model.ItemtypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemTypeRepository extends JpaRepository<ItemtypeEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT id FROM itemtype where characteristicType = (SELECT CAST(:type AS characteristicType))")
    Integer getByCharacteristictype(@Param("type") String characteristicType);

    @Query(nativeQuery = true, value = "SELECT * FROM itemtype where id = :id")
    ItemtypeEntity getByIdBlyat(@Param("id")int id);

}
