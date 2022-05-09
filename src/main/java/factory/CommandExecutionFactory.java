package factory;

import executors.*;

public class CommandExecutionFactory {

    public static AbstractCommandExecutor getCommandExecutor(String command) {
        switch (command) {
            case "ADD_BRANCH":
                return new AddBranchCommandExecutor();
            case "ADD_VEHICLE":
                return new AddVehicleCommandExecutor();
            case "BOOK":
                return new BookVehicleCommandExecutor();
            case "DISPLAY_VEHICLES":
                return new DisplayVehicleCommandExecutor();
            default:
                throw new IllegalArgumentException(String.format("Invalid command type passed %s", command));
        }
    }
}
