package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.WorkshopEntity;
import itmo.p33101.gtav_territorywar.repository.WorkshopRepository;
import itmo.p33101.gtav_territorywar.service.WorkshopService;
import org.springframework.stereotype.Service;

@Service
public class WorkshopServiceImpl implements WorkshopService {

    private final WorkshopRepository repository;

    public WorkshopServiceImpl(WorkshopRepository repository) {
        this.repository = repository;
    }

    @Override
    public WorkshopEntity findById(int id) {
        return repository.findById(id);
    }
}
