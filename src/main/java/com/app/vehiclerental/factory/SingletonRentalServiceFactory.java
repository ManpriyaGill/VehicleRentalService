package com.app.vehiclerental.factory;

import com.app.vehiclerental.service.VehicleRentalService;

public class SingletonRentalServiceFactory {
    private static VehicleRentalService rentalServiceInstance = null;

    public static VehicleRentalService getVehicleRentalServiceInstance() {
        if(rentalServiceInstance == null) {
            rentalServiceInstance = new VehicleRentalService();
        }

        return rentalServiceInstance;
    }

    public static void resetInstance() {
        rentalServiceInstance = null;
    }
}
