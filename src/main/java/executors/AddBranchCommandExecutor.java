package executors;

import java.util.Arrays;
import java.util.logging.Level;

public class AddBranchCommandExecutor extends AbstractCommandExecutor {

    @Override
    public void executeCommand(String[] splitCommand) {
        String branchName = splitCommand[1];
        String[] supportedVehicleTypes = getSupportedVehicleTypes(splitCommand[2]);

        boolean isBranchAdded = rentalService.addBranchWithVehicleTypes(branchName, supportedVehicleTypes);

//        printPlainOutput(isBranchAdded);
        printLog(isBranchAdded, branchName, supportedVehicleTypes);
    }

    private void printPlainOutput(boolean isBranchAdded) {
        System.out.println(isBranchAdded);
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
