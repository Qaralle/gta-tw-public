package itmo.p33101.gtav_territorywar.scheduler;

import itmo.p33101.gtav_territorywar.controller.WebSocketNotificationsController;
import itmo.p33101.gtav_territorywar.service.BanditBlockService;
import itmo.p33101.gtav_territorywar.store.Store;
import lombok.SneakyThrows;

import java.sql.Time;

public class ScheduledJob extends Thread {

    private final BanditBlockService banditService;
    private final WebSocketNotificationsController controller;
    private int banditId;
    private final Store store;

    public ScheduledJob(BanditBlockService banditService, WebSocketNotificationsController controller, int banditId, Store store1) {
        this.controller = controller;
        this.store = store1;
        System.out.println("created");
        this.banditService = banditService;
        this.banditId = banditId;
    }


    @Override
    @SneakyThrows
    public void run() {
        System.out.println("started");

        Time timeRaw = store.getData().get(banditId);
        int time = timeRaw.getMinutes()*60 + timeRaw.getSeconds();
        for (int i = 0; i<time; ++i){

            timeRaw = store.getData().get(banditId);
            int second = timeRaw.getSeconds();
            int min = timeRaw.getMinutes();

            if (second == 0) {
                min--;
                second = 59;
            } else {
                second=second-1;
            }

            store.getData().put(banditId,new Time(0,min,second));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }

        banditService.resetKnockDown(banditService.getById(banditId));
        controller.characteristic();
    }
}
