package itmo.p33101.gtav_territorywar.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "block", schema = "s283809", catalog = "studs")
public class BlockEntity {
    private int areaunitId;
    private int capacity;
    private boolean isrespawn;
    private AreaunitEntity areaunitByAreaunitId;
    private GangEntity gangByGangId;

    @Id
    @Column(name = "areaunit_id")
    public int getAreaunitId() {
        return areaunitId;
    }

    public void setAreaunitId(int areaunitId) {
        this.areaunitId = areaunitId;
    }

    @Basic
    @Column(name = "capacity")
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Basic
    @Column(name = "isrespawn")
    public boolean isIsrespawn() {
        return isrespawn;
    }

    public void setIsrespawn(boolean isrespawn) {
        this.isrespawn = isrespawn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlockEntity that = (BlockEntity) o;
        return areaunitId == that.areaunitId &&
                capacity == that.capacity &&
                isrespawn == that.isrespawn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(areaunitId, capacity, isrespawn);
    }

    @OneToOne
    @JoinColumn(name = "areaunit_id", referencedColumnName = "id", nullable = false)
    public AreaunitEntity getAreaunitByAreaunitId() {
        return areaunitByAreaunitId;
    }

    public void setAreaunitByAreaunitId(AreaunitEntity areaunitByAreaunitId) {
        this.areaunitByAreaunitId = areaunitByAreaunitId;
    }

    @ManyToOne
    @JoinColumn(name = "gang_id", referencedColumnName = "id")
    public GangEntity getGangByGangId() {
        return gangByGangId;
    }

    public void setGangByGangId(GangEntity gangByGangId) {
        this.gangByGangId = gangByGangId;
    }
}
