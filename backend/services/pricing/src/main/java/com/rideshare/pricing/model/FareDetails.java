package com.rideshare.pricing.model;

import java.util.Map;

public class FareDetails {
    private Double fare;              // Total fare (for private ride) or discounted total fare for carpool
    private Double farePerRider;      // For carpool rides
    private Map<String, Object> breakdown;  // Detailed fare breakdown
    private boolean fallbackTriggered;

    // Getters and setters
    public Double getFare() {
        return fare;
    }
    public void setFare(Double fare) {
        this.fare = fare;
    }
    public Double getFarePerRider() {
        return farePerRider;
    }
    public void setFarePerRider(Double farePerRider) {
        this.farePerRider = farePerRider;
    }
    public Map<String, Object> getBreakdown() {
        return breakdown;
    }
    public void setBreakdown(Map<String, Object> breakdown) {
        this.breakdown = breakdown;
    }
    public boolean isFallbackTriggered() {
        return fallbackTriggered;
    }
    public void setFallbackTriggered(boolean fallbackTriggered) {
        this.fallbackTriggered = fallbackTriggered;
    }
}
