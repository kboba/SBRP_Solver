package pl.kboba.sbrp.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Road {

    private int firstBusStopID;
    private int secondBusStopID;
    private double distance;
}
