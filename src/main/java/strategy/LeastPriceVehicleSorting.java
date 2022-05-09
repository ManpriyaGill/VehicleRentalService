package strategy;

import api.domain.Vehicle;
import api.strategy.VehicleSortingStrategy;

import java.util.Comparator;
import java.util.List;

public class LeastPriceVehicleSorting implements VehicleSortingStrategy {

    @Override
    public void sortVehicles(List<Vehicle> vehicle) {
        vehicle.sort(Comparator.comparingDouble(Vehicle::getPricePerHour));
    }
}
