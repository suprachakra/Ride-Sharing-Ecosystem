package com.rideshare.pricing.controller;

import com.rideshare.pricing.dto.FareRequest;
import com.rideshare.pricing.model.FareDetails;
import com.rideshare.pricing.model.RiskMetrics;
import com.rideshare.pricing.service.CarpoolPricingService;
import com.rideshare.pricing.service.ComplianceService;
import com.rideshare.pricing.service.FallbackService;
import com.rideshare.pricing.service.RiskTriggerService;
import com.rideshare.pricing.service.SurgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PricingController {

    @Autowired
    private SurgeService surgeService;

    @Autowired
    private CarpoolPricingService carpoolPricingService;

    @Autowired
    private ComplianceService complianceService;

    @Autowired
    private FallbackService fallbackService;

    @Autowired
    private RiskTriggerService riskTriggerService;

    /**
     * Endpoint to calculate fare details.
     * Expects a JSON body representing a FareRequest.
     */
    @PostMapping("/calculateFare")
    public ResponseEntity<?> calculateFare(@RequestBody FareRequest request) {
        try {
            // Validate required fields (basic validation)
            if (request.getBaseFare() == null || request.getDistance() == null ||
                request.getWaitingTime() == null || request.getDemand() == null ||
                request.getSupply() == null) {
                return ResponseEntity.badRequest().body("Invalid trip data provided.");
            }

            // Step 1: Compute surge multiplier.
            double surgeMultiplier = surgeService.computeSurge(request.getDemand(), request.getSupply());

            // Step 2: Calculate fare details based on trip type.
            FareDetails fareDetails;
            if ("CARPOOL".equalsIgnoreCase(request.getTripType())) {
                fareDetails = carpoolPricingService.computeCarpoolFare(request, surgeMultiplier);
            } else {
                // For PRIVATE ride, use carpool service’s basic calculation (or a separate private method if needed)
                fareDetails = carpoolPricingService.computePrivateFare(request, surgeMultiplier);
            }

            // Step 3: Apply compliance checks.
            fareDetails = complianceService.applyComplianceChecks(fareDetails);

            // Step 4: Check risk triggers and decide fallback.
            RiskMetrics metrics = riskTriggerService.getCurrentMetrics();
            boolean fallbackTriggered = fallbackService.shouldFallback(request, metrics);
            fareDetails.setFallbackTriggered(fallbackTriggered);

            return ResponseEntity.ok(fareDetails);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error calculating fare: " + e.getMessage());
        }
    }
}
