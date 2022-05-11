package itmo.p33101.gtav_territorywar.service;

import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;

public interface WorkService {
    Timestamp getLastAccessByBandit(int bandit_id);
    boolean workMed(int banditId);
}
