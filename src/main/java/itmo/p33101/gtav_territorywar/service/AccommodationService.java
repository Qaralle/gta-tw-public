package itmo.p33101.gtav_territorywar.service;

import itmo.p33101.gtav_territorywar.model.AccommodationEntity;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface AccommodationService {
    Timestamp getLastTimeMoveByBandit(int bandit_id);
    void save(AccommodationEntity a);
}
