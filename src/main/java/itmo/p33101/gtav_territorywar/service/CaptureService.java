package itmo.p33101.gtav_territorywar.service;

import itmo.p33101.gtav_territorywar.model.BanditEntity;

import java.sql.Timestamp;
import java.util.Map;

public interface CaptureService {
    Timestamp getLastCaptureTryTime(long banditId);
    Map<Object, Object> prepare(BanditEntity banditEntity);
}
