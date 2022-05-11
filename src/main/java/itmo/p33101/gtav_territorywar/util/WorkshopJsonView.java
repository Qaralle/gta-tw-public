package itmo.p33101.gtav_territorywar.util;

import itmo.p33101.gtav_territorywar.model.WorkshopEntity;
import lombok.Data;

@Data
public class WorkshopJsonView {
    private String name;

    public WorkshopJsonView(WorkshopEntity entity){
        name = entity.getName();
    }
}
