package pl.kboba.sbrp.model;

import lombok.Getter;
import lombok.Setter;

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
        deleteUnusedBusStops();
        initializeRoads();
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
                .basicBusStopsInitialize();
//                .basicBusStopsMoreMessedInitialize();
//                .testingBusStopsInitialize();
    }

    private void initializeStudents() {
        students = new ArrayList<>();
        students.add(new Student(9, 12));
        students.add(new Student(9, 22));
        students.add(new Student(19, 12));
        students.add(new Student(19, 22));
        students.add(new Student(29, 16));
        students.add(new Student(29, 7));
        students.add(new Student(39, 22));
        students.add(new Student(39, 12));
        students.add(new Student(39, 32));
        students.add(new Student(29, 29));
        students.add(new Student(14, 25));
        students.add(new Student(16, 19));
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

    public double calculateDistanceBetweenTwoBusStops(BusStop busStop1, BusStop busStop2) {
        double distanceX = Math.abs(busStop1.getX()-busStop2.getX());
        double distanceY = Math.abs(busStop1.getY()-busStop2.getY());

        return Math.hypot(distanceX, distanceY);
    }
}
