package com.app.vehiclerental.utils;

import java.util.Map;
import java.util.logging.Logger;

public class InputCommandValidator {

    private static final Logger logger = Logger.getLogger("CommandValidatorLogger");

    public static final String COMMAND_DELIMITER = " ";
    private static final Map<String, Integer> commandParameterCount = Map.of(
            "ADD_BRANCH", 3,
            "ADD_VEHICLE", 5,
            "BOOK", 5,
            "DISPLAY_VEHICLES", 4
    );

    public static String[] validateAndGetCommand(String command) {
        String[] splitCommand = command.split(COMMAND_DELIMITER);

        if (splitCommand.length == 0
                || !commandParameterCount.containsKey(splitCommand[0])
                || splitCommand.length != commandParameterCount.get(splitCommand[0])
        ) {
            throw new IllegalArgumentException(
                    String.format("Command %s is invalid or incorrect number of arguments passed",
                            command));
        }

        return splitCommand;
    }
}
