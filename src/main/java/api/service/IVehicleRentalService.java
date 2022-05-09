package api.service;

import api.domain.Vehicle;
import domain.BookingInvoice;

import java.util.List;

public interface IVehicleRentalService {

    boolean addBranchWithVehicleTypes(String branchName, String... vehicleTypes);

    boolean addVehicleToBranch(String branchName, String vehicleType, String vehicleID, double pricePerHour);

    List<Vehicle> getAvailableVehiclesForBranch(String branchName, int startTime, int endTime);

    BookingInvoice bookVehicle(String branchName, String vehicleType, int startTime, int endTime);

    boolean dropVehicleAtBranch(String branchName, BookingInvoice invoice);
}
