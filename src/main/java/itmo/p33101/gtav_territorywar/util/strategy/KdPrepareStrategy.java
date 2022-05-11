package itmo.p33101.gtav_territorywar.util.strategy;

import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.repository.CaptureTryRepository;
import itmo.p33101.gtav_territorywar.repository.WorkRepository;
import itmo.p33101.gtav_territorywar.service.AccommodationService;
import itmo.p33101.gtav_territorywar.service.TimeService;
import itmo.p33101.gtav_territorywar.store.Store;
import itmo.p33101.gtav_territorywar.util.KdJsonView;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Component
public class KdPrepareStrategy  implements PrepareStrategy<BanditEntity, KdJsonView> {
    private final WorkRepository workRepository;
    private final AccommodationService accommodationService;
    private final TimeService timeService;
    private final CaptureTryRepository captureService;

    public KdPrepareStrategy(WorkRepository workRepository, AccommodationService accommodationService, TimeService timeService, CaptureTryRepository captureService, Store store) {
        this.workRepository = workRepository;
        this.accommodationService = accommodationService;
        this.timeService = timeService;
        this.captureService = captureService;
    }

    @Override
    public Map<Object, KdJsonView> prepare(BanditEntity a) {
        Map<Object, KdJsonView> response = new HashMap<>();
        KdJsonView view = new KdJsonView();

        Timestamp lastWork = workRepository.getLastAccessByBandit(a.getId());
        Timestamp lastMove = accommodationService.getLastTimeMoveByBandit(a.getId());
        Timestamp lastCapture = captureService.lastCaptureTryTime(a.getId());


        view.setKdCaptureTime(timeService.geKd(lastCapture));
        view.setKdMoveTime(timeService.geKd(lastMove));
        view.setKdWorkTime(timeService.geKd(lastWork));

        response.put("data",view);
        return response;
    }
}
