package pl.kboba.sbrp;

import pl.kboba.sbrp.model.City;
import pl.kboba.sbrp.solver.*;

public class Main {
    public static void main(String[] args) {
        ProblemResultPrinter problemResultPrinter = new BasicResultPrinter(new BasicProblemSolver(new City()));
        problemResultPrinter.printResult();
        problemResultPrinter.setProblemSolver(new NearestNeightbourProblemSolver(new City()));
        problemResultPrinter.printResult();
    }
}
