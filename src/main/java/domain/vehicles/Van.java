package domain.vehicles;

import api.domain.Vehicle;
import constants.VehicleType;

public class Van extends Vehicle {

    public Van(String vehicleID, double pricePerHour) {
        super(vehicleID, pricePerHour);
        this.type = VehicleType.VAN;
    }
}
