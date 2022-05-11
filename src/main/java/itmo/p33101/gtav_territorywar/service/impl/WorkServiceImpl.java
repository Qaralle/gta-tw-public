package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.repository.WorkRepository;
import itmo.p33101.gtav_territorywar.service.WorkService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class WorkServiceImpl implements WorkService {
    private final WorkRepository repository;

    public WorkServiceImpl(WorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Timestamp getLastAccessByBandit(int bandit_id) {
        return repository.getLastAccessByBandit(bandit_id);
    }

    @Override
    public boolean workMed(int banditId) {
        return repository.work(banditId,1,"маленькая аптечка", 3);
    }
}
