package service;

import api.dao.BranchDAO;
import api.domain.Vehicle;
import api.strategy.VehicleSortingStrategy;
import factory.SingletonBranchDAOFactory;
import domain.BookingInvoice;
import domain.BookingSlot;
import exceptions.BranchNotFoundException;
import exceptions.VehicleUnavailableException;
import strategy.LeastPriceVehicleSorting;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookingManager {

    Set<BookingSlot> activeBookings = new HashSet<>();
    VehicleSortingStrategy sortingStrategy = new LeastPriceVehicleSorting();
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

    // TODO:
    public boolean releaseVehicle(String branchName, BookingSlot slot) {
        // verify branch
        return activeBookings.remove(slot);
    }
}
