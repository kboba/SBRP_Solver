package pl.kboba.sbrp;

import pl.kboba.sbrp.model.City;
import pl.kboba.sbrp.printer.BasicResultPrinter;
import pl.kboba.sbrp.printer.ProblemResultPrinter;
import pl.kboba.sbrp.solver.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n## Basic problem solver (random route)");
        ProblemResultPrinter problemResultPrinter = new BasicResultPrinter(new BasicProblemSolver(new City()));
        problemResultPrinter.printResult();
        System.out.println("\n## Nearest neighbour problem solver");
        problemResultPrinter.setProblemSolver(new NearestNeighbourProblemSolver(new City()));
        problemResultPrinter.printResult();
        System.out.println("\n## Greedy Heuristic problem solver");
        problemResultPrinter.setProblemSolver(new GreedyHeuristicProblemSolver(new City()));
        problemResultPrinter.printResult();
    }
}
