package pl.kboba.sbrp.solver;

import lombok.NonNull;
import pl.kboba.sbrp.model.City;

public class BasicProblemSolver extends ProblemSolver {
    public BasicProblemSolver(@NonNull City city) {
        super(city);
    }

    @Override
    public void findSolution() {
        initializeRoute();
    }

    private void initializeRoute() {

    }
}
