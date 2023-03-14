package pl.kboba.sbrp.model.initializers;

import pl.kboba.sbrp.model.BusStop;

import java.util.ArrayList;
import java.util.List;

public final class BusStopsInitializer {
    private BusStopsInitializer(){

    }

    public static List<BusStop> testingInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(100, 40, 10, true));
        busStops.add(new BusStop(2, 10, 15));
        busStops.add(new BusStop(3, 25, 10));
        busStops.add(new BusStop(4, 25, 20));
        busStops.add(new BusStop(5, 40, 20));
        return busStops;
    }

    public static List<BusStop> basicInitialize() {
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

    public static List<BusStop> basicMoreMessedInitialize() {
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

    public static List<BusStop> squareInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(0, 10, 10));
        busStops.add(new BusStop(1, 10, 20));
        busStops.add(new BusStop(2, 20, 10));
        busStops.add(new BusStop(3, 20, 20));
        busStops.add(new BusStop(4, 30, 10));
        busStops.add(new BusStop(5, 30, 20));
        busStops.add(new BusStop(6, 30, 30));
        busStops.add(new BusStop(7, 30, 40));
        busStops.add(new BusStop(8, 10, 30));
        busStops.add(new BusStop(9, 20, 30));
        busStops.add(new BusStop(10, 10, 40));
        busStops.add(new BusStop(11, 20, 40));
        busStops.add(new BusStop(12, 40, 10));
        busStops.add(new BusStop(13, 40, 20));
        busStops.add(new BusStop(14, 40, 30));
        busStops.add(new BusStop(15, 40, 40));
        busStops.add(new BusStop(100, 50, 25, true));
        return busStops;
    }

    public static List<BusStop> complicatedInitialize() {
        List<BusStop> busStops = new ArrayList<>();
        busStops.add(new BusStop(0, 5, 9));
        busStops.add(new BusStop(1, 14, 24));
        busStops.add(new BusStop(2, 19, 34));
        busStops.add(new BusStop(3, 12, 12));
        busStops.add(new BusStop(4, 15, 32));
        busStops.add(new BusStop(5, 32, 12));
        busStops.add(new BusStop(6, 45, 21));
        busStops.add(new BusStop(7, 13, 49));
        busStops.add(new BusStop(8, 27, 38));
        busStops.add(new BusStop(9, 20, 17));
        busStops.add(new BusStop(10, 7, 29));
        busStops.add(new BusStop(11, 37, 16));
        busStops.add(new BusStop(12, 3, 41));
        busStops.add(new BusStop(13, 26, 31));
        busStops.add(new BusStop(14, 19, 23));
        busStops.add(new BusStop(15, 29, 7));
        busStops.add(new BusStop(16, 15, 10));
        busStops.add(new BusStop(17, 37, 6));
        busStops.add(new BusStop(18, 24, 10));
        busStops.add(new BusStop(19, 13, 5));
        busStops.add(new BusStop(100, 50, 25, true));
        return busStops;
    }
}