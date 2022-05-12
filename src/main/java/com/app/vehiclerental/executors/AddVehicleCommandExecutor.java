package com.app.vehiclerental.executors;

import java.security.InvalidParameterException;
import java.util.logging.Level;

public class AddVehicleCommandExecutor extends AbstractCommandExecutor {

    @Override
    public String executeCommand(String[] splitCommand) {
        String branchName = splitCommand[1];
        String vehicleType = splitCommand[2];
        String vehicleID = splitCommand[3];
        double pricePerHour = Double.parseDouble(splitCommand[4]);
        boolean isVehicleAdded = false;
        try {
            validatePrice(pricePerHour);
            isVehicleAdded = rentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, pricePerHour);
            printLog(isVehicleAdded, branchName, vehicleType, vehicleID, pricePerHour);

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
        
        return Boolean.toString(isVehicleAdded).toUpperCase();
    }

    private void validatePrice(double pricePerHour) {
        if(pricePerHour <= 0) {
            throw new InvalidParameterException(String.format("Entered price %f is invalid", pricePerHour));
        }
    }

    private void printPlainOutput(boolean isVehicleAdded) {
        System.out.println(isVehicleAdded);
    }

    private void printLog(boolean isVehicleAdded, String branchName, String vehicleType,
                          String vehicleID, double pricePerHour) {
        if(isVehicleAdded) {
            logger.log(
                    Level.INFO,
                    String.format("Successfully added %s with id %s and price %.2f to branch %s\n",
                            vehicleType, vehicleID, pricePerHour, branchName)
            );
        } else {
            logger.log(
                    Level.SEVERE,
                    String.format("Cannot add %s with id %s and price %.2f to branch %s\n",
                            vehicleType, vehicleID, pricePerHour, branchName)
            );
        }
    }
}
