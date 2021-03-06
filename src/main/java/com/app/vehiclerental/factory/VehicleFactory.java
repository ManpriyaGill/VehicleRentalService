package com.app.vehiclerental.factory;

import com.app.vehiclerental.api.domain.Vehicle;
import com.app.vehiclerental.constants.VehicleType;
import com.app.vehiclerental.domain.vehicles.*;

public class VehicleFactory {
    public static Vehicle getVehicleOfType(String type, String vehicleID, double pricePerHour) {
        switch (VehicleType.valueOf(type)) {
            case CAR:
                return new Car(vehicleID, pricePerHour);
            case BIKE:
                return new Bike(vehicleID, pricePerHour);
            case VAN:
                return new Van(vehicleID, pricePerHour);
            case TAXI:
                return new Taxi(vehicleID, pricePerHour);
            case BUS:
                return new Bus(vehicleID, pricePerHour);
            default:
                return null;
        }
    }
}
