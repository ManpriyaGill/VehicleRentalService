package com.app.vehiclerental.api.domain;

import com.app.vehiclerental.constants.VehicleType;

public abstract class Vehicle {
    protected String vehicleID;
    protected double pricePerHour;
    protected VehicleType type;

    public Vehicle(String vehicleID, double pricePerHour) {
        this.vehicleID = vehicleID;
        this.pricePerHour = pricePerHour;
    }

    public String getID() {
        return vehicleID;
    }

    public double getPricePerHour() {
        return pricePerHour;
    }

    public VehicleType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleID='" + vehicleID + '\'' +
                ", price=" + pricePerHour +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Vehicle) {
            Vehicle that = (Vehicle) obj;
            return that.vehicleID.equals(this.vehicleID) && that.pricePerHour == this.pricePerHour;
        }
        return false;
    }
}
