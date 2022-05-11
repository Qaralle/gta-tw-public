package itmo.p33101.gtav_territorywar.util.strategy;

import itmo.p33101.gtav_territorywar.model.BlockEntity;
import itmo.p33101.gtav_territorywar.service.BanditService;
import itmo.p33101.gtav_territorywar.service.BlockService;
import itmo.p33101.gtav_territorywar.util.BlockJsonView;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BlockPrepareStrategy implements PrepareStrategy<BlockEntity, BlockJsonView> {

    private final BlockService blockService;

    public BlockPrepareStrategy(BlockService blockService) {
        this.blockService = blockService;
    }

    @Override
    public Map<Object, BlockJsonView> prepare(BlockEntity a) {
        Map<Object, BlockJsonView> response = new HashMap<>();
        BlockJsonView view = new BlockJsonView(a,blockService.getAllbyBlock(a.getAreaunitId()),
                blockService.getAllCharacteristics(a.getAreaunitId()));

        response.put("data",view);
        return response;
    }
}
