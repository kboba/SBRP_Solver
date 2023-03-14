package pl.kboba.sbrp.printer;

import lombok.NonNull;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.solver.ProblemSolver;

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
        problemSolver.optimizeSolutionWith2opt();
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
        if(busStopsSolution == null || problemSolver.getCity() == null) {
            System.out.println("An error occurred: solution is null");
            return;
        }
        System.out.println("Route:");
        BusStop busStop = problemSolver.getCity().findBusStopById(100);
        for(int i = 0; i < busStopsSolution.size(); i++) {
            if(busStop == null) {
                System.out.println("An error occurred: bus stop is null");
                return;
            }
            System.out.print("Id:".concat(String.valueOf(busStop.getId())).concat(" -> "));
            busStop = problemSolver.getCity().findBusStopById(busStop.getNextId());
        }
        double distance = problemSolver.getRouteDistance();
        System.out.println("\nTotal distance: ".concat(String.valueOf(distance)));
    }
}
