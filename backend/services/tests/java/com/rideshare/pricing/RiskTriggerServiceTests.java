package com.rideshare.pricing.service;

import com.rideshare.pricing.model.RiskMetrics;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RiskTriggerServiceTests {

    private final RiskTriggerService riskTriggerService = new RiskTriggerService();

    @Test
    public void testGetCurrentMetrics() {
        RiskMetrics metrics = riskTriggerService.getCurrentMetrics();
        assertNotNull(metrics, "RiskMetrics should not be null");
        assertTrue(metrics.getOccupancyRate() > 0, "Occupancy rate should be defined");
        assertNotNull(metrics.getMarginDip(), "Margin dip should be defined");
    }
}
