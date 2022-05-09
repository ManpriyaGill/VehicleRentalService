package factory;

import service.VehicleRentalService;

public class SingletonRentalServiceFactory {
    private static VehicleRentalService rentalServiceInstance = null;

    public static VehicleRentalService getVehicleRentalServiceInstance() {
        if(rentalServiceInstance == null) {
            rentalServiceInstance = new VehicleRentalService();
        }

        return rentalServiceInstance;
    }
}
