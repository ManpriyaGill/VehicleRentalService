package com.app.vehiclerental.service;

import com.app.vehiclerental.api.dao.BranchDAO;
import com.app.vehiclerental.api.domain.Vehicle;
import com.app.vehiclerental.api.strategy.VehicleSortingStrategy;
import com.app.vehiclerental.constants.VehicleType;
import com.app.vehiclerental.domain.BookingInvoice;
import com.app.vehiclerental.domain.BookingSlot;
import com.app.vehiclerental.exceptions.BookingNotFoundException;
import com.app.vehiclerental.exceptions.BranchNotFoundException;
import com.app.vehiclerental.exceptions.VehicleNotFoundException;
import com.app.vehiclerental.exceptions.VehicleUnavailableException;
import com.app.vehiclerental.factory.SingletonBranchDAOFactory;
import com.app.vehiclerental.strategy.LeastPriceVehicleSorting;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BookingManager {

    Set<BookingSlot> activeBookings = new HashSet<>();
    VehicleSortingStrategy sortingStrategy = new LeastPriceVehicleSorting(); // this can be dynamic
    BranchDAO branchDAO = SingletonBranchDAOFactory.getBranchDAOInstance("in-memory");

    public BookingInvoice bookBestSuitableVehicleAvailable(String branchName, String vehicleType, int startTime, int endTime)
            throws BranchNotFoundException, VehicleUnavailableException {

        List<Vehicle> vehiclesForBranch = branchDAO.getVehiclesForBranch(branchName, vehicleType);
        sortingStrategy.sortVehicles(vehiclesForBranch);

        /*
            Iterate over the vehicles in the selection order and find the vehicle with available slots
            Note: This assumes you cannot book multiple vehicles
         */
        for (Vehicle vehicle : vehiclesForBranch) {
            BookingSlot slot = new BookingSlot(vehicle.getID(), startTime, endTime);
            if (isAvailableInSlot(slot)) {
                activeBookings.add(slot);
                return getBookingInvoice(slot, vehicle.getPricePerHour());
            }
        }

        throw new VehicleUnavailableException(
                String.format("There are no vehicles available for slot %d to %d", startTime, endTime)
        );
    }

    private BookingInvoice getBookingInvoice(BookingSlot slot, double pricePerHour) {
        double totalPrice = (slot.getEndTime() - slot.getStartTime()) * pricePerHour;
        return new BookingInvoice(totalPrice, slot);
    }

    private boolean isAvailableInSlot(BookingSlot slot) {
        return !activeBookings.contains(slot);
    }

    public List<Vehicle> getAvailableVehiclesForBranch(String branchName, int start, int end) throws BranchNotFoundException {
        List<Vehicle> allVehicles = branchDAO.getVehiclesForBranch(branchName);
        return allVehicles.stream().filter(
                vehicle -> isAvailableInSlot(new BookingSlot(vehicle.getID(), start, end))
        ).collect(Collectors.toList());
    }

    public boolean releaseVehicle(String branchName, String vehicleID, int startTime, int endTime)
            throws BookingNotFoundException, VehicleNotFoundException, BranchNotFoundException {

        checkIfVehicleBelongsToBranch(branchName, vehicleID);
        BookingSlot slotToRelease = activeBookings.stream()
                .filter(
                        bookingSlot -> bookingSlot.getVehicleID().equals(vehicleID)
                                && bookingSlot.getStartTime() == startTime
                                && bookingSlot.getEndTime() == endTime
                ).findFirst().orElseThrow(() -> new BookingNotFoundException(
                        String.format("No booking found for vehicle %s at branch %s for slot %s to %s",
                                vehicleID, branchName, startTime, endTime)));

        return activeBookings.remove(slotToRelease);
    }

    private void checkIfVehicleBelongsToBranch(String branchName, String vehicleID) throws VehicleNotFoundException, BranchNotFoundException{
        if(!branchDAO.doesVehicleBelongToBranch(vehicleID, branchName)) {
            throw new VehicleNotFoundException(String.format("Vehicle %s not found for branch %s", vehicleID, branchName));
        }
    }
}
