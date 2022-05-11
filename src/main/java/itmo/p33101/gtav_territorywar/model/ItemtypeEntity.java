package itmo.p33101.gtav_territorywar.model;

import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "ITE_SEQUENCE_GENERATOR", sequenceName = "item_itemtype_id_seq", initialValue = 5, allocationSize = 1)
@Table(name = "itemtype", schema = "s283809", catalog = "studs")
public class ItemtypeEntity {
    private int id;
    private CharacteristicType characteristictype;
    private String clazz;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITE_SEQUENCE_GENERATOR")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "characteristictype")
    public CharacteristicType getCharacteristictype() {
        return characteristictype;
    }

    public void setCharacteristictype(CharacteristicType characteristictype) {
        this.characteristictype = characteristictype;
    }

    @Basic
    @Column(name = "class")
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemtypeEntity that = (ItemtypeEntity) o;
        return id == that.id &&
                Objects.equals(characteristictype, that.characteristictype) &&
                Objects.equals(clazz, that.clazz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, characteristictype, clazz);
    }
}
