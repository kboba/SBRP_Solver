package pl.kboba.sbrp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class City {

    @NonNull
    private List<BusStop> busStops;
    private Map<Integer, Boolean> visitedBusStops;
    @NonNull
    private List<Road> roads;


}
