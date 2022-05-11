package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.AccommodationEntity;
import itmo.p33101.gtav_territorywar.repository.AccommodationRepository;
import itmo.p33101.gtav_territorywar.service.AccommodationService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public Timestamp getLastTimeMoveByBandit(int bandit_id) {
        return accommodationRepository.getLastTimeMoveByBandit(bandit_id);
    }

    @Override
    public void save(AccommodationEntity a) {
        accommodationRepository.save(a);
    }


}
