package executors;

import factory.SingletonRentalServiceFactory;
import service.VehicleRentalService;

import java.util.logging.Logger;

public abstract class AbstractCommandExecutor {

    protected Logger logger = Logger.getLogger("CommandExecutorLogger");
    protected VehicleRentalService rentalService = SingletonRentalServiceFactory.getVehicleRentalServiceInstance();

    public abstract void executeCommand(String[] splitCommand);


    public void validateInputParameters(String[] splitCommand) {
        // validate if the input parameters are valid in the context of the given command
    }
}
