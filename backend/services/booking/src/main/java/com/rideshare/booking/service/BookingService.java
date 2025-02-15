package com.rideshare.booking.service;

import com.rideshare.booking.dto.BookingRequest;
import com.rideshare.booking.dto.BookingResponse;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BookingService {

    /**
     * Creates a booking.
     * In a production environment, this would interact with persistent storage
     * and manage trip state transitions.
     */
    public BookingResponse createBooking(BookingRequest request) {
        if (request.getRiderId() == null || request.getRiderId().isEmpty()) {
            throw new IllegalArgumentException("Rider ID is required");
        }
        BookingResponse response = new BookingResponse();
        response.setBookingId(UUID.randomUUID().toString());
        response.setStatus("CREATED");
        response.setMessage("Booking created successfully for rider: " + request.getRiderId());
        return response;
    }
}
