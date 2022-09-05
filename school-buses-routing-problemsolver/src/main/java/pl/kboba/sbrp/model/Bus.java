package pl.kboba.sbrp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bus {

    private int maxPassengers;
    private int currentPassengers;
    int distanceTraveled;

}
