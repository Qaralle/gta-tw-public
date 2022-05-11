package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.UserEntity;
import itmo.p33101.gtav_territorywar.model.WorkshopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Timestamp;

public interface WorkshopRepository extends JpaRepository<WorkshopEntity, Integer> {
    WorkshopEntity findById(int id);


}
