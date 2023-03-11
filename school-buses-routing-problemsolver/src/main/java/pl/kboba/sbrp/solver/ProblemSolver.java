package pl.kboba.sbrp.solver;

import lombok.*;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;

import java.util.List;

public abstract class ProblemSolver {

    @NonNull
    @Setter
    protected City city;
    @Getter
    @Setter
    private double routeDistance = 0;
    protected static final ProblemSolverUtils problemSolverUtils = new ProblemSolverUtils();

    ProblemSolver(@NonNull City city) {
        this.city = city;
    }

    public List<BusStop> getSolution() {
        return city.getBusStops();
    }
    public abstract void findSolution ();
}
