package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.repository.CaptureTryRepository;
import itmo.p33101.gtav_territorywar.service.CaptureService;
import itmo.p33101.gtav_territorywar.util.strategy.JsonContext;
import itmo.p33101.gtav_territorywar.util.strategy.KdPrepareStrategy;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.sql.Timestamp;
import java.util.Map;

@Service
public class CaptureServiceImpl implements CaptureService {
    private final CaptureTryRepository captureTryRepository;
    private JsonContext context;
    private final KdPrepareStrategy kdPrepareStrategy;


    public CaptureServiceImpl(CaptureTryRepository captureTryRepository, KdPrepareStrategy kdPrepareStrategy) {
        this.captureTryRepository = captureTryRepository;
        this.kdPrepareStrategy = kdPrepareStrategy;
    }

    @PostConstruct
    public void __init__(){
        context = new JsonContext();
        context.setStrategy(kdPrepareStrategy);
    }

    @Override
    public Timestamp getLastCaptureTryTime(long banditId) {
        return captureTryRepository.lastCaptureTryTime(banditId);
    }

    @Override
    public Map<Object, Object> prepare(BanditEntity banditEntity) {
        return context.prepare(banditEntity);
    }
}
