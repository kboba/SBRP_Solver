package pl.kboba.sbrp.solver;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@AllArgsConstructor
public abstract class ProblemResultPrinter {

    @NonNull
    @Setter
    ProblemSolver problemSolver;

    public abstract void printResult ();

}
