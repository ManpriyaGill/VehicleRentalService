package com.app.vehiclerental.domain.vehicles;

import com.app.vehiclerental.api.domain.Vehicle;
import com.app.vehiclerental.constants.VehicleType;

public class Van extends Vehicle {

    public Van(String vehicleID, double pricePerHour) {
        super(vehicleID, pricePerHour);
        this.type = VehicleType.VAN;
    }
}
