package pl.kboba.sbrp.solver;

import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;

import java.util.List;

public class ProblemSolverUtils {

    public static ChristofidesAlgorithmProblemSolver.ChristofidesBusStop findChristofidesBusStopById(final List<ChristofidesAlgorithmProblemSolver.ChristofidesBusStop> chBusStops, final int id) {
        for(ChristofidesAlgorithmProblemSolver.ChristofidesBusStop christofidesBusStop : chBusStops)
            if(id == christofidesBusStop.getId())
                return christofidesBusStop;
        return null;
    }

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