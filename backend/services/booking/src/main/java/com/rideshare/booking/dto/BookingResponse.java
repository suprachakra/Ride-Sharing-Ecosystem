package com.rideshare.booking.dto;

public class BookingResponse {
    private String bookingId;
    private String status;  // e.g., "CREATED", "CONFIRMED", "CANCELLED"
    private String message;

    // Getters and setters
    public String getBookingId() {
        return bookingId;
    }
    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
