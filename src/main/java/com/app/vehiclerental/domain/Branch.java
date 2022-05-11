package com.app.vehiclerental.domain;

import com.app.vehiclerental.api.domain.GenericBranch;
import com.app.vehiclerental.api.domain.Vehicle;
import com.app.vehiclerental.constants.VehicleType;

import java.util.*;

public class Branch implements GenericBranch {

    private final Set<VehicleType> allowedVehicleTypes = new HashSet<>();
    private final Map<String, Vehicle> vehicles = new HashMap<>();

    public Branch(String[] vehicleTypes) {
        for(String vehicleType : vehicleTypes) {
            VehicleType type = VehicleType.valueOf(vehicleType);
            allowedVehicleTypes.add(type);
        }
    }

    @Override
    public Set<VehicleType> getAllowedVehicleTypes() {
        return new HashSet<>(allowedVehicleTypes);
    }

    @Override
    public boolean containsVehicle(String vehicleID) {
        return vehicles.containsKey(vehicleID);
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicles.put(vehicle.getID(), vehicle);
    }

    @Override
    public List<Vehicle> getVehicles() {
        return new ArrayList<>(vehicles.values());
    }

}
