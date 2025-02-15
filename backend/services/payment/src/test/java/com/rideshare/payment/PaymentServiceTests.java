package com.rideshare.payment;

import com.rideshare.payment.dto.PaymentRequest;
import com.rideshare.payment.dto.PaymentResponse;
import com.rideshare.payment.service.PaymentService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTests {

    private final PaymentService paymentService = new PaymentService();

    @Test
    public void testProcessWalletPayment() {
        PaymentRequest request = new PaymentRequest();
        request.setRiderId("RIDER123");
        request.setAmount(50.0);
        request.setPaymentMethod("WALLET");

        PaymentResponse response = paymentService.processPayment(request);
        assertTrue(response.isSuccess());
        assertNotNull(response.getTransactionId());
    }

    @Test
    public void testProcessCardPayment() {
        PaymentRequest request = new PaymentRequest();
        request.setRiderId("RIDER123");
        request.setAmount(50.0);
        request.setPaymentMethod("CARD");
        request.setCardInfo("sample-card-info");

        PaymentResponse response = paymentService.processPayment(request);
        assertTrue(response.isSuccess());
        assertNotNull(response.getTransactionId());
    }
}
