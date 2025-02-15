package com.rideshare.pricing.model;

public class RiskMetrics {
    private double occupancyRate;  // e.g., average number of riders per car (should be >1.1 for profitable carpooling)
    private double marginDip;      // e.g., percentage dip in target margin

    // Constructors, getters, setters
    public RiskMetrics() {}

    public RiskMetrics(double occupancyRate, double marginDip) {
        this.occupancyRate = occupancyRate;
        this.marginDip = marginDip;
    }

    public double getOccupancyRate() {
        return occupancyRate;
    }
    public void setOccupancyRate(double occupancyRate) {
        this.occupancyRate = occupancyRate;
    }
    public double getMarginDip() {
        return marginDip;
    }
    public void setMarginDip(double marginDip) {
        this.marginDip = marginDip;
    }
}
