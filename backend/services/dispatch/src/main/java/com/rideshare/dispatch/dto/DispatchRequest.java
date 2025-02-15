package com.rideshare.dispatch.dto;

public class DispatchRequest {
    private double pickupLatitude;
    private double pickupLongitude;
    private double dropoffLatitude;
    private double dropoffLongitude;
    // Additional fields (e.g., rider ID) can be added as needed.

    // Getters and setters
    public double getPickupLatitude() {
        return pickupLatitude;
    }
    public void setPickupLatitude(double pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
    }
    public double getPickupLongitude() {
        return pickupLongitude;
    }
    public void setPickupLongitude(double pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
    }
    public double getDropoffLatitude() {
        return dropoffLatitude;
    }
    public void setDropoffLatitude(double dropoffLatitude) {
        this.dropoffLatitude = dropoffLatitude;
    }
    public double getDropoffLongitude() {
        return dropoffLongitude;
    }
    public void setDropoffLongitude(double dropoffLongitude) {
        this.dropoffLongitude = dropoffLongitude;
    }
}
