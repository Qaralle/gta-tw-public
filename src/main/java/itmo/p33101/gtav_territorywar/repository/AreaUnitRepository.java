package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.AreaunitEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaUnitRepository extends JpaRepository<AreaunitEntity, Integer> {
    AreaunitEntity findById(int id);
    List<AreaunitEntity> getAllByIdIsNotNull();
}
