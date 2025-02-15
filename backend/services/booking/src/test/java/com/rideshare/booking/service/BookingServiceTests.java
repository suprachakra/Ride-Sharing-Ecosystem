package com.rideshare.booking.service;

import com.rideshare.booking.dto.BookingRequest;
import com.rideshare.booking.dto.BookingResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookingServiceTests {

    private final BookingService bookingService = new BookingService();

    @Test
    public void testCreateBooking() {
        BookingRequest request = new BookingRequest();
        request.setRiderId("RIDER123");
        request.setTripType("PRIVATE");
        request.setPickupLatitude(40.7128);
        request.setPickupLongitude(-74.0060);
        request.setDropoffLatitude(40.730610);
        request.setDropoffLongitude(-73.935242);

        BookingResponse response = bookingService.createBooking(request);
        assertNotNull(response.getBookingId());
        assertEquals("CREATED", response.getStatus());
        assertTrue(response.getMessage().contains("RIDER123"));
    }
}
