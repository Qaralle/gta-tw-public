package itmo.p33101.gtav_territorywar.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "CE_SEQUENCE_GENERATOR", sequenceName = "capture_id_seq", initialValue = 5, allocationSize = 1)
@Table(name = "capture", schema = "s283809", catalog = "studs")
public class CaptureEntity {
    private int id;
    private Timestamp time;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CE_SEQUENCE_GENERATOR")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CaptureEntity that = (CaptureEntity) o;
        return id == that.id &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time);
    }
}
