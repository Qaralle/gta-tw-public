package itmo.p33101.gtav_territorywar.util;

import itmo.p33101.gtav_territorywar.model.GangEntity;
import lombok.Data;

@Data
public class GangJsonView {
    private int id;
    private String color;
    private String name;

    public GangJsonView(GangEntity a){
        this.id = a.getId();
        this.color = a.getColor().name();
        this.name = a.getName();
    }
}
