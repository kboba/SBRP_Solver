package pl.kboba.sbrp.model.initializers;

import pl.kboba.sbrp.model.BusStop;

import java.util.ArrayList;
import java.util.List;

public final class BusStopsInitializer {
    private BusStopsInitializer(){

    }

    public static List<BusStop> testingBusStopsInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(100, 40, 10, true));
        busStops.add(new BusStop(2, 10, 15));
        busStops.add(new BusStop(3, 25, 10));
        busStops.add(new BusStop(4, 25, 20));
        busStops.add(new BusStop(5, 40, 20));
        return busStops;
    }

    public static List<BusStop> basicBusStopsInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(0, 10, 10));
        busStops.add(new BusStop(1, 10, 20));
        busStops.add(new BusStop(2, 20, 10));
        busStops.add(new BusStop(3, 20, 20));
        busStops.add(new BusStop(4, 30, 5));
        busStops.add(new BusStop(5, 30, 15));
        busStops.add(new BusStop(6, 30, 25));
        busStops.add(new BusStop(7, 25, 20));
        busStops.add(new BusStop(100, 40, 10, true));
        return busStops;
    }

    public static List<BusStop> basicBusStopsMoreMessedInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(1, 10, 20));
        busStops.add(new BusStop(5, 30, 15));
        busStops.add(new BusStop(0, 10, 10));
        busStops.add(new BusStop(6, 30, 25));
        busStops.add(new BusStop(3, 20, 20));
        busStops.add(new BusStop(4, 30, 5));
        busStops.add(new BusStop(2, 20, 10));
        busStops.add(new BusStop(7, 25, 20));
        busStops.add(new BusStop(100, 40, 10, true));
        return busStops;
    }
}