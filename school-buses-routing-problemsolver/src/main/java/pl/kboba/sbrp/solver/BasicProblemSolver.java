package pl.kboba.sbrp.solver;

import lombok.NonNull;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;


public class BasicProblemSolver extends ProblemSolver {

    private double routeDistance = 0;

    public BasicProblemSolver(@NonNull City city) {
        super(city);
        initializeRoute();
    }

    @Override
    public void findSolution() {

    }

    private void initializeRoute() {
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

        this.routeDistance = calculateTotalRouteDistance();
    }

    private double calculateTotalRouteDistance() {
        BusStop school = city.findBusStopById(100);
        BusStop currentBusStop = school;
        double calculatedRouteDistance = 0;

        while (currentBusStop.getNextId() != 100){
            int nextBusStopId = currentBusStop.getNextId();
            BusStop nextBusStop = city.findBusStopById(nextBusStopId);
            double distanceBetweenCities = city.calculateDistanceBetweenTwoBusStops(currentBusStop, nextBusStop);
            calculatedRouteDistance += distanceBetweenCities;
            currentBusStop = nextBusStop;
        }
        double distanceBetweenLastBusStopAndSchool = city.calculateDistanceBetweenTwoBusStops(currentBusStop, school);
        calculatedRouteDistance += distanceBetweenLastBusStopAndSchool;

        return calculatedRouteDistance;
    }
}
