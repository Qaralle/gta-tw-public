package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.BlockEntity;
import itmo.p33101.gtav_territorywar.model.CharacteristicType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Collector;

public interface BlockRepository extends JpaRepository<BlockEntity, Integer> {
    BlockEntity findByAreaunitId(Integer id);
    BlockEntity getById(int id);

    @Query(nativeQuery = true, value = "SELECT get_block_bandits_id(:place)")
    List<Integer> getAllbyBlock(@Param("place") int blockId);

    @Query(nativeQuery = true, value = "SELECT what_is_blockid_bandit(:bandit)")
    Integer getBanditBlock(@Param("bandit") int banditId);

    @Query(nativeQuery = true, value = "SELECT count_characteristic(:place,:characteristic)")
    Integer getCharacteristic(@Param("place") int blockId, @Param("characteristic")String characteristicType);

    @Query(nativeQuery = true, value = "SELECT count_probability(:attack,:defense)")
    Float count_probability(@Param("attack")int attackId, @Param("defense")int defenseId);

    @Query(nativeQuery = true, value = "SELECT capture_block(:defense,:person)")
    boolean block_capture(@Param("defense")int defenseId, @Param("person")int person);

    @Query(nativeQuery = true, value = "SELECT get_respawn(:gangId)")
    Integer getRespawn(@Param("gangId")int gang);
}
