package com.rideshare.booking.dto;

public class BookingRequest {
    private String riderId;
    private String tripType; // "PRIVATE" or "CARPOOL"
    private double pickupLatitude;
    private double pickupLongitude;
    private double dropoffLatitude;
    private double dropoffLongitude;
    // Additional fields such as booking time can be added.

    // Getters and setters
    public String getRiderId() {
        return riderId;
    }
    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }
    public String getTripType() {
        return tripType;
    }
    public void setTripType(String tripType) {
        this.tripType = tripType;
    }
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
