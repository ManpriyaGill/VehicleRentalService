package com.app.vehiclerental.api.strategy;

import com.app.vehiclerental.api.domain.Vehicle;

import java.util.List;

public interface VehicleSortingStrategy {
    void sortVehicles(List<Vehicle> vehicle);
}
