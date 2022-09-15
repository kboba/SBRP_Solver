package pl.kboba.sbrp.solver;

import lombok.NonNull;
import pl.kboba.sbrp.model.BusStop;

import java.util.List;

public class BasicResultPrinter extends ProblemResultPrinter {

    public BasicResultPrinter(@NonNull ProblemSolver problemSolver) {
        super(problemSolver);
    }

    @Override
    public void printResult() {
        System.out.println("#########################");
        System.out.println("## Before optimization ##");
        System.out.println("#########################");
        printResultFromProblemSolver();
        problemSolver.findSolution();
        System.out.println();
        System.out.println("#########################");
        System.out.println("## After optimization  ##");
        System.out.println("#########################");
        printResultFromProblemSolver();
    }

    private void printResultFromProblemSolver() {
        List<BusStop> busStopsSolution = problemSolver.getSolution();
        System.out.println("Route:");
        BusStop busStop = problemSolver.city.findBusStopById(100);
        for(int i = 0; i < busStopsSolution.size(); i++) {
            System.out.print("Id:".concat(String.valueOf(busStop.getId())).concat(" -> "));
            busStop = problemSolver.city.findBusStopById(busStop.getNextId());
        }
        double distance = problemSolver.getRouteDistance();
        System.out.println("\nTotal distance: ".concat(String.valueOf(distance)));
    }
}
