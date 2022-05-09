package domain.vehicles;

import api.domain.Vehicle;
import constants.VehicleType;

public class Bike extends Vehicle {

    public Bike(String vehicleID, double pricePerHour) {
        super(vehicleID, pricePerHour);
        this.type = VehicleType.BIKE;
    }

}
