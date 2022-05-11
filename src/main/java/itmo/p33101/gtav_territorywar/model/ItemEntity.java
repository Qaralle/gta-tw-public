package itmo.p33101.gtav_territorywar.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@SequenceGenerator(name = "IE_SEQUENCE_GENERATOR", sequenceName = "item_id_seq", initialValue = 5, allocationSize = 1)
@Table(name = "item", schema = "s283809", catalog = "studs")
public class ItemEntity {
    private int id;
    private String name;
    private int characteristic;
    private ItemtypeEntity itemtypeByItemtypeId;
    private WorkEntity workByWorkId;

    public ItemEntity(String name, int characteristic, ItemtypeEntity itemtype, WorkEntity work){
        this.name = name;
        this.characteristic = characteristic;
        this.itemtypeByItemtypeId = itemtype;
        this.workByWorkId =work;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IE_SEQUENCE_GENERATOR")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "characteristic")
    public int getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(int characteristic) {
        this.characteristic = characteristic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemEntity that = (ItemEntity) o;
        return id == that.id &&
                characteristic == that.characteristic &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, characteristic);
    }

    @ManyToOne
    @JoinColumn(name = "itemtype_id", referencedColumnName = "id", nullable = false)
    public ItemtypeEntity getItemtypeByItemtypeId() {
        return itemtypeByItemtypeId;
    }

    public void setItemtypeByItemtypeId(ItemtypeEntity itemtypeByItemtypeId) {
        this.itemtypeByItemtypeId = itemtypeByItemtypeId;
    }

    @ManyToOne
    @JoinColumn(name = "work_id", referencedColumnName = "id")
    public WorkEntity getWorkByWorkId() {
        return workByWorkId;
    }

    public void setWorkByWorkId(WorkEntity workByWorkId) {
        this.workByWorkId = workByWorkId;
    }
}
