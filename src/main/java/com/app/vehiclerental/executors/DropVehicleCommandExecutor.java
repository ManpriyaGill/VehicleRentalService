package com.app.vehiclerental.executors;

public class DropVehicleCommandExecutor extends AbstractCommandExecutor {
    @Override
    public String executeCommand(String[] splitCommand) {
        String branchName = splitCommand[1];
        String vehicleID = splitCommand[2];
        int startTime = Integer.parseInt(splitCommand[3]);
        int endTime = Integer.parseInt(splitCommand[4]);

        boolean isVehicleDropped = rentalService.dropVehicleAtBranch(branchName, vehicleID, startTime, endTime);

        return Boolean.toString(isVehicleDropped).toUpperCase();
    }
}
