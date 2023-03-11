package pl.kboba.sbrp.solver;

import lombok.NonNull;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;
import pl.kboba.sbrp.model.Road;

import java.util.Comparator;
import java.util.Random;

public class NearestNeightbourProblemSolver extends ProblemSolver {

    public NearestNeightbourProblemSolver(@NonNull City city) {
        super(city);
        initializeRouteByNN();
        setRouteDistance(city.calculateTotalRouteDistance());
    }

    @Override
    public void findSolution() {
        Random random = new Random();
        int numberOfBusStops = city.getBusStops().size();
        for (int i = 0; i < 200; i++) {
            int firstListId = random.nextInt(numberOfBusStops);
            int secondListId = random.nextInt(numberOfBusStops);
            while (problemSolverUtils.numbersAreSame(firstListId, secondListId)
                    || problemSolverUtils.firstNextIdIsSameLikeSecondId(city, firstListId, secondListId)
                    || problemSolverUtils.secondPreviousIdIsSameLikeFristId(city, firstListId, secondListId)
                    || problemSolverUtils.firstNextIdIsSameLikeSecondPreviousId(city, firstListId, secondListId)
            ){
                firstListId = random.nextInt(numberOfBusStops);
                secondListId = random.nextInt(numberOfBusStops);
            }
            problemSolverUtils.revertTwoBusStopsById(city, firstListId, secondListId);
            double newCalculatedDistance = city.calculateTotalRouteDistance();
            if(newCalculatedDistance >= getRouteDistance())
                problemSolverUtils.revertTwoBusStopsById(city, firstListId, secondListId);
            else {
                setRouteDistance(newCalculatedDistance);
                i = 0;
            }
        }
    }

    private void initializeRouteByNN() {
        var numberOfBusStops = city.getBusStops().size();
        var currentBusStop = city.findBusStopById(100);
        for (int i = 0; i < numberOfBusStops; i++) {
            currentBusStop.setVisited(true);
            BusStop nextBusStop = getNearestNotVisitedBusStopId(currentBusStop);
            if( nextBusStop == null ){
                break;
            }
            currentBusStop.setNextId(nextBusStop.getId());
            nextBusStop.setPreviousId(currentBusStop.getId());
            currentBusStop = nextBusStop;
        }
        currentBusStop.setNextId(100);
        city.findBusStopById(100).setPreviousId(currentBusStop.getId());
    }

    public BusStop getNearestNotVisitedBusStopId(BusStop busStop) {
        var sortedRoads = busStop.getRoadsToOtherStops()
                .stream()
                .sorted(Comparator.comparing(Road::getDistance))
                .toList();

        for (Road road : sortedRoads) {
            var checkedBusStop = city.findBusStopById(road.getSecondBusStopID());
            if (!checkedBusStop.isVisited()) {
                return checkedBusStop;
            }
        }

        return null;
    }
}
