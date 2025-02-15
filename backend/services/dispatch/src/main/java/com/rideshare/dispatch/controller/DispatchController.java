package com.rideshare.dispatch.controller;

import com.rideshare.dispatch.dto.DispatchRequest;
import com.rideshare.dispatch.dto.DispatchResponse;
import com.rideshare.dispatch.service.MatchingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dispatch")
public class DispatchController {

    @Autowired
    private MatchingService matchingService;

    @PostMapping("/match")
    public ResponseEntity<?> matchRide(@RequestBody DispatchRequest request) {
        try {
            DispatchResponse response = matchingService.matchRide(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error matching ride: " + e.getMessage());
        }
    }
}
