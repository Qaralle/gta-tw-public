package itmo.p33101.gtav_territorywar.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class InventoryId implements Serializable {
    private BanditEntity banditByBanditId;
    private ItemEntity itemByItemId;
}