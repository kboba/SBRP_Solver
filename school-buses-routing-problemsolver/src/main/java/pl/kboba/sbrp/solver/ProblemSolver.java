package pl.kboba.sbrp.solver;

import lombok.*;
import pl.kboba.sbrp.model.BusStop;
import pl.kboba.sbrp.model.City;

import java.util.List;
import java.util.Random;

public abstract class ProblemSolver {

    @NonNull
    @Setter
    @Getter
    protected City city;
    @Getter
    @Setter
    private double routeDistance = 0;
    protected static final ProblemSolverUtils problemSolverUtils = new ProblemSolverUtils();

    ProblemSolver(@NonNull City city) {
        this.city = city;
    }

    public List<BusStop> getSolution() {
        return city.getBusStops();
    }

    public void findSolution (){
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
}
