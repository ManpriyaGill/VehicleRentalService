package com.app.vehiclerental.factory;

import com.app.vehiclerental.executors.*;

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
            case "DROP":
                return new DropVehicleCommandExecutor();
            default:
                throw new IllegalArgumentException(String.format("Invalid command type passed %s", command));
        }
    }
}
