package executors;

import java.util.logging.Level;

public class AddVehicleCommandExecutor extends AbstractCommandExecutor {

    @Override
    public void executeCommand(String[] splitCommand) {
        String branchName = splitCommand[1];
        String vehicleType = splitCommand[2];
        String vehicleID = splitCommand[3];
        double pricePerHour = Double.parseDouble(splitCommand[4]);

        boolean isVehicleAdded = rentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, pricePerHour);

//        printPlainOutput(isVehicleAdded);
        printLog(isVehicleAdded, branchName, vehicleType, vehicleID, pricePerHour);
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
