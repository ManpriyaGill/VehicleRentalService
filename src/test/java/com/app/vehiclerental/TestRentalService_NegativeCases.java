package com.app.vehiclerental;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class TestRentalService_NegativeCases extends AbstractTestBase{

    @Test
    public void testBranchNotFound() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,VAN",
                "ADD_VEHICLE B2 CAR V1 200"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "FALSE"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testUnsupportedVehicleType() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_BRANCH B2 CAR,VAN,BUS",
                "ADD_VEHICLE B1 VAN V1 100",
                "ADD_VEHICLE B2 BIKE V2 1000"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "FALSE",
                "FALSE"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testInvalidPriceForVehicle() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_VEHICLE B1 VAN V1 -100"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "FALSE"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testUnableToBook_InvalidBookingTimes() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 VAN",
                "ADD_VEHICLE B1 VAN V1 100",
                "BOOK B1 VAN 5 1",
                "BOOK B1 VAN -1 20",
                "BOOK B1 VAN 1 25"

        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "-1",
                "-1",
                "-1"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testDuplicateBranches() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_BRANCH B1 CAR,BIKE,BUS"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "FALSE"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }


    @Test
    public void testUnsuccessfulBookingOfVehicle_UnsupportedVehicleType() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_VEHICLE B1 BIKE V1 1000",
                "BOOK B1 VAN 1 5"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "-1"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testUnsuccessfulBookingOfVehicle_VehicleNotAvailable() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_VEHICLE B1 BIKE V1 1000",
                "ADD_VEHICLE B1 CAR V2 2000",
                "BOOK B1 BIKE 1 5",
                "BOOK B1 BIKE 1 5"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "TRUE",
                "4000",
                "-1"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testSameVehicleCannotBeAddedToDifferentBranches() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_BRANCH B2 CAR",
                "ADD_VEHICLE B1 CAR V1 1000",
                "ADD_VEHICLE B1 CAR V1 1000"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "TRUE",
                "FALSE"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testUnsuccessfulBookingOfVehicle_UnsupportedBranch() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_VEHICLE B1 BIKE V1 1000",
                "BOOK B2 VAN 1 5"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "-1"
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testDisplayBookingOfVehicle_UnsupportedBranch() {
        List<String> commands = Arrays.asList(
                "ADD_BRANCH B1 CAR,BIKE,BUS",
                "ADD_VEHICLE B1 BIKE V1 1000",
                "BOOK B1 BIKE 3 4",
                "DISPLAY_VEHICLES B2 3 5"
        );
        List<String> expectedResult = Arrays.asList(
                "TRUE",
                "TRUE",
                "1000",
                ""
        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testDropVehicle_UnsupportedBranch() {
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
                "DROP B2 V4 2 5",
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
                "FALSE",
                "V1,V3"

        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }

    @Test
    public void testCannotDropVehicle_OutOfRangeBookingTimes() {
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
                "DROP B1 V4 2 7",
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
                "FALSE",
                "V1,V3"

        );
        List<String> actualResult = VehicleRentalDriver.runApplicationCommands(commands);
        Assertions.assertIterableEquals(expectedResult, actualResult);
    }
}
