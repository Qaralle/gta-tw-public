package itmo.p33101.gtav_territorywar.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "AUE_SEQUENCE_GENERATOR", sequenceName = "areaunit_id_seq", initialValue = 5, allocationSize = 1)
@Table(name = "areaunit", schema = "s283809", catalog = "studs")
public class AreaunitEntity {
    private int id;
    private int x;
    private int y;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUE_SEQUENCE_GENERATOR")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "x")
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Basic
    @Column(name = "y")
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaunitEntity that = (AreaunitEntity) o;
        return id == that.id &&
                x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y);
    }
}
