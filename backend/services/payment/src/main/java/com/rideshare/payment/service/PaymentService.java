package com.rideshare.payment.service;

import com.rideshare.payment.dto.PaymentRequest;
import com.rideshare.payment.dto.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentService {

    @Autowired
    private WalletService walletService;

    @Autowired
    private CardProcessingService cardProcessingService;

    @Autowired
    private CashHandlingService cashHandlingService;

    /**
     * Processes payment based on the specified payment method.
     */
    public PaymentResponse processPayment(PaymentRequest request) {
        PaymentResponse response = new PaymentResponse();
        try {
            boolean result;
            switch (request.getPaymentMethod().toUpperCase()) {
                case "WALLET":
                    result = walletService.processWalletPayment(request.getAmount(), request.getRiderId());
                    break;
                case "CARD":
                    result = cardProcessingService.tokenizeAndProcessCard(request.getCardInfo(), request.getAmount());
                    break;
                case "CASH":
                    result = cashHandlingService.processCashPayment(request.getAmount());
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported payment method");
            }
            response.setSuccess(result);
            response.setTransactionId(UUID.randomUUID().toString());
            response.setMessage(result ? "Payment processed successfully" : "Payment failed");
        } catch (Exception e) {
            e.printStackTrace();
            response.setSuccess(false);
            response.setMessage("Error processing payment: " + e.getMessage());
        }
        return response;
    }
}
