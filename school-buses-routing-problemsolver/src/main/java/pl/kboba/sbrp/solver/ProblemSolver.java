package pl.kboba.sbrp.solver;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import pl.kboba.sbrp.model.City;

@AllArgsConstructor
public abstract class ProblemSolver {

    @NonNull
    private City city;

    public abstract void findSolution ();

}
