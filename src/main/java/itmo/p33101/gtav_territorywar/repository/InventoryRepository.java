package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.InventoryEntity;
import itmo.p33101.gtav_territorywar.model.InventoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InventoryRepository extends JpaRepository<InventoryEntity, InventoryId> {
    @Query(nativeQuery = true, value = "SELECT inventary_add(:bi,:ii)")
    boolean saveNormalWay(@Param("bi") int bandit, @Param("ii")int item );
}
