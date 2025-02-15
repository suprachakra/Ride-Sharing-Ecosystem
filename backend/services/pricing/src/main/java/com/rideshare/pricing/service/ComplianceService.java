package com.rideshare.pricing.service;

import com.rideshare.pricing.model.FareDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ComplianceService {

    @Value("${pricing.maxSurgeCap:1.5}")
    private double maxSurgeCap;

    /**
     * Applies compliance checks to fare details.
     * If the surge multiplier exceeds the cap, adjusts the fare accordingly.
     */
    public FareDetails applyComplianceChecks(FareDetails details) {
        try {
            if (details.getBreakdown() != null && details.getBreakdown().get("surgeMultiplier") instanceof Number) {
                double surgeMultiplier = ((Number) details.getBreakdown().get("surgeMultiplier")).doubleValue();
                if (surgeMultiplier > maxSurgeCap) {
                    // Adjust the surge multiplier to the cap.
                    details.getBreakdown().put("surgeMultiplier", maxSurgeCap);
                    // Recalculate fare based on new multiplier.
                    double baseFare = ((Number) details.getBreakdown().get("baseFare")).doubleValue();
                    double distanceCharge = ((Number) details.getBreakdown().get("distanceCharge")).doubleValue();
                    double waitingCharge = ((Number) details.getBreakdown().get("waitingCharge")).doubleValue();
                    double newSubtotal = (baseFare + distanceCharge + waitingCharge) * maxSurgeCap;
                    // If discount exists, subtract it.
                    if (details.getBreakdown().containsKey("discount")) {
                        double discount = ((Number) details.getBreakdown().get("discount")).doubleValue();
                        newSubtotal -= discount;
                    }
                    details.setFare(round(newSubtotal));
                }
            }
            return details;
        } catch (Exception e) {
            e.printStackTrace();
            // In case of error, return original details.
            return details;
        }
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
