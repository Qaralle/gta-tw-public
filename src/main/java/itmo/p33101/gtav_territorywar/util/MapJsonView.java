package itmo.p33101.gtav_territorywar.util;

import lombok.Data;

import java.util.List;

@Data
public class MapJsonView {
    private List<AreaUnitJsonView> map;

    public MapJsonView(List<AreaUnitJsonView> map){
        this.map = map;
    }
}
