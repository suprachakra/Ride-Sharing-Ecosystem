package com.rideshare.pricing.service;

import com.rideshare.pricing.dto.FareRequest;
import com.rideshare.pricing.model.RiskMetrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FallbackServiceTests {

    private final FallbackService fallbackService = new FallbackService();

    @Test
    public void testShouldFallbackWhenMarginDipHigh() {
        FareRequest request = new FareRequest();
        RiskMetrics metrics = new RiskMetrics(1.2, 0.06);
        boolean result = fallbackService.shouldFallback(request, metrics);
        assertTrue(result, "Fallback should trigger when margin dip is high");
    }

    @Test
    public void testShouldNotFallbackWhenMetricsNormal() {
        FareRequest request = new FareRequest();
        RiskMetrics metrics = new RiskMetrics(1.2, 0.03);
        boolean result = fallbackService.shouldFallback(request, metrics);
        assertFalse(result, "Fallback should not trigger when metrics are within thresholds");
    }
}
