package domain.vehicles;

import api.domain.Vehicle;
import constants.VehicleType;

public class Bus extends Vehicle {

    public Bus(String vehicleID, double pricePerHour) {
        super(vehicleID, pricePerHour);
        this.type = VehicleType.BUS;
    }
}
