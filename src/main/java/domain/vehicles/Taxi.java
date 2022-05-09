package domain.vehicles;

import api.domain.Vehicle;
import constants.VehicleType;

public class Taxi extends Vehicle {

    public Taxi(String vehicleID, double pricePerHour) {
        super(vehicleID, pricePerHour);
        this.type = VehicleType.TAXI;
    }
}
