package com.rideshare.pricing.service;

import com.rideshare.pricing.model.RiskMetrics;
import org.springframework.stereotype.Service;

@Service
public class RiskTriggerService {

    /**
     * Retrieves current risk metrics.
     * In production, this would integrate with a monitoring system.
     * Here, we simulate with fixed values.
     */
    public RiskMetrics getCurrentMetrics() {
        try {
            // Simulated risk metrics:
            double occupancyRate = 1.2;  // Should be above 1.1 for healthy carpooling
            double marginDip = 0.03;     // 3% dip (acceptable)
            return new RiskMetrics(occupancyRate, marginDip);
        } catch (Exception e) {
            e.printStackTrace();
            // Return safe defaults in case of error.
            return new RiskMetrics(1.0, 0.1);
        }
    }
}
