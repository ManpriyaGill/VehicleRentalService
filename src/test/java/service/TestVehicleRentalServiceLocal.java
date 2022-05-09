package service;

import api.domain.Vehicle;
import api.service.IVehicleRentalService;
import factory.SingletonBranchDAOFactory;
import domain.vehicles.Bike;
import domain.BookingInvoice;
import domain.vehicles.Car;
import domain.vehicles.Taxi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestVehicleRentalServiceLocal {

    IVehicleRentalService vehicleRentalService;

    @BeforeEach
    void setup() {
        vehicleRentalService = new VehicleRentalService();
    }

    @AfterEach
    void cleanUp() {
        SingletonBranchDAOFactory.resetData("in-memory");
    }

    @Test
    void testAdditionOfBranches() {
        String branchName = "Hyderabad";
        String[] vehicleTypes = {"CAR", "BIKE", "VAN" };

        // add a valid branch
        boolean isBranchAdded = vehicleRentalService.addBranchWithVehicleTypes(branchName, vehicleTypes);
        assertTrue(isBranchAdded);

        // CANNOT add already existing branch
        boolean isDuplicateBranchAdded = vehicleRentalService.addBranchWithVehicleTypes(branchName, vehicleTypes);
        assertFalse(isDuplicateBranchAdded);

    }

    @Test
    void testAdditionOfVehicles() {
        String branchName = "Hyderabad";
        String[] vehicleTypes = {"CAR", "BIKE" };
        vehicleRentalService.addBranchWithVehicleTypes(branchName, vehicleTypes);

        // add a car
        String vehicleType = "CAR";
        String vehicleID = "C001";
        double defaultPricePerHour = 1000;
        boolean isCarAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isCarAdded);

        // add a bike
        vehicleType = "BIKE";
        vehicleID = "B001";
        defaultPricePerHour = 200;
        boolean isBikeAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isBikeAdded);

        // add another car
        vehicleType = "CAR";
        vehicleID = "C002";
        defaultPricePerHour = 2000;
        isCarAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isCarAdded);

        // CANNOT add a duplicate car
        vehicleType = "CAR";
        vehicleID = "C001";
        defaultPricePerHour = 2000;
        isCarAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertFalse(isCarAdded);

        // CANNOT add unsupported vehicle type
        vehicleType = "TAXI";
        vehicleID = "T001";
        defaultPricePerHour = 3000;
        boolean isTaxiAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertFalse(isTaxiAdded);

    }

    @Test
    void testDisplayVehiclesForBranchSortByPrice() {
        String branchName = "Hyderabad";
        String[] vehicleTypes = {"CAR", "BIKE", "TAXI" };
        vehicleRentalService.addBranchWithVehicleTypes(branchName, vehicleTypes);

        // add some vehicles

        String vehicleType = "CAR";
        String vehicleID = "C001";
        double defaultPricePerHour = 1000;
        boolean isCarAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isCarAdded);

        vehicleType = "CAR";
        vehicleID = "C002";
        defaultPricePerHour = 2000;
        isCarAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isCarAdded);

        vehicleType = "BIKE";
        vehicleID = "B001";
        defaultPricePerHour = 200;
        boolean isBikeAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isBikeAdded);

        vehicleType = "TAXI";
        vehicleID = "T001";
        defaultPricePerHour = 3000;
        boolean isTaxiAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isTaxiAdded);

        vehicleType = "TAXI";
        vehicleID = "T002";
        defaultPricePerHour = 900;
        isTaxiAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isTaxiAdded);

        List<Vehicle> expected = List.of(
                new Bike("B001", 200),
                new Taxi("T002", 900),
                new Car("C001", 1000),
                new Car("C002", 2000),
                new Taxi("T001", 3000)
                );

        List<Vehicle> received = vehicleRentalService.getAvailableVehiclesForBranch(branchName, 0, 23);
        assertFalse(received.isEmpty());

        assertIterableEquals(expected, received);

    }

    @Test
    void testBookVehicleForBranch() {
        String branchName = "Chennai";
        String[] vehicleTypes = {"CAR", "BIKE", "VAN"};
        vehicleRentalService.addBranchWithVehicleTypes(branchName, vehicleTypes);

        String vehicleType = "CAR";
        String vehicleID = "C001";
        double defaultPricePerHour = 500;
        boolean isVehicleAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isVehicleAdded);

        vehicleType = "CAR";
        vehicleID = "C002";
        defaultPricePerHour = 1000;
        isVehicleAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isVehicleAdded);

        vehicleType = "BIKE";
        vehicleID = "B001";
        defaultPricePerHour = 250;
        isVehicleAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isVehicleAdded);

        vehicleType = "BIKE";
        vehicleID = "B002";
        defaultPricePerHour = 300;
        isVehicleAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertTrue(isVehicleAdded);

        vehicleType = "TAXI";
        vehicleID = "T001";
        defaultPricePerHour = 2500;
        isVehicleAdded = vehicleRentalService.addVehicleToBranch(branchName, vehicleType, vehicleID, defaultPricePerHour);
        assertFalse(isVehicleAdded);

        // CANNOT book unavailable vehicle type
        double bookingPrice = vehicleRentalService.bookVehicle("Chennai", "VAN", 1, 5).getTotalPrice();
        assertEquals(-1, bookingPrice);

        // book in available slots

        BookingInvoice carInvoice = vehicleRentalService.bookVehicle("Chennai", "CAR", 1, 3);
        bookingPrice = carInvoice.getTotalPrice();
        assertEquals(1000, bookingPrice);

        bookingPrice = vehicleRentalService.bookVehicle("Chennai", "BIKE", 2, 3).getTotalPrice();
        assertEquals(250, bookingPrice);

        bookingPrice = vehicleRentalService.bookVehicle("Chennai", "BIKE", 2, 5).getTotalPrice();
        assertEquals(900, bookingPrice);

        List<Vehicle> expected = new ArrayList<>();
        expected.add(new Car("C002", 1000));
        List<Vehicle> availableVehicles = vehicleRentalService.getAvailableVehiclesForBranch("Chennai", 1, 5);

        assertIterableEquals(expected, availableVehicles);

        /*boolean isVehicleDropped = vehicleRentalService.dropVehicleAtBranch("Chennai", carInvoice);
        assertTrue(isVehicleDropped);

        expected.add(new Car("C001", 500));
        expected.sort(Comparator.comparingDouble(Vehicle::getPricePerHour));
        availableVehicles = vehicleRentalService.getAvailableVehiclesForBranch("Chennai", 1, 5);
        assertIterableEquals(expected, availableVehicles);*/

    }
}
