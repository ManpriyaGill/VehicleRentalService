package executors;

import java.util.logging.Level;

public class BookVehicleCommandExecutor extends AbstractCommandExecutor {
    @Override
    public void executeCommand(String[] splitCommand) {

        String branchName = splitCommand[1];
        String vehicleType = splitCommand[2];
        int startTime = Integer.parseInt(splitCommand[3]);
        int endTime = Integer.parseInt(splitCommand[4]);

        double bookingPrice = rentalService.bookVehicle(branchName, vehicleType, startTime, endTime).getTotalPrice();

//        printPlainOutput(bookingPrice);
        printLog(bookingPrice, branchName, vehicleType, startTime, endTime);
    }

    private void printPlainOutput(double bookingPrice) {
        System.out.println((int)bookingPrice);
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
