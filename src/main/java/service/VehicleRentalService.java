package service;

import api.dao.BranchDAO;
import api.domain.Vehicle;
import api.service.IVehicleRentalService;
import factory.SingletonBranchDAOFactory;
import domain.BookingInvoice;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VehicleRentalService implements IVehicleRentalService {

    // this ideally is injected in the constructor
    BranchDAO branchDAO = SingletonBranchDAOFactory.getBranchDAOInstance("in-memory");
    BookingManager bookingManager = new BookingManager();

    private final Logger logger = Logger.getLogger(VehicleRentalService.class.getName());

    @Override
    public boolean addBranchWithVehicleTypes(String branchName, String[] vehicleTypes) {
        try {
            return branchDAO.addBranchWithVehicleTypes(branchName, vehicleTypes);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public boolean addVehicleToBranch(String branchName, String vehicleType, String vehicleID, double pricePerHour) {

        try {
            return branchDAO.addVehicleToBranchIfSupported(branchName, vehicleType, vehicleID, pricePerHour);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return false;
        }
    }

    @Override
    public List<Vehicle> getAvailableVehiclesForBranch(String branchName, int startTime, int endTime) {
        try {
            List<Vehicle> vehicles = bookingManager.getAvailableVehiclesForBranch(branchName, startTime, endTime);
            vehicles.sort(Comparator.comparingDouble(Vehicle::getPricePerHour));
            return vehicles;

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return Collections.emptyList();
        }

    }

    @Override
    public BookingInvoice bookVehicle(String branchName, String vehicleType, int startTime, int endTime) {
        try {
            BookingInvoice invoice = bookingManager.bookBestSuitableVehicleAvailable(
                    branchName, vehicleType, startTime, endTime
            );
            logger.info(invoice.toString() + "\n");
            return invoice;
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            return new BookingInvoice(-1, null);
        }
    }

    @Override
    public boolean dropVehicleAtBranch(String branchName, BookingInvoice invoice) {
        // TODO: some validations and exceptions
        return bookingManager.releaseVehicle(branchName, invoice.getSlot());
    }

}
