package pl.kboba.sbrp;

import pl.kboba.sbrp.model.City;
import pl.kboba.sbrp.solver.BasicProblemPrinter;
import pl.kboba.sbrp.solver.BasicProblemSolver;
import pl.kboba.sbrp.solver.ProblemSolver;
import pl.kboba.sbrp.solver.ResultPrinter;

public class Main {
    public static void main(String[] args) {
        City city = new City();
        ProblemSolver basicProblemSolver = new BasicProblemSolver(city);
        ResultPrinter resultPrinter = new BasicProblemPrinter(basicProblemSolver);
        resultPrinter.printResult();
    }
}
