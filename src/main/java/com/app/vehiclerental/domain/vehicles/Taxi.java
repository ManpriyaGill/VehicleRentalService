package com.app.vehiclerental.domain.vehicles;

import com.app.vehiclerental.api.domain.Vehicle;
import com.app.vehiclerental.constants.VehicleType;

public class Taxi extends Vehicle {

    public Taxi(String vehicleID, double pricePerHour) {
        super(vehicleID, pricePerHour);
        this.type = VehicleType.TAXI;
    }
}
