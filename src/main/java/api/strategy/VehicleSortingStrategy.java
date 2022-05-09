package api.strategy;

import api.domain.Vehicle;

import java.util.List;

public interface VehicleSortingStrategy {
    void sortVehicles(List<Vehicle> vehicle);
}
