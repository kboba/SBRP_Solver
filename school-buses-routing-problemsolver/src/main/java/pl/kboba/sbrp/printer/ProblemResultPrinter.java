package pl.kboba.sbrp.printer;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import pl.kboba.sbrp.solver.ProblemSolver;

@AllArgsConstructor
public abstract class ProblemResultPrinter {

    @NonNull
    @Setter
    ProblemSolver problemSolver;

    public abstract void printResult ();

}
