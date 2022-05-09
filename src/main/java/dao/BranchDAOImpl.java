package dao;

import api.dao.BranchDAO;
import api.domain.Vehicle;
import constants.VehicleType;
import factory.VehicleFactory;
import domain.Branch;
import exceptions.BranchNotFoundException;
import exceptions.DuplicateBranchException;
import exceptions.DuplicateVehicleException;
import exceptions.UnsupportedVehicleTypeException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class BranchDAOImpl implements BranchDAO {

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

        Branch curBranch = branches.get(branchName);
        if(curBranch.containsVehicle(vehicleID)) {
            throw new DuplicateVehicleException(String.format("Cannot add vehicle to branch %s. " +
                    "The branch already contains vehicle %s", branchName, vehicleID));
        }

        Vehicle vehicle = VehicleFactory.getVehicleOfType(vehicleType, vehicleID, pricePerHour);
        curBranch.addVehicle(vehicle);

        return true;
    }

    @Override
    public List<Vehicle> getVehiclesForBranch(String branchName) throws BranchNotFoundException {
        checkIfBranchExists(branchName);
        return branches.get(branchName).getVehicles();
    }

    @Override
    public List<Vehicle> getVehiclesForBranch(String branchName, String vehicleType) throws BranchNotFoundException {
        List<Vehicle> vehiclesForBranch = getVehiclesForBranch(branchName);
        return vehiclesForBranch.stream()
                .filter(vehicle -> vehicle.getType().equals(VehicleType.valueOf(vehicleType)))
                .collect(Collectors.toList());
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
