package itmo.p33101.gtav_territorywar.util;

import itmo.p33101.gtav_territorywar.model.AreaunitEntity;
import itmo.p33101.gtav_territorywar.model.BlockEntity;
import itmo.p33101.gtav_territorywar.model.WorkshopEntity;
import lombok.Data;

@Data
public class AreaUnitJsonView {
    private int id;
    private BlockJsonView block;
    private WorkshopJsonView workshopEbtity;

    public AreaUnitJsonView(AreaunitEntity areaunitEntity, WorkshopJsonView workshopJsonView, BlockJsonView blockJsonView){
        id = areaunitEntity.getId();
        block = blockJsonView;
        workshopEbtity = workshopJsonView;
    }
}
