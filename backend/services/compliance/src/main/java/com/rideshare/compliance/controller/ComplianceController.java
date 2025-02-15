package com.rideshare.compliance.controller;

import com.rideshare.compliance.dto.ComplianceRequest;
import com.rideshare.compliance.dto.ComplianceResponse;
import com.rideshare.compliance.service.ComplianceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compliance")
public class ComplianceController {

    @Autowired
    private ComplianceService complianceService;

    @PostMapping("/check")
    public ResponseEntity<?> checkCompliance(@RequestBody ComplianceRequest request) {
        try {
            ComplianceResponse response = complianceService.checkCompliance(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error in compliance check: " + e.getMessage());
        }
    }
}
