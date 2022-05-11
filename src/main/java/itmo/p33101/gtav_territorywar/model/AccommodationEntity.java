package itmo.p33101.gtav_territorywar.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name = "accommodation", schema = "s283809", catalog = "studs")
@SequenceGenerator(name = "AE_SEQUENCE_GENERATOR", sequenceName = "accommodation_id_seq", initialValue = 5, allocationSize = 1)
public class AccommodationEntity {
    private int id;
    private Timestamp checkintime;
    private Timestamp checkouttime;
    private BlockEntity blockByBlockAreaunitId;
    private BanditEntity banditByBanditId;

    public AccommodationEntity(Timestamp checkintime,BlockEntity block,BanditEntity bandit){
        this.checkintime = checkintime;
        blockByBlockAreaunitId = block;
        banditByBanditId = bandit;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AE_SEQUENCE_GENERATOR")
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "checkintime")
    public Timestamp getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(Timestamp checkintime) {
        this.checkintime = checkintime;
    }

    @Basic
    @Column(name = "checkouttime")
    public Timestamp getCheckouttime() {
        return checkouttime;
    }

    public void setCheckouttime(Timestamp checkouttime) {
        this.checkouttime = checkouttime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccommodationEntity that = (AccommodationEntity) o;
        return id == that.id &&
                Objects.equals(checkintime, that.checkintime) &&
                Objects.equals(checkouttime, that.checkouttime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, checkintime, checkouttime);
    }

    @ManyToOne
    @JoinColumn(name = "block_areaunit_id", referencedColumnName = "areaunit_id", nullable = false)
    public BlockEntity getBlockByBlockAreaunitId() {
        return blockByBlockAreaunitId;
    }

    public void setBlockByBlockAreaunitId(BlockEntity blockByBlockAreaunitId) {
        this.blockByBlockAreaunitId = blockByBlockAreaunitId;
    }

    @ManyToOne
    @JoinColumn(name = "bandit_id", referencedColumnName = "id", nullable = false)
    public BanditEntity getBanditByBanditId() {
        return banditByBanditId;
    }

    public void setBanditByBanditId(BanditEntity banditByBanditId) {
        this.banditByBanditId = banditByBanditId;
    }
}
