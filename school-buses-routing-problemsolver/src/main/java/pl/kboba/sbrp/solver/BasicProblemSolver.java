package pl.kboba.sbrp.solver;

import lombok.NonNull;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;

import java.util.Random;


public class BasicProblemSolver extends ProblemSolver {

    Random random = new Random();

    public BasicProblemSolver(@NonNull City city) {
        super(city);
        initializeRouteById();
        setRouteDistance(city.calculateTotalRouteDistance());
    }


    @Override
    public void findSolution() {
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


    public void initializeRouteById() {
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
