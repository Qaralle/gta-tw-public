package itmo.p33101.gtav_territorywar.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "CTE_SEQUENCE_GENERATOR", sequenceName = "capturetry_id_seq", initialValue = 5, allocationSize = 1)
@Table(name = "capturetry", schema = "s283809", catalog = "studs")
public class CapturetryEntity {
    private int id;
    private Timestamp time;
    private BlockEntity blockByAttackId;
    private BlockEntity blockByDefenceId;
    private BanditEntity banditByBanditId;
    private CaptureEntity captureByCaptureId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CTE_SEQUENCE_GENERATOR")
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
        CapturetryEntity that = (CapturetryEntity) o;
        return id == that.id &&
                Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, time);
    }

    @ManyToOne
    @JoinColumn(name = "attack_id", referencedColumnName = "areaunit_id", nullable = false)
    public BlockEntity getBlockByAttackId() {
        return blockByAttackId;
    }

    public void setBlockByAttackId(BlockEntity blockByAttackId) {
        this.blockByAttackId = blockByAttackId;
    }

    @ManyToOne
    @JoinColumn(name = "defence_id", referencedColumnName = "areaunit_id", nullable = false)
    public BlockEntity getBlockByDefenceId() {
        return blockByDefenceId;
    }

    public void setBlockByDefenceId(BlockEntity blockByDefenceId) {
        this.blockByDefenceId = blockByDefenceId;
    }

    @ManyToOne
    @JoinColumn(name = "bandit_id", referencedColumnName = "id", nullable = false)
    public BanditEntity getBanditByBanditId() {
        return banditByBanditId;
    }

    public void setBanditByBanditId(BanditEntity banditByBanditId) {
        this.banditByBanditId = banditByBanditId;
    }

    @ManyToOne
    @JoinColumn(name = "capture_id", referencedColumnName = "id")
    public CaptureEntity getCaptureByCaptureId() {
        return captureByCaptureId;
    }

    public void setCaptureByCaptureId(CaptureEntity captureByCaptureId) {
        this.captureByCaptureId = captureByCaptureId;
    }
}
