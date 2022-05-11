package itmo.p33101.gtav_territorywar.service.impl;


import itmo.p33101.gtav_territorywar.model.GangEntity;
import itmo.p33101.gtav_territorywar.repository.GangRepository;
import itmo.p33101.gtav_territorywar.service.GangService;
import itmo.p33101.gtav_territorywar.util.strategy.GangListPrepareStrategy;
import itmo.p33101.gtav_territorywar.util.strategy.JsonContext;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Service
public class GangServiceImpl implements GangService {

    private JsonContext context;
    private final GangListPrepareStrategy gangListPrepareStrategy;
    private final GangRepository repository;

    public GangServiceImpl(GangListPrepareStrategy gangListPrepareStrategy, GangRepository repository) {
        this.gangListPrepareStrategy = gangListPrepareStrategy;
        this.repository = repository;
    }
    @PostConstruct
    public void __init__(){
        context = new JsonContext();
        context.setStrategy(gangListPrepareStrategy);
    }
    @Override
    public List<GangEntity> getAll() {
        return repository.getAllByIdIsNotNull();
    }

    @Override
    public Map<Object, Object> prepare() {
        List<GangEntity> a = getAll();
        return context.prepare(a);
    }

    @Override
    public GangEntity getById(int id) {
        return repository.getById(id);
    }
}
