package com.app.vehiclerental.utils;

import com.app.vehiclerental.domain.CommandDetails;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputCommandValidator {

    private static final Map<String, Integer> commandParameterCount = Stream.of(new Object[][] {
            { "ADD_BRANCH", 3 },
            { "ADD_VEHICLE", 5 },
            { "BOOK", 5 },
            { "DISPLAY_VEHICLES", 4 },
            { "DROP", 5 }
    }).collect(Collectors.toMap(data -> (String) data[0], data -> (Integer) data[1]));

    public static CommandDetails validateAndGetCommandDetails(String command) {
        String[] splitCommand = command.split(" ");
        if (splitCommand.length == 0
                || !commandParameterCount.containsKey(splitCommand[0])
                || splitCommand.length != commandParameterCount.get(splitCommand[0])
        ) {
            throw new IllegalArgumentException(
                    String.format("Command %s is invalid or incorrect number of arguments passed",
                            command));
        }

        return new CommandDetails(splitCommand[0], splitCommand);
    }
}
