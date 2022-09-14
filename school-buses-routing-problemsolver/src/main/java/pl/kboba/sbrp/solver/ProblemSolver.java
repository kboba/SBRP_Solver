package pl.kboba.sbrp.solver;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;

import java.util.List;

public abstract class ProblemSolver {

    ProblemSolver(@NonNull City city) {
        this.city = city;
    }

    @NonNull
    @Setter
    City city;

    @Getter
    double routeDistance = 0;

    public List<BusStop> getSolution() {
        return city.getBusStops();
    }
    public abstract void findSolution ();
}
