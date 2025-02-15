package com.rideshare.payment.service;

import org.springframework.stereotype.Service;

@Service
public class CashHandlingService {

    /**
     * Processes a cash payment.
     * For cash payments, confirmation may be handled on the driver’s side.
     */
    public boolean processCashPayment(double amount) {
        try {
            // Simulate cash payment processing.
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
