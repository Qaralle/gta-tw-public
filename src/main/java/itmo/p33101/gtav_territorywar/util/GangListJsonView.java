package itmo.p33101.gtav_territorywar.util;

import lombok.Data;

import java.util.List;

@Data
public class GangListJsonView {
    private List<GangJsonView> gangs;

    public GangListJsonView(List<GangJsonView> gangs){
        this.gangs = gangs;
    }
}
