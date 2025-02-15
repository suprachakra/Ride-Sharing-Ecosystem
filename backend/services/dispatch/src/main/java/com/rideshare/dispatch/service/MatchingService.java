package com.rideshare.dispatch.service;

import com.rideshare.dispatch.dto.DispatchRequest;
import com.rideshare.dispatch.dto.DispatchResponse;
import org.springframework.stereotype.Service;

@Service
public class MatchingService {

    /**
     * Matches a ride request with a suitable driver.
     * In a production system, this would use complex geospatial matching,
     * real-time traffic data (e.g., via Google Maps API), and driver availability.
     * Here, we simulate matching with placeholder logic.
     */
    public DispatchResponse matchRide(DispatchRequest request) {
        DispatchResponse response = new DispatchResponse();
        // Simulated matching logic:
        response.setDriverId("DRIVER123");
        response.setVehicleId("VEHICLE123");
        double estimatedTime = calculateEstimatedArrivalTime(
            request.getPickupLatitude(), request.getPickupLongitude(),
            request.getDropoffLatitude(), request.getDropoffLongitude());
        response.setEstimatedArrivalTime(estimatedTime);
        response.setMessage("Driver matched successfully.");
        return response;
    }

    /**
     * A dummy method to calculate estimated arrival time.
     * Replace with calls to a mapping API (e.g., Google Maps) in production.
     */
    private double calculateEstimatedArrivalTime(double pickupLat, double pickupLon, 
                                                 double dropoffLat, double dropoffLon) {
        double distance = Math.sqrt(Math.pow(pickupLat - dropoffLat, 2) + Math.pow(pickupLon - dropoffLon, 2));
        return (distance < 0.05) ? 5.0 : 10.0;
    }
}
