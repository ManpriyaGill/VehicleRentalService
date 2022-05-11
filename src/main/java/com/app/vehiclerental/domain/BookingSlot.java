package com.app.vehiclerental.domain;

import java.util.Objects;

public class BookingSlot {

    private final String vehicleID;
    private final int startTime;
    private final int endTime;

    public BookingSlot(String vehicleID, int startTime, int endTime) {
        this.vehicleID = vehicleID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleID);
    }

    /*
        This returns true if
            1. Two slots are equal
            2. Two slots overlap

            All the overlapping conditions, examples:

            1-----7
            1-3
             2---6 => this
              3---7
             2---6
              3-5
     */

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof BookingSlot) {
            BookingSlot that = (BookingSlot)obj;
            return  this.vehicleID.equals(that.vehicleID) && doesBookingTimeOverlap(that);
        }
        return false;
    }
    private boolean doesBookingTimeOverlap(BookingSlot that) {
        return this.startTime == that.startTime && this.endTime == that.endTime
                || (this.startTime < that.endTime && that.startTime < this.startTime)
                || (this.endTime > that.startTime && that.endTime > this.startTime)
                || (this.startTime < that.startTime && this.endTime > that.endTime)
                || (this.startTime > that.startTime && this.endTime < that.endTime);
    }

    public int getStartTime() {
        return startTime;
    }

    public String getVehicleID() {
        return vehicleID;
    }

    public int getEndTime() {
        return endTime;
    }
}
