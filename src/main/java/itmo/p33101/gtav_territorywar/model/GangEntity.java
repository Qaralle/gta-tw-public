package itmo.p33101.gtav_territorywar.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "GE_SEQUENCE_GENERATOR", sequenceName = "gang_id_seq", initialValue = 5, allocationSize = 1)
@Table(name = "gang", schema = "s283809", catalog = "studs")
public class GangEntity {
    private int id;
    private String name;
    private Color color;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GE_SEQUENCE_GENERATOR")
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
    @Column(name = "color")
    @Enumerated(EnumType.STRING)
    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GangEntity that = (GangEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(color, that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }
}
