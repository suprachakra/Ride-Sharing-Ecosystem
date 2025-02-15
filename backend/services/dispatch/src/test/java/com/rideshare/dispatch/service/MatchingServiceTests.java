package com.rideshare.dispatch.service;

import com.rideshare.dispatch.dto.DispatchRequest;
import com.rideshare.dispatch.dto.DispatchResponse;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatchingServiceTests {

    private final MatchingService matchingService = new MatchingService();

    @Test
    public void testMatchRide() {
        DispatchRequest request = new DispatchRequest();
        request.setPickupLatitude(40.7128);
        request.setPickupLongitude(-74.0060);
        request.setDropoffLatitude(40.7138);
        request.setDropoffLongitude(-74.0050);

        DispatchResponse response = matchingService.matchRide(request);
        assertNotNull(response);
        assertEquals("DRIVER123", response.getDriverId());
        assertNotNull(response.getVehicleId());
        assertTrue(response.getEstimatedArrivalTime() >= 5);
        assertEquals("Driver matched successfully.", response.getMessage());
    }
}
