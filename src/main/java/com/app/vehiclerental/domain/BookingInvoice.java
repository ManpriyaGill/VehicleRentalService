package com.app.vehiclerental.domain;

public class BookingInvoice {

    private final double totalPrice;

    private final BookingSlot slot;

    public BookingInvoice(double totalPrice, BookingSlot slot) {
        this.totalPrice = totalPrice;
        this.slot = slot;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return "BookingInvoice{" +
                "totalPrice=" + totalPrice +
                ", vehicleID=" + slot.getVehicleID() +
                ", startTime=" + slot.getStartTime() +
                ", endTime=" + slot.getEndTime() +
                '}';
    }

    public BookingSlot getSlot() {
        return slot;
    }
}
