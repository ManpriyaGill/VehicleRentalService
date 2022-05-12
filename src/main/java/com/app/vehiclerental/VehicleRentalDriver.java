package com.app.vehiclerental;

import com.app.vehiclerental.factory.CommandExecutionFactory;
import com.app.vehiclerental.factory.SingletonBranchDAOFactory;
import com.app.vehiclerental.factory.SingletonRentalServiceFactory;
import com.app.vehiclerental.utils.InputCommandValidator;
import com.app.vehiclerental.utils.InputParser;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleRentalDriver {

    public static void main(String[] args) {
        try {
            String filePath = args.length == 0 ? "src/main/resources/inputs.txt" : args[0];
            List<String> inputCommands = InputParser.getInputFromFile(filePath);
            List<String> outputResults = runApplicationCommands(inputCommands);

            printOutput(inputCommands, outputResults);
        } finally {
            resetSingleTons();
        }
    }

    private static void printOutput(List<String> inputCommands, List<String> outputResults) {
        System.out.println("### Output");
        if(inputCommands.size() == outputResults.size()) {
            for(int i = 0; i< inputCommands.size(); i++) {
                System.out.print(inputCommands.get(i) + "  =>  " + outputResults.get(i) + "\n");
            }
            return;
        }

        outputResults.forEach(System.out::println);
    }

    public static void resetSingleTons() {
        // this is ideally handled by the respective frameworks
        SingletonBranchDAOFactory.resetInstance("in-memory");
        SingletonRentalServiceFactory.resetInstance();
    }

    public static List<String> runApplicationCommands(List<String> inputCommands) {
        return inputCommands.stream()
                .map(InputCommandValidator::validateAndGetCommandDetails)
                .map(
                        details -> CommandExecutionFactory
                                .getCommandExecutor(details.getName())
                                .executeCommand(details.getArguments())
                )
                .collect(Collectors.toList());

    }
}
