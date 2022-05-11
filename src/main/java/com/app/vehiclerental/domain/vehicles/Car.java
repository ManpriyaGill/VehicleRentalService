package com.app.vehiclerental.domain.vehicles;

import com.app.vehiclerental.api.domain.Vehicle;
import com.app.vehiclerental.constants.VehicleType;

public class Car extends Vehicle {

    public Car(String vehicleID, double pricePerHour) {
        super(vehicleID, pricePerHour);
        this.type = VehicleType.CAR;
    }
}
