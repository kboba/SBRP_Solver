package pl.kboba.sbrp.solver;

import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;

public class ProblemSolverUtils {

    public void revertTwoBusStopsById(City city, int firstListId, int secondListId) {
        BusStop firstBusStop = city.getBusStops().get(firstListId);
        BusStop secondBusStop = city.getBusStops().get(secondListId);
        BusStop firstBusStopNext = city.findBusStopById(firstBusStop.getNextId());
        BusStop secondBusStopPrevious = city.findBusStopById(secondBusStop.getPreviousId());

        BusStop tempBusStop = secondBusStopPrevious;
        BusStop previousTempBusStop = city.findBusStopById(tempBusStop.getPreviousId());
        BusStop previousPreviousTempBusStop = city.findBusStopById(previousTempBusStop.getPreviousId());
        while (tempBusStop.getId() != firstBusStopNext.getId()) {
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

    public void initializeRouteById(City city) {
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

    public boolean firstNextIdIsSameLikeSecondId(City city, int firstListId, int secondListId) {
        return city.getBusStops().indexOf(city.findBusStopById(city.getBusStops().get(firstListId).getNextId())) == secondListId;
    }

    public boolean secondPreviousIdIsSameLikeFristId(City city, int firstListId, int secondListId) {
        return city.getBusStops().indexOf(city.findBusStopById(city.getBusStops().get(secondListId).getPreviousId())) == firstListId;
    }

    public boolean firstNextIdIsSameLikeSecondPreviousId(City city, int firstListId, int secondListId) {
        return city.getBusStops().get(firstListId).getNextId() == city.getBusStops().get(secondListId).getPreviousId();
    }

    public boolean numbersAreSame(int random1, int random2) {
        return random1 == random2;
    }
}