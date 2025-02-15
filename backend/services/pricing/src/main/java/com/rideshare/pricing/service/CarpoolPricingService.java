package com.rideshare.pricing.service;

import com.rideshare.pricing.dto.FareRequest;
import com.rideshare.pricing.model.FareDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CarpoolPricingService {

    @Value("${pricing.perKmRate:0.60}")
    private double perKmRate;

    @Value("${pricing.waitingCharge:0.15}")
    private double waitingCharge;

    @Value("${pricing.carpoolDiscount:0.25}")
    private double carpoolDiscount;

    @Value("${pricing.maxFarePerRider:20.00}")
    private double maxFarePerRider;

    /**
     * Computes fare details for a carpool ride.
     */
    public FareDetails computeCarpoolFare(FareRequest request, double surgeMultiplier) {
        if (request.getNumRiders() == null || request.getNumRiders() <= 0) {
            throw new IllegalArgumentException("Number of riders must be greater than 0");
        }
        // Calculate components.
        double distanceCharge = request.getDistance() * perKmRate;
        double waitingChargeTotal = request.getWaitingTime() * waitingCharge;
        double subtotal = (request.getBaseFare() + distanceCharge + waitingChargeTotal) * surgeMultiplier;

        // Discount is applied on the base fare portion.
        double discount = request.getBaseFare() * carpoolDiscount;
        double discountedFare = subtotal - discount;

        // Calculate fare per rider.
        double farePerRider = discountedFare / request.getNumRiders();
        if (farePerRider > maxFarePerRider) {
            farePerRider = maxFarePerRider;
        }

        // Prepare breakdown.
        Map<String, Object> breakdown = new HashMap<>();
        breakdown.put("baseFare", request.getBaseFare());
        breakdown.put("distanceCharge", round(distanceCharge));
        breakdown.put("waitingCharge", round(waitingChargeTotal));
        breakdown.put("surgeMultiplier", surgeMultiplier);
        breakdown.put("discount", round(discount));

        FareDetails details = new FareDetails();
        details.setFare(round(discountedFare));
        details.setFarePerRider(round(farePerRider));
        details.setBreakdown(breakdown);
        return details;
    }

    /**
     * For PRIVATE rides: calculates fare without splitting.
     */
    public FareDetails computePrivateFare(FareRequest request, double surgeMultiplier) {
        double distanceCharge = request.getDistance() * perKmRate;
        double waitingChargeTotal = request.getWaitingTime() * waitingCharge;
        double fare = (request.getBaseFare() + distanceCharge + waitingChargeTotal) * surgeMultiplier;

        Map<String, Object> breakdown = new HashMap<>();
        breakdown.put("baseFare", request.getBaseFare());
        breakdown.put("distanceCharge", round(distanceCharge));
        breakdown.put("waitingCharge", round(waitingChargeTotal));
        breakdown.put("surgeMultiplier", surgeMultiplier);

        FareDetails details = new FareDetails();
        details.setFare(round(fare));
        details.setBreakdown(breakdown);
        return details;
    }

    private double round(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
