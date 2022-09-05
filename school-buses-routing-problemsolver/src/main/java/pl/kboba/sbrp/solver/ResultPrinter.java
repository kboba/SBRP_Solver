package pl.kboba.sbrp.solver;

import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public abstract class ResultPrinter {

    @NonNull
    private ProblemSolver problemSolver;

    public abstract void printResult ();

}
