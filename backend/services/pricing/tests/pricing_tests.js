/**
 * pricing_tests.js
 * Unit tests for the pricing service modules.
 * Run these tests using a framework like Mocha.
 */

const assert = require('assert');
const surge = require('../src/surge');
const carpoolPricing = require('../src/carpool_pricing');
const fallback = require('../src/fallback');
const compliance = require('../src/compliance');
const riskTriggers = require('../src/risk_triggers');

describe('Pricing Service Tests', function() {
  describe('Surge Calculation', function() {
    it('should return a surge multiplier of 1.0 when demand equals supply', async function() {
      const multiplier = await surge.computeSurge(50, 50);
      assert.strictEqual(multiplier, 1.0);
    });

    it('should return a surge multiplier greater than 1.0 when demand exceeds supply', async function() {
      const multiplier = await surge.computeSurge(60, 50);
      assert(multiplier > 1.0);
    });
  });

  describe('Carpool Pricing', function() {
    it('should calculate fare per rider correctly', async function() {
      // Sample trip data for a carpool ride.
      const tripData = {
        baseFare: 2.00,
        distance: 10,           // kilometers
        waitingTime: 5,         // minutes
        surgeMultiplier: 1.2,
        numRiders: 2,
        config: {
          perKmRate: 0.60,
          waitingCharge: 0.15,
          carpoolDiscount: 0.25,
          maxFarePerRider: 20.00
        }
      };
      const result = await carpoolPricing.computeCarpoolFare(tripData);
      assert(result.farePerRider > 0, 'Fare per rider should be positive');
    });
  });

  describe('Fallback Logic', function() {
    it('should trigger fallback when risk metrics indicate high margin dip', function() {
      const tripData = {}; // Trip data is not used in our simple check.
      const riskMetrics = { marginDip: 0.06, occupancyRate: 1.2 };
      const result = fallback.shouldFallback(tripData, riskMetrics);
      assert.strictEqual(result, true);
    });

    it('should not trigger fallback when risk metrics are within acceptable thresholds', function() {
      const tripData = {};
      const riskMetrics = { marginDip: 0.03, occupancyRate: 1.2 };
      const result = fallback.shouldFallback(tripData, riskMetrics);
      assert.strictEqual(result, false);
    });
  });

  describe('Compliance Checks', function() {
    it('should cap the surge multiplier in fare details if it exceeds the max surge cap', function() {
      const fareDetails = {
        fare: 50,
        breakdown: {
          baseFare: 2.00,
          distanceCharge: 6.00,
          waitingCharge: 0.75,
          surgeMultiplier: 2.0 // Exceeds the cap of 1.5
        }
      };
      const adjustedFareDetails = compliance.applyComplianceChecks(fareDetails);
      assert(adjustedFareDetails.breakdown.surgeMultiplier <= 1.5);
    });
  });

  describe('Risk Triggers', function() {
    it('should return valid default metrics without error', async function() {
      const metrics = await riskTriggers.getCurrentMetrics();
      assert(metrics.occupancyRate, 'Occupancy rate should be defined');
      assert(metrics.marginDip !== undefined, 'Margin dip should be defined');
    });
  });
});
