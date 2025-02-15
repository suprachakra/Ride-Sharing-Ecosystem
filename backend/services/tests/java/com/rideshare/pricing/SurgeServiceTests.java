package com.rideshare.pricing.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SurgeServiceTests {

    private final SurgeService surgeService = new SurgeService();

    @Tes
    public void testComputeSurgeWhenDemandEqualsSupply() {
        double multiplier = surgeService.computeSurge(50, 50);
        assertEquals(1.0, multiplier, "Surge multiplier should be 1.0 when demand equals supply");
    }

    @Test
    public void testComputeSurgeWhenDemandExceedsSupply() {
        double multiplier = surgeService.computeSurge(60, 50);
        assertTrue(multiplier > 1.0, "Surge multiplier should be greater than 1.0 when demand exceeds supply");
    }
}
