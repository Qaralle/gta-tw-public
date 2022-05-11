package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUsername(String name);
}
