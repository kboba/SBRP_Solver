package pl.kboba.sbrp.solver;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;
import pl.kboba.sbrp.model.Road;

import java.util.*;

public class ChristofidesAlgorithmProblemSolver extends ProblemSolver {

    List<ChristofidesBusStop> christofidesBusStops = new ArrayList<>();
    List <BusStop> visitedStops = new ArrayList<>();
    List <BusStop> unvisitedStops = new ArrayList<>();
    List <Road> currentRoads = new ArrayList<>();
    List <ChristofidesBusStop> oddDegreeVertices = new ArrayList<>();
    List <Road> christofidesRoads = new ArrayList<>();
    List <Integer> eulerianTour = new ArrayList<>();

    public ChristofidesAlgorithmProblemSolver(@NonNull City city) {
        super(city);
        initializeChristofidesRoute();
        setRouteDistance(city.calculateTotalRouteDistance());
    }

    private void initializeChristofidesRoute() {
        // Initialize
        for(BusStop busStop : city.getBusStops()) {
            christofidesBusStops.add(new ChristofidesBusStop(busStop));
            unvisitedStops.add(busStop);
            currentRoads.addAll(busStop.getRoadsToOtherStops());
        }
        // 1. Finds MST T of Graph
        generateMST();
        // 2. Isolate Set of Odd-Degree Vertices S
        createListOfOddDegreeVertices();
        // 3. Find Min Weight Perfect Matching M of S
        perfectMatchOddDegreeVertices();
        // 4.Combine T and M into Multigraph G
        combineMultigraph();
        // 5. Generate Eulerian Tour of G
        generateEulerianTour();
        // 6. Generate TSP Tour from Eulerian Tour
        initializeRoute();
    }

    private void generateMST() {
        BusStop currentStop = city.findBusStopById(100);
        visitedStops.add(currentStop);
        unvisitedStops.remove(currentStop);
        currentRoads.removeAll(currentStop.getRoadsToOtherStops());
        while (city.isAnyBusStopNotVisited()) {
            currentStop = findClosestUnvisitedStop();
            if(currentStop == null)
                throw new RuntimeException("CAPS - current stop can not be null");
            currentStop.setVisited(true);
            visitedStops.add(currentStop);
            unvisitedStops.remove(currentStop);
//            currentRoads.removeAll(currentStop.getRoadsToOtherStops());
        }
    }

    private void createListOfOddDegreeVertices() {
        christofidesBusStops.forEach(busStop -> {
            if(busStop.getNeighbours().size() % 2 == 1)
                oddDegreeVertices.add(new ChristofidesBusStop(city.findBusStopById(busStop.getId())));
        });
    }

    private void perfectMatchOddDegreeVertices() {
        List<Road> sortedRoads = new ArrayList<>();
        for (ChristofidesBusStop source : oddDegreeVertices) {
            for (ChristofidesBusStop destination : oddDegreeVertices) {
                if(source == destination)
                    continue;
                double distanceBetweenStops = city.calculateDistanceBetweenTwoBusStopsByIds(source.id, destination.id);
                sortedRoads.add(new Road(source.id, destination.id, distanceBetweenStops));
            }
        }
        sortedRoads.sort(Comparator.comparing(Road::getDistance));

        List<ChristofidesBusStop> unmatchedStops = new ArrayList<>(oddDegreeVertices);
        for (Road road : sortedRoads) {
            if (unmatchedStops.isEmpty())
                break;
            if (unmatchedStops.contains(ProblemSolverUtils.findChristofidesBusStopById(unmatchedStops, road.getFirstBusStopID()))
                    && unmatchedStops.contains(ProblemSolverUtils.findChristofidesBusStopById(unmatchedStops, road.getSecondBusStopID()))) {
                christofidesRoads.add(road);
                unmatchedStops.remove(ProblemSolverUtils.findChristofidesBusStopById(unmatchedStops, road.getFirstBusStopID()));
                unmatchedStops.remove(ProblemSolverUtils.findChristofidesBusStopById(unmatchedStops, road.getSecondBusStopID()));
            }
        }
    }

    private void combineMultigraph() {
        for (Road road : christofidesRoads) {
            updateBusStopsNeightbours(road.getFirstBusStopID(), road.getSecondBusStopID());
        }
    }

    private void generateEulerianTour() {
        ChristofidesBusStop currentBusStop = findChristofidesBusStopById(100);
        eulerianTour.add(currentBusStop.getId());
        while (christofidesBusStopsGotNeightbours()) {
            if (currentBusStop.gotSameNeightbour()) {
                Integer neightbourId = currentBusStop.getDoubleRoadNeightbourId();
                updateEulerianTour(currentBusStop, neightbourId);
                ChristofidesBusStop neightbourBusStop = findChristofidesBusStopById(neightbourId);
                updateEulerianTour(neightbourBusStop, currentBusStop.getId());
                updateEulerianTour(neightbourBusStop, currentBusStop.getId());
                updateEulerianTour(currentBusStop, neightbourId);
                eulerianTour.add(neightbourId);
                eulerianTour.add(currentBusStop.getId());
            } else {
                Integer firstNeightbourId = currentBusStop.getNeighbours().get(0);
                updateEulerianTour(currentBusStop, firstNeightbourId);
                ChristofidesBusStop neightbourBusStop = findChristofidesBusStopById(firstNeightbourId);
                updateEulerianTour(neightbourBusStop, currentBusStop.getId());
                eulerianTour.add(firstNeightbourId);
                currentBusStop = neightbourBusStop;
            }
        }
    }

    private void updateEulerianTour(ChristofidesBusStop currentBusStop, final Integer neightbour) {
        currentBusStop.neighbours.remove(neightbour);
    }

    private void initializeRoute() {
        List<Integer> busTour = new ArrayList<>();
        for (Integer currentBusStopId : eulerianTour) {
            if (!busTour.contains(currentBusStopId))
                busTour.add(currentBusStopId);
        }
        busTour.remove(0);
        busTour.add(100);

        BusStop currentBusStop = this.city.findBusStopById(100);
        int previousBusStopId;
        for (Integer nextBusStopId : busTour) {
            currentBusStop.setNextId(nextBusStopId);
            previousBusStopId = currentBusStop.getId();
            currentBusStop = this.city.findBusStopById(nextBusStopId);
            currentBusStop.setPreviousId(previousBusStopId);
        }
    }

    private boolean christofidesBusStopsGotNeightbours() {
        for (ChristofidesBusStop christofidesBusStop : christofidesBusStops) {
            if(!christofidesBusStop.getNeighbours().isEmpty())
                return true;
        }
        return false;
    }

    private BusStop findClosestUnvisitedStop() {
        Iterator<Road> sortedRoadsIterator = sortRoads(currentRoads).iterator();

        BusStop busStop = null;
        while (busStop == null && sortedRoadsIterator.hasNext()) {
            Road road = sortedRoadsIterator.next();
            BusStop firstBusStop = city.findBusStopById(road.getFirstBusStopID());
            BusStop secondBusStop = city.findBusStopById(road.getSecondBusStopID());
            if (!firstBusStop.isVisited() && secondBusStop.isVisited()) {
                updateBusStopsNeightbours(firstBusStop, secondBusStop);
                currentRoads.removeAll(firstBusStop.getRoadsToOtherStops());
                busStop = firstBusStop;
            }
            else if (!secondBusStop.isVisited() && firstBusStop.isVisited()) {
                updateBusStopsNeightbours(firstBusStop, secondBusStop);
                currentRoads.removeAll(secondBusStop.getRoadsToOtherStops());
                busStop = secondBusStop;
            }
        }
        return busStop;
    }

    private void updateBusStopsNeightbours(BusStop firstBusStop, BusStop secondBusStop) {
        findChristofidesBusStopById(firstBusStop.getId()).getNeighbours().add(secondBusStop.getId());
        findChristofidesBusStopById(secondBusStop.getId()).getNeighbours().add(firstBusStop.getId());
    }

    private void updateBusStopsNeightbours(final int firstBusStopId, final int secondBusStopId) {
        findChristofidesBusStopById(firstBusStopId).getNeighbours().add(secondBusStopId);
        findChristofidesBusStopById(secondBusStopId).getNeighbours().add(firstBusStopId);
    }

    private List<Road> sortRoads(List<Road> roads) {
        return roads.stream()
                .sorted(Comparator.comparing(Road::getDistance))
                .toList();
    }

    ChristofidesBusStop findChristofidesBusStopById(int id) {
        return christofidesBusStops.stream()
                .filter(busStop -> busStop.getId() == id)
                .findAny()
                .orElse(null);
    }

    static final class ChristofidesBusStop {
        @Getter
        private final int id;
        @Getter
        @Setter
        private List<Integer> neighbours = new ArrayList<>();

        public ChristofidesBusStop(BusStop busStop) {
            this.id = busStop.getId();
        }

        private boolean gotSameNeightbour() {
            Set<Integer> uniqueElements = new HashSet<>();
            for (Integer neighbour : neighbours) {
                if(uniqueElements.contains(neighbour))
                    return true;
                uniqueElements.add(neighbour);
            }
            return false;
        }

        private Integer getDoubleRoadNeightbourId() {
            Set<Integer> uniqueElements = new HashSet<>();
            for (Integer neighbour : neighbours) {
                if(uniqueElements.contains(neighbour))
                    return neighbour;
                uniqueElements.add(neighbour);
            }
            return null;
        }
    }
}
