package itmo.p33101.gtav_territorywar.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "workshop", schema = "s283809", catalog = "studs")
public class WorkshopEntity {
    private int blockAreaunitId;
    private String name;
    private BlockEntity blockByBlockAreaunitId;

    @Id
    @Column(name = "block_areaunit_id")
    public int getBlockAreaunitId() {
        return blockAreaunitId;
    }

    public void setBlockAreaunitId(int blockAreaunitId) {
        this.blockAreaunitId = blockAreaunitId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkshopEntity that = (WorkshopEntity) o;
        return blockAreaunitId == that.blockAreaunitId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blockAreaunitId, name);
    }

    @OneToOne
    @JoinColumn(name = "block_areaunit_id", referencedColumnName = "areaunit_id", nullable = false)
    public BlockEntity getBlockByBlockAreaunitId() {
        return blockByBlockAreaunitId;
    }

    public void setBlockByBlockAreaunitId(BlockEntity blockByBlockAreaunitId) {
        this.blockByBlockAreaunitId = blockByBlockAreaunitId;
    }
}
