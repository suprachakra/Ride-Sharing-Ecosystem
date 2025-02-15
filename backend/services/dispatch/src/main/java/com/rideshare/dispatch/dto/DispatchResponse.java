package com.rideshare.dispatch.dto;

public class DispatchResponse {
    private String driverId;
    private String vehicleId;
    private double estimatedArrivalTime; // In minutes
    private String message;

    // Getters and setters
    public String getDriverId() {
        return driverId;
    }
    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }
    public String getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }
    public double getEstimatedArrivalTime() {
        return estimatedArrivalTime;
    }
    public void setEstimatedArrivalTime(double estimatedArrivalTime) {
        this.estimatedArrivalTime = estimatedArrivalTime;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
