package api.dao;

import api.domain.Vehicle;
import constants.VehicleType;
import exceptions.BranchNotFoundException;
import exceptions.DuplicateBranchException;
import exceptions.DuplicateVehicleException;
import exceptions.UnsupportedVehicleTypeException;

import java.util.List;
import java.util.Set;

public interface BranchDAO {
    boolean addBranchWithVehicleTypes(String branchName, String[] vehicleTypes)
            throws DuplicateBranchException, IllegalArgumentException;

    Set<VehicleType> getAllowedVehicleTypesForBranch(String branchName) throws BranchNotFoundException;

    boolean addVehicleToBranchIfSupported(String branchName, String vehicleType, String vehicleID, double pricePerHour)
            throws UnsupportedVehicleTypeException, DuplicateVehicleException, BranchNotFoundException;

    List<Vehicle> getVehiclesForBranch(String branchName) throws BranchNotFoundException;

    List<Vehicle> getVehiclesForBranch(String branchName, String vehicleType) throws BranchNotFoundException;
}
