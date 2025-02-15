package com.rideshare.pricing.service;

import com.rideshare.pricing.dto.FareRequest;
import com.rideshare.pricing.model.FareDetails;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CarpoolPricingServiceTests {

    private final CarpoolPricingService carpoolPricingService = new CarpoolPricingService();

    @Test
    public void testComputeCarpoolFare() {
        FareRequest request = new FareRequest();
        request.setTripType("CARPOOL");
        request.setBaseFare(2.00);
        request.setDistance(10.0);
        request.setWaitingTime(5.0);
        request.setNumRiders(2);
        // Demand and supply values are not used in fare calculation here.
        request.setDemand(60);
        request.setSupply(50);

        double surgeMultiplier = 1.2;
        FareDetails details = carpoolPricingService.computeCarpoolFare(request, surgeMultiplier);

        assertNotNull(details, "FareDetails should not be null");
        assertTrue(details.getFarePerRider() > 0, "Fare per rider should be positive");
    }
}
