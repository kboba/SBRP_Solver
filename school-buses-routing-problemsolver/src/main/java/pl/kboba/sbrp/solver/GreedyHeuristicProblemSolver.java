package pl.kboba.sbrp.solver;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;
import pl.kboba.sbrp.model.Road;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class GreedyHeuristicProblemSolver extends ProblemSolver {

    List <GreedyHeuristicBusStop> greedyBusStops = new ArrayList<>();

    public GreedyHeuristicProblemSolver(@NonNull City city) {
        super(city);
        initializeGreedyHeuristicRoute();
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

    private void initializeGreedyHeuristicRoute() {
        for(BusStop busStop : city.getBusStops())
            greedyBusStops.add(new GreedyHeuristicBusStop(busStop));

        setGreedyHeuristicBusStopsNeighbours();
        initializeRoute();
    }

    private void initializeRoute() {
        int currentId = 100;
        for(int i = 0; i < greedyBusStops.size(); i++) {
            GreedyHeuristicBusStop currentGreedyHeuristicBusStop = findGreedyBusStopById(currentId);
            BusStop currentBusStop = city.findBusStopById(currentId);
            currentBusStop.setVisited(true);
            List<Integer> neighbours = currentGreedyHeuristicBusStop.getNeighbours();
            int firstNeighbourId = neighbours.get(0);
            int secondNeighbourId = neighbours.get(1);

            if (currentBusStop.getPreviousId() != firstNeighbourId) {
                setNextBusStop(currentBusStop, firstNeighbourId);
                currentId = firstNeighbourId;
            }
            else if (currentBusStop.getPreviousId() != secondNeighbourId) {
                setNextBusStop(currentBusStop, secondNeighbourId);
                currentId = secondNeighbourId;
            }
        }
    }

    private void setGreedyHeuristicBusStopsNeighbours() {
        List<Road> sortedRoads = city.getRoads()
                .stream()
                .sorted(Comparator.comparing(Road::getDistance))
                .toList();

        int counter = 0;
        int limit = greedyBusStops.size();
        for (Road road : sortedRoads) {
            int firstBusStopID = road.getFirstBusStopID();
            int secondBusStopID = road.getSecondBusStopID();
            GreedyHeuristicBusStop firstBusStop = findGreedyBusStopById(firstBusStopID);
            GreedyHeuristicBusStop secondBusStop = findGreedyBusStopById(secondBusStopID);
            List<Integer> firstNeighbours = firstBusStop.getNeighbours();
            List<Integer> secondNeighbours = secondBusStop.getNeighbours();
            if( firstNeighbours.size() > 1 || secondNeighbours.size() > 1 || firstNeighbours.contains(secondBusStopID) || secondNeighbours.contains(firstBusStopID))
                continue;
            firstNeighbours.add(secondBusStopID);
            secondNeighbours.add(firstBusStopID);
            counter++;

            if(isCycle(firstBusStopID) || isCycle(secondBusStopID)) {
                firstNeighbours.remove(firstNeighbours.indexOf(secondBusStopID));
                secondNeighbours.remove(secondNeighbours.indexOf(firstBusStopID));
                counter--;
            }

            if(counter >= limit)
                break;
        }

        List<GreedyHeuristicBusStop> twoLastGreedyHeuristicBusStops = new ArrayList<>();
        for(GreedyHeuristicBusStop greedyHeuristicBusStop : greedyBusStops) {
            if(greedyHeuristicBusStop.neighbours.size()==1)
                twoLastGreedyHeuristicBusStops.add(greedyHeuristicBusStop);
        }
        GreedyHeuristicBusStop firstOfLast = twoLastGreedyHeuristicBusStops.get(0);
        GreedyHeuristicBusStop secondOfLast = twoLastGreedyHeuristicBusStops.get(1);
        firstOfLast.getNeighbours().add(secondOfLast.getId());
        secondOfLast.getNeighbours().add(firstOfLast.getId());
    }

    private boolean isCycle(int firstBusStopID) {
        boolean isCycle = false;
        int lastVisitedStopId = -2;
        int currentStopId = findGreedyBusStopById(firstBusStopID).getNeighbours().get(0);
        int timesVisited = 0;
        while(!isCycle) {
            List<Integer> neighbours = findGreedyBusStopById(currentStopId).getNeighbours();
            if(neighbours.size() > 1) {
                if (neighbours.get(0) == firstBusStopID || neighbours.get(1) == firstBusStopID)
                    timesVisited++;
                if(timesVisited > 1)
                    isCycle = true;
                else if(neighbours.get(0) != lastVisitedStopId){
                    lastVisitedStopId = currentStopId;
                    currentStopId = neighbours.get(0);
                }
                else if(neighbours.get(1) != lastVisitedStopId) {
                    lastVisitedStopId = currentStopId;
                    currentStopId = neighbours.get(1);
                }

            }
            else break;
        }
        return isCycle;
    }

    public GreedyHeuristicBusStop findGreedyBusStopById(int id) {
        return greedyBusStops.stream()
                .filter(busStop -> busStop.getId() == id)
                .findAny()
                .orElse(null);
    }

    private void setNextBusStop(BusStop currentBusStop, int firstNeighbourId) {
        currentBusStop.setNextId(firstNeighbourId);
        city.findBusStopById(currentBusStop.getNextId()).setPreviousId(currentBusStop.getId());
    }

    private class GreedyHeuristicBusStop {

        @Getter
        private int id;
        @Getter
        @Setter
        private List<Integer> neighbours = new ArrayList<>();

        public GreedyHeuristicBusStop(BusStop busStop) {
            this.id = busStop.getId();
        }
    }
}
