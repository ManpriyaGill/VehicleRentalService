package com.app.vehiclerental.strategy;

import com.app.vehiclerental.api.domain.Vehicle;
import com.app.vehiclerental.api.strategy.VehicleSortingStrategy;

import java.util.Comparator;
import java.util.List;

public class LeastPriceVehicleSorting implements VehicleSortingStrategy {

    @Override
    public void sortVehicles(List<Vehicle> vehicle) {
        vehicle.sort(Comparator.comparingDouble(Vehicle::getPricePerHour));
    }
}
