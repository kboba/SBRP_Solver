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
        System.out.println();
        System.out.println();
    }

    private void printResultFromProblemSolver() {
        List<BusStop> busStopsSolution = problemSolver.getSolution();
        if(busStopsSolution == null || problemSolver.city == null) {
            System.out.println("An error occurred: solution is null");
            return;
        }
        System.out.println("Route:");
        BusStop busStop = problemSolver.city.findBusStopById(100);
        for(int i = 0; i < busStopsSolution.size(); i++) {
            if(busStop == null) {
                System.out.println("An error occurred: bus stop is null");
                return;
            }
            System.out.print("Id:".concat(String.valueOf(busStop.getId())).concat(" -> "));
            busStop = problemSolver.city.findBusStopById(busStop.getNextId());
        }
        double distance = problemSolver.getRouteDistance();
        System.out.println("\nTotal distance: ".concat(String.valueOf(distance)));
    }
}
