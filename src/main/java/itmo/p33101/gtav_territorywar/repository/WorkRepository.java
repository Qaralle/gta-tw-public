package itmo.p33101.gtav_territorywar.repository;

import itmo.p33101.gtav_territorywar.model.WorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;


public interface WorkRepository extends JpaRepository<WorkEntity, Integer> {

    @Query(nativeQuery = true, value = "SELECT get_last_time(:worker)")
    Timestamp getLastAccessByBandit(@Param("worker")int bandit_id);

    @Query(nativeQuery = true, value = "SELECT work(:worker,:type_of_product,:name,:characteristic)")
    boolean work(@Param("worker")int bandit_id,@Param("type_of_product")int itemtype, @Param("name") String name, @Param("characteristic") int characteristic );


}
