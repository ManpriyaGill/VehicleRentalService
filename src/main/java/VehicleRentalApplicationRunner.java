import factory.CommandExecutionFactory;
import executors.AbstractCommandExecutor;
import utils.InputParser;
import utils.InputCommandValidator;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehicleRentalApplicationRunner {

    private static final Logger logger = Logger.getLogger(VehicleRentalApplicationRunner.class.getName());

    public static void main(String[] args) {
        List<String> inputCommands = InputParser.getInputFromFile();
        for (String command : inputCommands) {
            try {
                String[] splitCommand = InputCommandValidator.validateAndGetCommand(command.trim());
                String commandType = splitCommand[0];
                AbstractCommandExecutor executor = CommandExecutionFactory.getCommandExecutor(commandType);
                executor.executeCommand(splitCommand);
            } catch (Exception e) {
                logger.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }
}
