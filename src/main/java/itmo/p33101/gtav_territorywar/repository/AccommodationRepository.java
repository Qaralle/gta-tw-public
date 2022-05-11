package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.AccommodationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface AccommodationRepository  extends JpaRepository<AccommodationEntity, Integer> {
    @Query(nativeQuery = true, value = "SELECT get_time_in(:person)")
    Timestamp getLastTimeMoveByBandit(@Param("person")int bandit_id);

}
