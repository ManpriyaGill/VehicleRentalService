package com.app.vehiclerental.dao;

import com.app.vehiclerental.api.dao.BranchDAO;
import com.app.vehiclerental.api.domain.Vehicle;
import com.app.vehiclerental.constants.VehicleType;
import com.app.vehiclerental.domain.Branch;
import com.app.vehiclerental.exceptions.BranchNotFoundException;
import com.app.vehiclerental.exceptions.DuplicateBranchException;
import com.app.vehiclerental.exceptions.DuplicateVehicleException;
import com.app.vehiclerental.exceptions.UnsupportedVehicleTypeException;
import com.app.vehiclerental.factory.VehicleFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BranchDAOImpl implements BranchDAO {

    private static final String ALL_BRANCHES = "*";
    Map<String, Branch> branches;
    public BranchDAOImpl() {
        branches  = new HashMap<>();
    }

    public boolean addBranchWithVehicleTypes(String branchName, String[] vehicleTypes)
            throws DuplicateBranchException, IllegalArgumentException{

        if(branches.containsKey(branchName)) {
            throw new DuplicateBranchException(String.format("Trying to add a duplicate branch %s", branchName));
        }
        branches.put(branchName, new Branch(vehicleTypes));
        return true;
    }

    @Override
    public Set<VehicleType> getAllowedVehicleTypesForBranch(String branchName) throws BranchNotFoundException {
        checkIfBranchExists(branchName);
        return branches.get(branchName).getAllowedVehicleTypes();
    }

    @Override
    public boolean addVehicleToBranchIfSupported(String branchName, String vehicleType, String vehicleID, double pricePerHour)
            throws UnsupportedVehicleTypeException, DuplicateVehicleException, BranchNotFoundException {

        verifyVehicleCompatibilityForBranch(branchName, vehicleType);
        verifyIfVehicleExistsInAnyBranch(vehicleID, branchName);

        Branch curBranch = branches.get(branchName);
        Vehicle vehicle = VehicleFactory.getVehicleOfType(vehicleType, vehicleID, pricePerHour);
        curBranch.addVehicle(vehicle);

        return true;
    }

    private void verifyIfVehicleExistsInAnyBranch(String vehicleID, String branchName) throws DuplicateVehicleException, BranchNotFoundException {
        if(doesVehicleBelongToBranch(vehicleID, ALL_BRANCHES)) {
            throw new DuplicateVehicleException(String.format("Cannot add vehicle to branch %s. " +
                    "The branch already contains vehicle %s", branchName, vehicleID));
        }
    }

    @Override
    public List<Vehicle> getVehiclesForBranch(String branchName, String vehicleType) throws BranchNotFoundException {
        List<Vehicle> vehiclesForBranch = getVehiclesForBranch(branchName);
        return vehiclesForBranch.stream()
                .filter(vehicle -> vehicle.getType().equals(VehicleType.valueOf(vehicleType)))
                .collect(Collectors.toList());
    }

    @Override
    public boolean doesVehicleBelongToBranch(String vehicleID, String branchName) throws BranchNotFoundException {
        if(!branchName.equals(ALL_BRANCHES)) {
            checkIfBranchExists(branchName);
            return branches.get(branchName).containsVehicle(vehicleID);
        }

        for(Branch branch : branches.values()) {
            if(branch.containsVehicle(vehicleID)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Vehicle> getVehiclesForBranch(String branchName) throws BranchNotFoundException {
        checkIfBranchExists(branchName);
        return branches.get(branchName).getVehicles();
    }

    private void verifyVehicleCompatibilityForBranch(String branchName, String vehicleType)
            throws UnsupportedVehicleTypeException, BranchNotFoundException {

        Set<VehicleType> allowedVehicleTypes = getAllowedVehicleTypesForBranch(branchName);

        if(!allowedVehicleTypes.contains(VehicleType.valueOf(vehicleType))) {
            throw new UnsupportedVehicleTypeException(
                    String.format("The vehicle type %s is not supported for branch %s", vehicleType, branchName)
            );
        }
    }

    private void checkIfBranchExists(String branchName) throws BranchNotFoundException {
        if(!branches.containsKey(branchName)) {
            throw new BranchNotFoundException(String.format("Provided branch %s doesn't exist", branchName));
        }
    }

}
