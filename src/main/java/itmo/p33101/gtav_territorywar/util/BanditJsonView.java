package itmo.p33101.gtav_territorywar.util;

import itmo.p33101.gtav_territorywar.model.AreaunitEntity;
import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.model.BlockEntity;
import itmo.p33101.gtav_territorywar.model.GangEntity;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Map;

@Data
public class BanditJsonView {
    private int id;
    private String name;
    private String lastname;
    private Integer age;
    private Time isknockdown;
    private GangEntity gangByGangId;
    private AreaunitEntity areaunitEntity;
    private Map<String, Integer> characteristics;
    private Time deathTime;

    public BanditJsonView(BanditEntity banditEntity, AreaunitEntity areaunitEntity, Time deathTime){
        id=banditEntity.getId();
        name = banditEntity.getName();
        lastname = banditEntity.getLastname();
        age = banditEntity.getAge();
        isknockdown = banditEntity.getIsknockdown();
        gangByGangId = banditEntity.getGangByGangId();
        this.areaunitEntity = areaunitEntity;
        this.deathTime = deathTime;
    }
}
