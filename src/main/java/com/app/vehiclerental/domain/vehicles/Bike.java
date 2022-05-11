package com.app.vehiclerental.domain.vehicles;

import com.app.vehiclerental.api.domain.Vehicle;
import com.app.vehiclerental.constants.VehicleType;

public class Bike extends Vehicle {

    public Bike(String vehicleID, double pricePerHour) {
        super(vehicleID, pricePerHour);
        this.type = VehicleType.BIKE;
    }

}
