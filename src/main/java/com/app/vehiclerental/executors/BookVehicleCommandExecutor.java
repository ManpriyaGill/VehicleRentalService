package com.app.vehiclerental.executors;

import java.util.logging.Level;

public class BookVehicleCommandExecutor extends AbstractCommandExecutor {
    @Override
    public String executeCommand(String[] splitCommand) {

        String branchName = splitCommand[1];
        String vehicleType = splitCommand[2];
        int startTime = Integer.parseInt(splitCommand[3]);
        int endTime = Integer.parseInt(splitCommand[4]);
        double bookingPrice = -1;

        try {
            validateBookingTimes(startTime, endTime);
            bookingPrice = rentalService.bookVehicle(branchName, vehicleType, startTime, endTime).getTotalPrice();
            printLog(bookingPrice, branchName, vehicleType, startTime, endTime);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }

        return Integer.toString((int)bookingPrice);
    }

    private void validateBookingTimes(int startTime, int endTime) {
        if(startTime >= endTime || endTime < 1 || endTime > 24 || startTime < 1) {
            throw new IllegalArgumentException("Invalid startTime and endTime for the booking");
        }
    }

    private void printLog(double bookingPrice, String branchName, String vehicleType,
                          int startTime, int endTime) {
        if(bookingPrice == -1) {
            logger.log(
                    Level.SEVERE,
                    String.format("Unable to book %s for the slot %d to %d in branch %s\n",
                            vehicleType, startTime, endTime, branchName)
            );
        } else {
            logger.log(
                    Level.INFO,
                    String.format("Booked a %s for the slot %d to %d in branch %s. Booking price: %.2f\n",
                            vehicleType, startTime, endTime, branchName, bookingPrice)
            );
        }
    }
}
