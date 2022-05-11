package itmo.p33101.gtav_territorywar.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@SequenceGenerator(name = "BE_SEQUENCE_GENERATOR", sequenceName = "bandit_id_seq", initialValue = 5, allocationSize = 1)
@Table(name = "bandit", schema = "s283809", catalog = "studs")
public class BanditEntity {
    private int id;
    private String name;
    private String lastname;
    private Integer age;
    private Time isknockdown;
    private GangEntity gangByGangId;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BE_SEQUENCE_GENERATOR")
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
    @Column(name = "lastname")
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "isknockdown")
    public Time getIsknockdown() {
        return isknockdown;
    }

    public void setIsknockdown(Time isknockdown) {
        this.isknockdown = isknockdown;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BanditEntity that = (BanditEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(age, that.age) &&
                Objects.equals(isknockdown, that.isknockdown);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastname, age, isknockdown);
    }

    @ManyToOne
    @JoinColumn(name = "gang_id", referencedColumnName = "id", nullable = false)
    public GangEntity getGangByGangId() {
        return gangByGangId;
    }

    public void setGangByGangId(GangEntity gangByGangId) {
        this.gangByGangId = gangByGangId;
    }
}
