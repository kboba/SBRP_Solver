package pl.kboba.sbrp.model;

import lombok.Getter;
import lombok.Setter;
import pl.kboba.sbrp.model.initializers.BusStopsInitializer;
import pl.kboba.sbrp.model.initializers.StudentsInitializer;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class City {

    private List<BusStop> busStops;
    private List<Student> students;
    private List<Road> roads;

    public City() {
        initializeBusStops();
        initializeStudents();
        assignStudentsToStops();
//        deleteUnusedBusStops();
        initializeRoads();
    }

    public double calculateTotalRouteDistance() {
        BusStop school = findBusStopById(100);
        BusStop currentBusStop = school;
        double calculatedRouteDistance = 0;

        while (currentBusStop.getNextId() != 100){
            int nextBusStopId = currentBusStop.getNextId();
            BusStop nextBusStop = findBusStopById(nextBusStopId);
            double distanceBetweenBusStops = calculateDistanceBetweenTwoBusStops(currentBusStop, nextBusStop);
            calculatedRouteDistance += distanceBetweenBusStops;
            currentBusStop = nextBusStop;
        }
        double distanceBetweenLastBusStopAndSchool = calculateDistanceBetweenTwoBusStops(currentBusStop, school);
        calculatedRouteDistance += distanceBetweenLastBusStopAndSchool;

        return calculatedRouteDistance;
    }

    public BusStop findBusStopById(int id) {
        return busStops.stream()
                .filter(busStop -> busStop.getId() == id)
                .findAny()
                .orElse(null);
    }

    public BusStop findAnyNotVisitedBusStop() {
        return busStops.stream()
                .filter(bStop -> !bStop.isVisited())
                .findAny()
                .orElse(null);
    }

    public boolean isAnyBusStopNotVisited() {
        for (BusStop busStop : busStops) {
            if (!busStop.isVisited())
                return true;
        }
        return false;
    }

    private void initializeBusStops() {
        busStops = BusStopsInitializer
//                .basicInitialize();
//                .basicMoreMessedInitialize();
//                .squareInitialize();
                .complicatedInitialize();
    }

    private void initializeStudents() {
        students = StudentsInitializer
                .basicInitialize();
    }

    private void assignStudentsToStops() {
        students.forEach(student -> {
            BusStop nearestBusStopId = findNearestBusStopForStudent(student);
            nearestBusStopId.addPassengerToBusStop();
        });
    }

    private void deleteUnusedBusStops() {
        for (int i = busStops.size()-1; i > 0; i--) {
            if(busStops.get(i).getPassengersOnBusStop() <= 0)
                busStops.remove(i);
        }
    }

    private void initializeRoads() {
        roads = new ArrayList<>();
        // every BusStop
        for (int i = 0; i < busStops.size(); i++) {
            BusStop currentBusTop = busStops.get(i);
            // with add every BusStop to roads
            for (int j = 0; j < busStops.size(); j++) {
                // (only every else BusStop)
                if(i != j) {
                    BusStop secondBusStop = busStops.get(j);
                    double distanceBetweenBusStops = calculateDistanceBetweenTwoBusStops(currentBusTop, secondBusStop);
                    Road newRoad = new Road(currentBusTop.getId(), secondBusStop.getId(), distanceBetweenBusStops);
                    roads.add(newRoad);
                    currentBusTop.addRoad(newRoad);
                }
            }
        }
    }

    private BusStop findNearestBusStopForStudent(Student student){
        BusStop nearestBusStop = null;
        double currentMinDistance = Double.MAX_VALUE;

        for (BusStop currentStop : busStops) {
            double distanceToStop = calculateDistanceBetweenBusStopAndStudent(currentStop, student);
            if (distanceToStop < currentMinDistance) {
                currentMinDistance = distanceToStop;
                nearestBusStop = currentStop;
            }
        }

        return nearestBusStop;
    }

    private double calculateDistanceBetweenBusStopAndStudent(BusStop busStop, Student student) {
        double distanceX = Math.abs(busStop.getX()-student.getX());
        double distanceY = Math.abs(busStop.getY()-student.getY());

        return Math.hypot(distanceX, distanceY);
    }

    public double calculateDistanceBetweenTwoBusStops(BusStop sourceBusStop, BusStop destinationBusStop) {
        double distanceX = Math.abs(sourceBusStop.getX()-destinationBusStop.getX());
        double distanceY = Math.abs(sourceBusStop.getY()-destinationBusStop.getY());

        return Math.hypot(distanceX, distanceY);
    }

    public double calculateDistanceBetweenTwoBusStopsByIds(final int sourceId, final int destinationId) {
        BusStop sourceBusStop = getBusStopById(sourceId);
        BusStop destinationBusStop = getBusStopById(destinationId);
        double distanceX = Math.abs(sourceBusStop.getX()-destinationBusStop.getX());
        double distanceY = Math.abs(sourceBusStop.getY()-destinationBusStop.getY());

        return Math.hypot(distanceX, distanceY);
    }

    private BusStop getBusStopById(final int id) {
        for(BusStop busStop: this.busStops)
            if(id == busStop.getId())
                return busStop;
        throw new IllegalStateException("C - There is no bus stop with given id");
    }
}
