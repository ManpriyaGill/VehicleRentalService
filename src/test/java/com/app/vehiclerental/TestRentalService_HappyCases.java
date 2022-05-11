package com.app.vehiclerental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestRentalService_HappyCases extends AbstractTestBase{

    @Test
    public void testMixedUseCaseFromProblemStatement() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,VAN",
                "ADD_VEHICLE B1 CAR V1 500",
                "ADD_VEHICLE B1 CAR V2 1000",
                "ADD_VEHICLE B1 BIKE V3 250",
                "ADD_VEHICLE B1 BIKE V4 300",
                "ADD_VEHICLE B1 BUS V5 2500",
                "BOOK B1 VAN 1 5",
                "BOOK B1 CAR 1 3",
                "BOOK B1 BIKE 2 3",
                "BOOK B1 BIKE 2 5", // V4
                "DISPLAY_VEHICLES B1 1 5",
                "DROP B1 V4 2 5",
                "DISPLAY_VEHICLES B1 1 5"

        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "FALSE",
                "-1",
                "1000",
                "250",
                "900",
                "V2",
                "TRUE",
                "V4,V2"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testSuccessfulAdditionOfBranches() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_BRANCH B2 CAR,VAN,BUS"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testSuccessfulAdditionOfVehicles() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_BRANCH B2 CAR,VAN,BUS",
                "ADD_VEHICLE B1 BIKE V1 100",
                "ADD_VEHICLE B2 CAR V2 1000"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testSuccessfulBookingOfVehicle() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_VEHICLE B1 BIKE V1 1000",
                "ADD_VEHICLE B1 CAR V2 2000",
                "BOOK B1 BIKE 1 5",
                "BOOK B1 CAR 1 5"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "TRUE",
                "4000",
                "8000"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testDisplayAvailableVehiclesForBranch_OrderByPrice() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE",
                "ADD_VEHICLE B1 BIKE V1 500",
                "ADD_VEHICLE B1 BIKE V2 200",
                "ADD_VEHICLE B1 CAR V3 1000",
                "ADD_VEHICLE B1 CAR V4 2000",
                "BOOK B1 CAR 1 3",
                "BOOK B1 BIKE 5 7",
                "BOOK B1 CAR 2 5",
                "DISPLAY_VEHICLES B1 3 6",
                "DISPLAY_VEHICLES B1 2 6"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "2000", // V3
                "400", // V2
                "6000", // V4
                "V1,V3",
                "V1"

        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testDropVehicleAtBranch() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE",
                "ADD_VEHICLE B1 BIKE V1 500",
                "ADD_VEHICLE B1 BIKE V2 200",
                "ADD_VEHICLE B1 CAR V3 1000",
                "ADD_VEHICLE B1 CAR V4 2000",
                "BOOK B1 CAR 1 3", // V3
                "BOOK B1 BIKE 5 7", // V2
                "BOOK B1 CAR 2 5", //  V4
                "DISPLAY_VEHICLES B1 3 6",
                "DROP B1 V4 2 5",
                "DISPLAY_VEHICLES B1 3 6"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "TRUE",
                "2000",
                "400",
                "6000",
                "V1,V3",
                "TRUE",
                "V1,V3,V4"

        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }
}
