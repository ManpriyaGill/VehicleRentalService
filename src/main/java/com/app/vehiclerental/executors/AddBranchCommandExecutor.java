package com.app.vehiclerental.executors;

import java.util.Arrays;
import java.util.logging.Level;

public class AddBranchCommandExecutor extends AbstractCommandExecutor {

    @Override
    public String executeCommand(String[] splitCommand) {
        String branchName = splitCommand[1];
        String[] supportedVehicleTypes = getSupportedVehicleTypes(splitCommand[2]);

        boolean isBranchAdded = rentalService.addBranchWithVehicleTypes(branchName, supportedVehicleTypes);

        printLog(isBranchAdded, branchName, supportedVehicleTypes);

        return Boolean.toString(isBranchAdded).toUpperCase();
    }

    private String[] getSupportedVehicleTypes(String vehicleTypes) {
        return vehicleTypes.split(",");
    }

    private void printLog(boolean isBranchAdded, String branchName, String[] supportedVehicleTypes) {
        if(isBranchAdded) {
            logger.log(
                    Level.INFO,
                    String.format("Successfully added branch %s with vehicle types: %s\n",
                            branchName, Arrays.toString(supportedVehicleTypes))
            );
        } else {
            logger.log(
                    Level.SEVERE,
                    String.format("Cannot add branch %s with vehicle types: %s\n",
                            branchName, Arrays.toString(supportedVehicleTypes))
            );
        }
    }
}
