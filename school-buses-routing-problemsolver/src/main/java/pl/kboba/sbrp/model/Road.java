package pl.kboba.sbrp.model;

import lombok.*;

@Data
public class Road {

    @NonNull
    private int roadID;
    @NonNull
    private int distance;
    @NonNull
    private int firstBusStopID;
    @NonNull
    private int secondBusStopID;

}
