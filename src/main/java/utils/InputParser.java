package utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class InputParser {

    private static final Logger logger = Logger.getLogger(InputParser.class.getName());

    public static List<String> getInputFromFile() {
        List<String> inputCommands = new ArrayList<>();
        try {
            String fileName = "src/main/resources/inputs.txt";
            try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
                stream.forEach(input -> {
                    try {
                        if (input.startsWith("#")) {
                            logger.info("Skipping: " + input);
                        } else {
                            inputCommands.add(input);
                        }
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "\tUnable to process input: " + input, e);
                        System.out.println(e.getMessage());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return inputCommands;
    }
}
