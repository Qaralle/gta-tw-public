package itmo.p33101.gtav_territorywar.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "WE_SEQUENCE_GENERATOR", sequenceName = "work_id_seq", initialValue = 5, allocationSize = 1)
@Table(name = "work", schema = "s283809", catalog = "studs")
public class WorkEntity {
    private int id;
    private Timestamp interactiontime;
    private BanditEntity banditByBanditId;
    private WorkshopEntity workshopByWorkshopBlockAreaunitId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WE_SEQUENCE_GENERATOR")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "interactiontime")
    public Timestamp getInteractiontime() {
        return interactiontime;
    }

    public void setInteractiontime(Timestamp interactiontime) {
        this.interactiontime = interactiontime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorkEntity that = (WorkEntity) o;
        return id == that.id &&
                Objects.equals(interactiontime, that.interactiontime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, interactiontime);
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
    @JoinColumn(name = "workshop_block_areaunit_id", referencedColumnName = "block_areaunit_id", nullable = false)
    public WorkshopEntity getWorkshopByWorkshopBlockAreaunitId() {
        return workshopByWorkshopBlockAreaunitId;
    }

    public void setWorkshopByWorkshopBlockAreaunitId(WorkshopEntity workshopByWorkshopBlockAreaunitId) {
        this.workshopByWorkshopBlockAreaunitId = workshopByWorkshopBlockAreaunitId;
    }
}
