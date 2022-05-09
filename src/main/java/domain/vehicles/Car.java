package domain.vehicles;

import api.domain.Vehicle;
import constants.VehicleType;

public class Car extends Vehicle {

    public Car(String vehicleID, double pricePerHour) {
        super(vehicleID, pricePerHour);
        this.type = VehicleType.CAR;
    }
}
