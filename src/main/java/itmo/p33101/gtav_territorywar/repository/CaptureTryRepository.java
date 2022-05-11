package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.BlockEntity;
import itmo.p33101.gtav_territorywar.model.CapturetryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Time;
import java.sql.Timestamp;

public interface CaptureTryRepository extends JpaRepository<CapturetryEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT max(time) from CaptureTry where Bandit_id = :bandit")
    Timestamp lastCaptureTryTime(@Param("bandit") long banditId);
}
