package com.rideshare.pricing.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SurgeService {

    // Surge factor from configuration (e.g., 0.1)
    @Value("${pricing.surge.factor:0.1}")
    private double surgeFactor;

    /**
     * Computes the surge multiplier based on current demand and supply.
     * If demand exceeds supply, surge = 1 + (demand - supply) * factor.
     * Otherwise, returns 1.0.
     */
    public double computeSurge(int demand, int supply) {
        try {
            double multiplier = 1.0;
            if (demand > supply) {
                multiplier += (demand - supply) * surgeFactor;
            }
            return Math.round(multiplier * 100.0) / 100.0;
        } catch (Exception e) {
            // Log the error and return default surge multiplier.
            e.printStackTrace();
            return 1.0;
        }
    }
}
