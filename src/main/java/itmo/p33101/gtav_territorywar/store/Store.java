package itmo.p33101.gtav_territorywar.store;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

@Data
@Service
public class Store {
    private final Map<Integer, Time> data = new HashMap<>();
}
