package itmo.p33101.gtav_territorywar.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Table(name = "inventory", schema = "s283809", catalog = "studs")
@IdClass(InventoryId.class)
public class InventoryEntity implements Serializable {
    private BanditEntity banditByBanditId;
    private ItemEntity itemByItemId;

    public InventoryEntity(BanditEntity banditByBanditId, ItemEntity itemByItemId){
        this.banditByBanditId=banditByBanditId;
        this.itemByItemId = itemByItemId;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "bandit_id", referencedColumnName = "id", nullable = false)
    public BanditEntity getBanditByBanditId() {
        return banditByBanditId;
    }

    public void setBanditByBanditId(BanditEntity banditByBanditId) {
        this.banditByBanditId = banditByBanditId;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "item_id", referencedColumnName = "id", nullable = false)
    public ItemEntity getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(ItemEntity itemByItemId) {
        this.itemByItemId = itemByItemId;
    }
}
