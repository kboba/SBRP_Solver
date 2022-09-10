package pl.kboba.sbrp.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BusStop {

    private int x;
    private int y;
    private int id;
    private int previous_id;
    private int next_id;
    private boolean visited;
    private int passengersOnBusStop;

    public BusStop(int id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
        visited = false;
    }
}
