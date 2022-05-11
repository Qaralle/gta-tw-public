package itmo.p33101.gtav_territorywar.scheduler;

import itmo.p33101.gtav_territorywar.controller.WebSocketNotificationsController;
import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.service.BanditBlockService;
import itmo.p33101.gtav_territorywar.store.Store;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;
import java.util.Timer;

@Component
public class KnockDownScheduler implements EntityScheduler<Integer>{
    private final BanditBlockService banditService;
    private final WebSocketNotificationsController controller;
    private final Store store;


    public KnockDownScheduler(BanditBlockService banditService, WebSocketNotificationsController controller, Store store) {
        this.banditService = banditService;
        this.controller = controller;
        this.store = store;
    }

    public void createJob(Integer banditId) {
        ScheduledJob scheduledJob = new ScheduledJob(banditService, controller, banditId,store);
        BanditEntity banditEntity = banditService.getById(banditId);
        Time knock = banditEntity.getIsknockdown();

        int second = knock.getSeconds();
        int min = knock.getMinutes();
        store.getData().put(banditId,new Time(0,min,second));

        if ((second+min*60)> 0){
            scheduledJob.start();
        }else {
            banditService.resetKnockDown(banditEntity);
        }
    }

}
