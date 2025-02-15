package com.rideshare.pricing.service;

import com.rideshare.pricing.model.FareDetails;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

public class ComplianceServiceTests {

    private final ComplianceService complianceService = new ComplianceService();

    @Test
    public void testApplyComplianceChecksCapsSurgeMultiplier() {
        FareDetails details = new FareDetails();
        Map<String, Object> breakdown = new HashMap<>();
        breakdown.put("baseFare", 2.00);
        breakdown.put("distanceCharge", 6.00);
        breakdown.put("waitingCharge", 0.75);
        breakdown.put("surgeMultiplier", 2.0); // Exceeds cap of 1.5
        details.setBreakdown(breakdown);
        details.setFare(50.0);

        FareDetails adjusted = complianceService.applyComplianceChecks(details);
        double adjustedSurge = ((Number) adjusted.getBreakdown().get("surgeMultiplier")).doubleValue();
        assertTrue(adjustedSurge <= 1.5, "Surge multiplier should be capped at or below 1.5");
    }
}
