package pl.kboba.sbrp.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class BusStop {

    private int id;
    private int previousId = -1;
    private int nextId = -1;
    private int x;
    private int y;
    private boolean visited;
    private int passengersOnBusStop = 0;
    private List<Road> roadsToOtherStops = new ArrayList<>();

    public BusStop(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.visited = false;
    }

    public BusStop(int id, int x, int y, boolean visited) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.visited = visited;
    }

    public void addPassengerToBusStop() {
        passengersOnBusStop++;
    }

    public void addRoad(Road road) {
        roadsToOtherStops.add(road);
    }

    @Override
    public String toString() {
        return "BusStop{" +
                "id=" + id +
                ", previousId=" + previousId +
                ", nextId=" + nextId +
                '}';
    }
}
