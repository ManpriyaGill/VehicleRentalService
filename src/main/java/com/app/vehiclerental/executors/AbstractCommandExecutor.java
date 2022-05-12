package com.app.vehiclerental.executors;

import com.app.vehiclerental.factory.SingletonRentalServiceFactory;
import com.app.vehiclerental.service.VehicleRentalService;

import java.util.logging.Logger;

public abstract class AbstractCommandExecutor {

    protected Logger logger = Logger.getLogger("CommandExecutorLogger");
    protected VehicleRentalService rentalService = SingletonRentalServiceFactory.getVehicleRentalServiceInstance();

    public abstract String executeCommand(String[] splitCommand);

}
