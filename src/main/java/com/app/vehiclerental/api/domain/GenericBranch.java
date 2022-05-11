package com.app.vehiclerental.api.domain;

import com.app.vehiclerental.constants.VehicleType;

import java.util.Collection;
import java.util.Set;

public interface GenericBranch {

    Set<VehicleType> getAllowedVehicleTypes();

    boolean containsVehicle(String vehicleID);

    void addVehicle(Vehicle vehicle);

    Collection<Vehicle> getVehicles();
}
