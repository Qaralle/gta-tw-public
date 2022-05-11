package itmo.p33101.gtav_territorywar.service.impl;

import itmo.p33101.gtav_territorywar.service.TimeService;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;

@Service
public class TimeServiceImpl implements TimeService {
    @Override
    public Time geKd(Timestamp timestamp) {

        long datetime = System.currentTimeMillis();
        Timestamp currentTime = new Timestamp(datetime);

        if (timestamp == null){
            return Time.valueOf("00:00:00");
        }
        long milliseconds = currentTime.getTime() - timestamp.getTime();
        long max = 120000;

        if (max-milliseconds <=0){
            return Time.valueOf("00:00:00");
        } else {

            long secondsA = (max-milliseconds)/ 1000;
            long hours = secondsA / 3600;
            long minutes = (secondsA % 3600) / 60;
            long seconds = (secondsA % 3600) % 60;

            return new Time((int) hours,(int)minutes,(int)seconds);
        }

    }
}
