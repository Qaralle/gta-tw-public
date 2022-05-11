package itmo.p33101.gtav_territorywar.util;

import lombok.Data;

import java.sql.Time;

@Data
public class KdJsonView {
    private Time kdWorkTime;
    private Time kdCaptureTime;
    private Time kdMoveTime;
}
