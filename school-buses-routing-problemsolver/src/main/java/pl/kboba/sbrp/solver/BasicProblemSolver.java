package pl.kboba.sbrp.solver;

import lombok.Getter;
import lombok.NonNull;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;

import java.util.List;
import java.util.Random;


public class BasicProblemSolver extends ProblemSolver {

    Random random = new Random();

    public BasicProblemSolver(@NonNull City city) {
        super(city);
        initializeRoute();
    }

    @Override
    public void findSolution() {
        int numberOfBusStops = city.getBusStops().size();
        for (int i = 0; i < 200; i++) {
            int firstListId = random.nextInt(numberOfBusStops);
            int secondListId = random.nextInt(numberOfBusStops);
            while (numbersAreSame(firstListId, secondListId)
                    || firstNextIdIsSameLikeSecondId(firstListId, secondListId)
                    || secondPreviousIdIsSameLikeFristId(firstListId, secondListId)
                    || firstNextIdIsSameLikeSecondPreviousId(firstListId, secondListId)
            ){
                firstListId = random.nextInt(numberOfBusStops);
                secondListId = random.nextInt(numberOfBusStops);
            }
            revertTwoBusStopsById(firstListId, secondListId);
            double newCalculatedDistance = calculateTotalRouteDistance();
            if(newCalculatedDistance >= routeDistance)
                revertTwoBusStopsById(firstListId, secondListId);
            else
                routeDistance = newCalculatedDistance;
        }
    }

    private void revertTwoBusStopsById(int firstListId, int secondListId) {
        BusStop firstBusStop = city.getBusStops().get(firstListId);
        BusStop secondBusStop = city.getBusStops().get(secondListId);
        BusStop firstBusStopNext = city.findBusStopById(firstBusStop.getNextId());
        BusStop secondBusStopPrevious = city.findBusStopById(secondBusStop.getPreviousId());

        BusStop tempBusStop = secondBusStopPrevious;
        BusStop previousTempBusStop = city.findBusStopById(tempBusStop.getPreviousId());
        BusStop previousPreviousTempBusStop = city.findBusStopById(previousTempBusStop.getPreviousId());
        while(tempBusStop.getId() != firstBusStopNext.getId()){
            tempBusStop.setNextId(previousTempBusStop.getId());
            previousTempBusStop.setPreviousId(tempBusStop.getId());
            tempBusStop = previousTempBusStop;
            previousTempBusStop = previousPreviousTempBusStop;
            previousPreviousTempBusStop = city.findBusStopById(previousPreviousTempBusStop.getPreviousId());
        }

        firstBusStop.setNextId(secondBusStopPrevious.getId());
        secondBusStopPrevious.setPreviousId(firstBusStop.getId());
        secondBusStop.setPreviousId(firstBusStopNext.getId());
        firstBusStopNext.setNextId(secondBusStop.getId());
    }

    private boolean firstNextIdIsSameLikeSecondId(int firstListId, int secondListId) {
        return city.getBusStops().indexOf(city.findBusStopById(city.getBusStops().get(firstListId).getNextId())) == secondListId;
    }

    private boolean secondPreviousIdIsSameLikeFristId(int firstListId, int secondListId) {
        return city.getBusStops().indexOf(city.findBusStopById(city.getBusStops().get(secondListId).getPreviousId())) == firstListId;
    }

    private boolean firstNextIdIsSameLikeSecondPreviousId(int firstListId, int secondListId) {
        return city.getBusStops().get(firstListId).getNextId() == city.getBusStops().get(secondListId).getPreviousId();
    }

    private boolean numbersAreSame(int random1, int random2) {
        return random1 == random2;
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
