package pl.kboba.sbrp.solver;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;

import java.util.List;

@AllArgsConstructor
public abstract class ProblemSolver {

    @NonNull
    @Setter
    City city;

    public abstract List<BusStop> getSolution();
    public abstract void findSolution ();
}
