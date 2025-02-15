package com.rideshare.pricing.dto;

public class FareRequest {
    private String tripType;  // "PRIVATE" or "CARPOOL"
    private Double baseFare;
    private Double distance;      // in kilometers
    private Double waitingTime;   // in minutes
    private Integer demand;
    private Integer supply;
    private Integer numRiders;    // For carpool rides

    // Getters and setters
    public String getTripType() {
        return tripType;
    }
    public void setTripType(String tripType) {
        this.tripType = tripType;
    }
    public Double getBaseFare() {
        return baseFare;
    }
    public void setBaseFare(Double baseFare) {
        this.baseFare = baseFare;
    }
    public Double getDistance() {
        return distance;
    }
    public void setDistance(Double distance) {
        this.distance = distance;
    }
    public Double getWaitingTime() {
        return waitingTime;
    }
    public void setWaitingTime(Double waitingTime) {
        this.waitingTime = waitingTime;
    }
    public Integer getDemand() {
        return demand;
    }
    public void setDemand(Integer demand) {
        this.demand = demand;
    }
    public Integer getSupply() {
        return supply;
    }
    public void setSupply(Integer supply) {
        this.supply = supply;
    }
    public Integer getNumRiders() {
        return numRiders;
    }
    public void setNumRiders(Integer numRiders) {
        this.numRiders = numRiders;
    }
}
