package itmo.p33101.gtav_territorywar.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Table(name = "user", schema = "s283809", catalog = "studs")
public class UserEntity {
    private int banditId;
    private String username;
    private String password;
    private String role;
    private String refreshToken;
    private String status;
    private Timestamp lastAccessTime;
    private BanditEntity banditByBanditId;


    public UserEntity(String username, String password){
        this.username = username;
        this.password = password;
        this.lastAccessTime = new Timestamp(System.currentTimeMillis());
    }


    @Id
    @Column(name = "bandit_id")
    public int getBanditId() {
        return banditId;
    }

    public void setBanditId(int banditId) {
        this.banditId = banditId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "role")
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Basic
    @Column(name = "refresh_token")
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "lastaccesstime")
    public Timestamp getLastAccessTime() {
        return lastAccessTime;
    }

    public void setLastAccessTime(Timestamp lastaccesstime) {
        this.lastAccessTime = lastaccesstime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return banditId == that.banditId &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(role, that.role) &&
                Objects.equals(refreshToken, that.refreshToken) &&
                Objects.equals(status, that.status) &&
                Objects.equals(lastAccessTime, that.lastAccessTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banditId, username, password, role, refreshToken, status, lastAccessTime);
    }

    @OneToOne
    @JoinColumn(name = "bandit_id", referencedColumnName = "id", nullable = false)
    public BanditEntity getBanditByBanditId() {
        return banditByBanditId;
    }

    public void setBanditByBanditId(BanditEntity banditByBanditId) {
        this.banditByBanditId = banditByBanditId;
    }
}
