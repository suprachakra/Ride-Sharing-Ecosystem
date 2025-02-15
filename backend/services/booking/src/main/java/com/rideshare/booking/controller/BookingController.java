package com.rideshare.booking.controller;

import com.rideshare.booking.dto.BookingRequest;
import com.rideshare.booking.dto.BookingResponse;
import com.rideshare.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest request) {
        try {
            BookingResponse response = bookingService.createBooking(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error creating booking: " + e.getMessage());
        }
    }
}
