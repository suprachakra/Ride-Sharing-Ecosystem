package com.rideshare.payment.controller;

import com.rideshare.payment.dto.PaymentRequest;
import com.rideshare.payment.dto.PaymentResponse;
import com.rideshare.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<?> processPayment(@RequestBody PaymentRequest request) {
        try {
            PaymentResponse response = paymentService.processPayment(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error processing payment: " + e.getMessage());
        }
    }
}
