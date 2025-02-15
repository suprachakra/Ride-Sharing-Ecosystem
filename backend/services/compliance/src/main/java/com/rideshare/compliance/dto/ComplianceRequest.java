package com.rideshare.compliance.dto;

public class ComplianceRequest {
    private String rideId;
    private double surgeMultiplier;
    // Additional fields can include location or ride details.

    // Getters and setters
    public String getRideId() {
        return rideId;
    }
    public void setRideId(String rideId) {
        this.rideId = rideId;
    }
    public double getSurgeMultiplier() {
        return surgeMultiplier;
    }
    public void setSurgeMultiplier(double surgeMultiplier) {
        this.surgeMultiplier = surgeMultiplier;
    }
}
