package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.AreaunitEntity;
import itmo.p33101.gtav_territorywar.repository.AreaUnitRepository;
import itmo.p33101.gtav_territorywar.service.AreaUnitService;
import itmo.p33101.gtav_territorywar.util.strategy.AreaUnitPrepareStrategy;
import itmo.p33101.gtav_territorywar.util.strategy.JsonContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class AreaUnitServiceImpl implements AreaUnitService {

    private final AreaUnitRepository repository;
    private final AreaUnitPrepareStrategy strategy;
    private JsonContext context;

    public AreaUnitServiceImpl(AreaUnitRepository repository, AreaUnitPrepareStrategy strategy) {
        this.repository = repository;
        this.strategy = strategy;
    }

    @PostConstruct
    public void __init__(){
        context = new JsonContext();
        context.setStrategy(strategy);
    }

    @Override
    public AreaunitEntity findById(int id) {
        return repository.findById(id);
    }

    @Override
    public List<AreaunitEntity> getAll() {
        return repository.getAllByIdIsNotNull();
    }

    @Override
    public Map<Object, Object> prepare(AreaunitEntity area) {
        return context.prepare(area);
    }
}
