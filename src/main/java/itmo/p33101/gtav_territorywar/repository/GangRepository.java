package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.GangEntity;
import itmo.p33101.gtav_territorywar.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GangRepository extends JpaRepository<GangEntity, Integer> {
    List<GangEntity> getAllByIdIsNotNull();

    @Query(nativeQuery = true, value = "SELECT * FROM gang where id=(:id)")
    GangEntity getById(@Param("id") int id);
}
