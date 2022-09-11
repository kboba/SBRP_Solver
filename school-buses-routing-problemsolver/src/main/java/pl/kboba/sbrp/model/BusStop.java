package pl.kboba.sbrp.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BusStop {

    private List<Road> roadsToOtherStops = new ArrayList<>();
    private int x;
    private int y;
    private int id;
    private int previousId;
    private int nextId;
    private boolean visited;
    private int passengersOnBusStop;

    public BusStop(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.visited = false;
        this.passengersOnBusStop = 0;
    }

    public BusStop(int id, int x, int y, boolean visited) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.visited = visited;
        this.passengersOnBusStop = 0;
    }

    public void addPassengerToBusStop() {
        passengersOnBusStop++;
    }

    public void addRoad(Road road) {
        roadsToOtherStops.add(road);
    }
}
