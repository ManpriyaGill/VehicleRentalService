package executors;

import api.domain.Vehicle;

import java.util.List;
import java.util.logging.Level;

public class DisplayVehicleCommandExecutor extends AbstractCommandExecutor {
    @Override
    public void executeCommand(String[] splitCommand) {
        String branchName = splitCommand[1];
        int startTime = Integer.parseInt(splitCommand[2]);
        int endTime = Integer.parseInt(splitCommand[3]);

        List<Vehicle> vehicles = rentalService.getAvailableVehiclesForBranch(branchName, startTime, endTime);

//        printPlainOutput(vehicles);
        printLog(vehicles, branchName, startTime, endTime);
    }

    private void printPlainOutput(List<Vehicle> vehicles) {
        vehicles.forEach(
                vehicle -> System.out.print(vehicle.getID() + " ")
        );
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
