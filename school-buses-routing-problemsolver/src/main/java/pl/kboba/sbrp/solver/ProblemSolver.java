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
    protected final static ProblemSolverUtils problemSolverUtils = new ProblemSolverUtils();

    ProblemSolver(@NonNull City city) {
        this.city = city;
    }

    public abstract List<BusStop> getSolution();
    public abstract void findSolution ();
}
