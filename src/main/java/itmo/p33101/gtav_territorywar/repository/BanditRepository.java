package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.AreaunitEntity;
import itmo.p33101.gtav_territorywar.model.BanditEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BanditRepository extends JpaRepository<BanditEntity, Integer>  {
    BanditEntity findById(int id);

    @Query(nativeQuery = true, value = "SELECT run_over(:destination,:person)")
    boolean runOver(@Param("destination") int destination,@Param("person")  int b_id);

    @Query(nativeQuery = true, value = "SELECT count_characteristic_bandit(:place,:characteristic)")
    Integer getCharacteristic(@Param("place") int banditId, @Param("characteristic")String characteristicType);

    @Query(nativeQuery = true, value = "SELECT * FROM bandit")
    List<BanditEntity> getAll();
}
