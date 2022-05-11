package com.app.vehiclerental.executors;

import com.app.vehiclerental.api.domain.Vehicle;

import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class DisplayVehicleCommandExecutor extends AbstractCommandExecutor {
    @Override
    public String executeCommand(String[] splitCommand) {
        String branchName = splitCommand[1];
        int startTime = Integer.parseInt(splitCommand[2]);
        int endTime = Integer.parseInt(splitCommand[3]);

        List<Vehicle> vehicles = rentalService.getAvailableVehiclesForBranch(branchName, startTime, endTime);

        printLog(vehicles, branchName, startTime, endTime);

        return vehicles.stream()
                .map(Vehicle::getID)
                .collect(Collectors.joining(","));
    }

    private void printLog(List<Vehicle> vehicles, String branchName, int startTime, int endTime) {
        if(vehicles.isEmpty()) {
            logger.log(Level.WARNING,
                    String.format("Cannot find any vehicles in branch %s for time %d to %d\n",
                            branchName, startTime, endTime));
        } else {
            logger.log(Level.INFO,
                    String.format("Following vehicles found for %s for time %d to %d \n %s\n",
                            branchName, startTime, endTime, vehicles));
        }
    }
}
