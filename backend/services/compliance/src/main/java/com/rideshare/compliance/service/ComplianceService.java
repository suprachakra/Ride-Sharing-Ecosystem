package com.rideshare.compliance.service;

import com.rideshare.compliance.dto.ComplianceRequest;
import com.rideshare.compliance.dto.ComplianceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ComplianceService {

    @Value("${compliance.maxSurgeCap:1.5}")
    private double maxSurgeCap;

    /**
     * Checks if a ride’s parameters comply with local regulations.
     */
    public ComplianceResponse checkCompliance(ComplianceRequest request) {
        ComplianceResponse response = new ComplianceResponse();
        try {
            if (request.getSurgeMultiplier() > maxSurgeCap) {
                response.setCompliant(false);
                response.setMessage("Surge multiplier exceeds maximum allowed cap of " + maxSurgeCap);
            } else {
                response.setCompliant(true);
                response.setMessage("Ride is compliant with local surge regulations.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setCompliant(false);
            response.setMessage("Error checking compliance: " + e.getMessage());
        }
        return response;
    }
}
