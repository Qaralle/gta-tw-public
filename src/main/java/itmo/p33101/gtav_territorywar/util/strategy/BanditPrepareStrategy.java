package itmo.p33101.gtav_territorywar.util.strategy;

import itmo.p33101.gtav_territorywar.model.AreaunitEntity;
import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.repository.WorkRepository;
import itmo.p33101.gtav_territorywar.service.*;
import itmo.p33101.gtav_territorywar.store.Store;
import itmo.p33101.gtav_territorywar.util.BanditJsonView;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Component
public class BanditPrepareStrategy implements PrepareStrategy<BanditEntity,BanditJsonView>{

    private final BlockService blockService;
    private final AreaUnitService areaUnitService;
    private final WorkRepository workRepository;
    private final AccommodationService accommodationService;
    private final BanditBlockService banditService;
    private final Store store;
    private final CaptureService captureService;
    private final TimeService timeService;

    public BanditPrepareStrategy(BlockService blockService, AreaUnitService areaUnitService, WorkRepository workRepository, AccommodationService accommodationService, BanditBlockService banditService, Store store, CaptureService captureService, TimeService timeService) {
        this.blockService = blockService;
        this.areaUnitService = areaUnitService;
        this.workRepository = workRepository;
        this.accommodationService = accommodationService;
        this.banditService = banditService;
        this.store = store;
        this.captureService = captureService;
        this.timeService = timeService;
    }

    @Override
    public Map<Object, BanditJsonView> prepare(BanditEntity a) {

        Map<Object, BanditJsonView> response = new HashMap<>();
        AreaunitEntity area =  areaUnitService.findById(blockService.getBanditBlock(a.getId()));
        BanditJsonView view = new BanditJsonView(a,area,store.getData().get(a.getId()));
        view.setCharacteristics(banditService.getAllCharacteristics(a.getId()));

        Timestamp lastWork = workRepository.getLastAccessByBandit(a.getId());
        Timestamp lastMove = accommodationService.getLastTimeMoveByBandit(a.getId());
        Timestamp lastCapture = captureService.getLastCaptureTryTime(a.getId());


//        view.setKdCaptureTime(timeService.geKd(lastCapture));
//        view.setKdMoveTime(timeService.geKd(lastMove));
//        view.setKdWorkTime(timeService.geKd(lastWork));

        response.put("data",view);
        return response;
    }
}
