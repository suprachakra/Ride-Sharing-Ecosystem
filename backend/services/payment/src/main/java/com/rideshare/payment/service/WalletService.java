package com.rideshare.payment.service;

import org.springframework.stereotype.Service;

@Service
public class WalletService {

    /**
     * Processes payment using the rider’s wallet.
     * In production, integrate with an external wallet API.
     */
    public boolean processWalletPayment(double amount, String riderId) {
        try {
            // Simulate successful wallet payment.
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
