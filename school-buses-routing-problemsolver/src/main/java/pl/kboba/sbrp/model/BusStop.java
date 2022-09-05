package pl.kboba.sbrp.model;

import lombok.*;

@Data
public class BusStop {

    @NonNull
    private int id;
    private int parent_id = -1;
    private boolean visited = false;
    private double distance = Double.MAX_VALUE;
    @NonNull
    private int passengersOnBusStop;

}
