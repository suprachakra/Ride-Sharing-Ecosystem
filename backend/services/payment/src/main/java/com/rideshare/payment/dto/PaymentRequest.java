package com.rideshare.payment.dto;

public class PaymentRequest {
    private String riderId;
    private double amount;
    private String paymentMethod; // "WALLET", "CARD", or "CASH"
    private String cardInfo;      // For card payments

    // Getters and setters
    public String getRiderId() {
        return riderId;
    }
    public void setRiderId(String riderId) {
        this.riderId = riderId;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getCardInfo() {
        return cardInfo;
    }
    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }
}
