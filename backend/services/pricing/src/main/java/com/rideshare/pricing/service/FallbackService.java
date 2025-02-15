package com.rideshare.pricing.service;

import com.rideshare.pricing.dto.FareRequest;
import com.rideshare.pricing.model.RiskMetrics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FallbackService {

    @Value("${pricing.fallback.maxMarginDip:0.05}")
    private double maxMarginDip;

    @Value("${pricing.fallback.minOccupancyRate:1.1}")
    private double minOccupancyRate;

    /**
     * Determines whether fallback logic should be triggered based on risk metrics.
     */
    public boolean shouldFallback(FareRequest request, RiskMetrics metrics) {
        try {
            if (metrics.getMarginDip() > maxMarginDip || metrics.getOccupancyRate() < minOccupancyRate) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            // On error, trigger fallback conservatively.
            return true;
        }
    }
}
