package pl.kboba.sbrp;

import pl.kboba.sbrp.model.City;
import pl.kboba.sbrp.solver.BasicResultPrinter;
import pl.kboba.sbrp.solver.BasicProblemSolver;
import pl.kboba.sbrp.solver.ProblemSolver;
import pl.kboba.sbrp.solver.ProblemResultPrinter;

public class Main {
    public static void main(String[] args) {
        City city = new City();
        ProblemSolver basicProblemSolver = new BasicProblemSolver(city);
        ProblemResultPrinter problemResultPrinter = new BasicResultPrinter(basicProblemSolver);
        problemResultPrinter.printResult();
    }
}
