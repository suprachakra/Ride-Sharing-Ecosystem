package com.rideshare.compliance.service;

import com.rideshare.compliance.dto.ComplianceRequest;
import com.rideshare.compliance.dto.ComplianceResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ComplianceServiceTests {

    private final ComplianceService complianceService = new ComplianceService();

    @Test
    public void testCheckComplianceWithinLimit() {
        ComplianceRequest request = new ComplianceRequest();
        request.setRideId("RIDE123");
        request.setSurgeMultiplier(1.4);
        ComplianceResponse response = complianceService.checkCompliance(request);
        assertTrue(response.isCompliant());
    }

    @Test
    public void testCheckComplianceExceedsLimit() {
        ComplianceRequest request = new ComplianceRequest();
        request.setRideId("RIDE123");
        request.setSurgeMultiplier(1.6);
        ComplianceResponse response = complianceService.checkCompliance(request);
        assertFalse(response.isCompliant());
        assertTrue(response.getMessage().contains("exceeds maximum allowed"));
    }
}
