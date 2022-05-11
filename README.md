# VehicleRental
Rent different kinds of vehicles such as cars and bikes.

### Run the application

`java -jar VehicleRental-latest-all.jar relative/path/to/input_file`

If no input file is passed, default file is picked up from [here](src/main/resources/inputs.txt) 

You can pick the attached jar file or build your own jar. 


### Build the jar (optional)
From the project folder run the command

`./gradlew shadowJar`

This generates a jar `VehicleRental-latest-all.jar` at the location `build/libs`

### Running tests

`./gradlew test [-i]` 

This runs the tests from [here](src/test/java/com/app/vehiclerental) and produces a test report.

`-i` is optional; additionally, prints logs in the console.

### Note
This application also logs appropriate exceptions(as shown [here](src/main/java/com/app/vehiclerental/exceptions)) wherever applicable.


