package pl.kboba.sbrp.solver;

import lombok.NonNull;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;

import java.util.Random;


public class BasicProblemSolver extends ProblemSolver {

    public BasicProblemSolver(@NonNull City city) {
        super(city);
        initializeRandomRoute();
        setRouteDistance(city.calculateTotalRouteDistance());
    }

    private void initializeRandomRoute() {
        // find starting stop (id == 100)
        BusStop currentBusStop = city.findBusStopById(100);
        // when is any bus stop not visited, then continue visiting
        while(city.isAnyBusStopNotVisited()) {
            BusStop notVisitedBusStop = city.findAnyNotVisitedBusStop();
            currentBusStop.setNextId(notVisitedBusStop.getId());
            notVisitedBusStop.setPreviousId(currentBusStop.getId());
            currentBusStop = notVisitedBusStop;
            currentBusStop.setVisited(true);
        }
        // last visited BusStop lead to starting point
        currentBusStop.setNextId(100);
        city.findBusStopById(100).setPreviousId(currentBusStop.getId());
    }
}
