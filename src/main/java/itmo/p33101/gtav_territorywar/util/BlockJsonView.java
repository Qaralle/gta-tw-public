package itmo.p33101.gtav_territorywar.util;

import itmo.p33101.gtav_territorywar.model.AreaunitEntity;
import itmo.p33101.gtav_territorywar.model.BanditEntity;
import itmo.p33101.gtav_territorywar.model.BlockEntity;
import itmo.p33101.gtav_territorywar.model.GangEntity;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class BlockJsonView {
    private boolean isrespawn;
    private GangEntity gangByGangId;
    private List<Integer> bandits;
    private Map<String, Integer> characteristics;

    public BlockJsonView(BlockEntity blockEntity, List<Integer> bandits){
        isrespawn = blockEntity.isIsrespawn();
        gangByGangId = blockEntity.getGangByGangId();
        this.bandits = bandits;
    }

    public BlockJsonView(BlockEntity blockEntity, List<Integer> bandits, Map<String, Integer> characteristics){
        isrespawn = blockEntity.isIsrespawn();
        gangByGangId = blockEntity.getGangByGangId();
        this.bandits = bandits;
        this.characteristics = characteristics;
    }
}
