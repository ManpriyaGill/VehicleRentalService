package com.app.vehiclerental.api.dao;

import com.app.vehiclerental.api.domain.Vehicle;
import com.app.vehiclerental.constants.VehicleType;
import com.app.vehiclerental.exceptions.BranchNotFoundException;
import com.app.vehiclerental.exceptions.DuplicateBranchException;
import com.app.vehiclerental.exceptions.DuplicateVehicleException;
import com.app.vehiclerental.exceptions.UnsupportedVehicleTypeException;

import java.util.List;
import java.util.Set;

public interface BranchDAO {
    boolean addBranchWithVehicleTypes(String branchName, String[] vehicleTypes)
            throws DuplicateBranchException, IllegalArgumentException;

    Set<VehicleType> getAllowedVehicleTypesForBranch(String branchName) throws BranchNotFoundException;

    boolean addVehicleToBranchIfSupported(String branchName, String vehicleType, String vehicleID, double pricePerHour)
            throws UnsupportedVehicleTypeException, DuplicateVehicleException, BranchNotFoundException;

    boolean doesVehicleBelongToBranch(String vehicleID, String branchName) throws BranchNotFoundException;

    List<Vehicle> getVehiclesForBranch(String branchName) throws BranchNotFoundException;

    List<Vehicle> getVehiclesForBranch(String branchName, String vehicleType) throws BranchNotFoundException;

}
