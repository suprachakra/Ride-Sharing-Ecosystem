/**
 * main.js
 * Entry point for the Dynamic Pricing & Carpool Logic Service.
 * Exposes REST endpoints to calculate fares for both private and carpool rides.
 */
/*
const express = require('express');
const bodyParser = require('body-parser');
const surge = require('./surge');
const carpoolPricing = require('./carpool_pricing');
const fallback = require('./fallback');
const compliance = require('./compliance');
const riskTriggers = require('./risk_triggers');
const config = require('../config/pricing_config.json');

const app = express();
app.use(bodyParser.json());

// Endpoint: Calculate fare based on trip details.
app.post('/calculateFare', async (req, res) => {
  try {
    // Expected JSON input structure:
    // {
    //   "tripType": "PRIVATE" or "CARPOOL",
    //   "baseFare": Number,
    //   "distance": Number,         // in kilometers
    //   "waitingTime": Number,      // in minutes
    //   "demand": Number,           // current demand metric
    //   "supply": Number,           // current supply metric
    //   "numRiders": Number         // for carpool rides
    // }
    const tripData = req.body;

    // Validate required fields
    if (
      typeof tripData.baseFare !== 'number' ||
      typeof tripData.distance !== 'number' ||
      typeof tripData.waitingTime !== 'number' ||
      typeof tripData.demand !== 'number' ||
      typeof tripData.supply !== 'number'
    ) {
      return res.status(400).json({ success: false, message: 'Invalid trip data provided.' });
    }

    // Step 1: Compute surge multiplier based on demand/supply.
    let surgeMultiplier = await surge.computeSurge(tripData.demand, tripData.supply);

    let fareDetails;
    // Step 2: Calculate fare using carpool logic if applicable.
    if (tripData.tripType === 'CARPOOL') {
      fareDetails = await carpoolPricing.computeCarpoolFare({
        baseFare: tripData.baseFare,
        distance: tripData.distance,
        waitingTime: tripData.waitingTime,
        surgeMultiplier: surgeMultiplier,
        numRiders: tripData.numRiders,
        config: config
      });
    } else {
      // For a private ride: fare = (baseFare + distanceCharge + waitingCharge) * surgeMultiplier
      const perKmRate = config.perKmRate;
      const waitingCharge = config.waitingCharge;
      const distanceCharge = tripData.distance * perKmRate;
      const waitingChargeTotal = tripData.waitingTime * waitingCharge;
      let fare = (tripData.baseFare + distanceCharge + waitingChargeTotal) * surgeMultiplier;
      fareDetails = {
        fare: parseFloat(fare.toFixed(2)),
        breakdown: {
          baseFare: tripData.baseFare,
          distanceCharge: parseFloat(distanceCharge.toFixed(2)),
          waitingCharge: parseFloat(waitingChargeTotal.toFixed(2)),
          surgeMultiplier: surgeMultiplier
        }
      };
    }

    // Step 3: Apply compliance adjustments (e.g., enforce maximum surge limits).
    fareDetails = compliance.applyComplianceChecks(fareDetails);

    // Step 4: Retrieve current risk metrics and check for fallback conditions.
    const riskMetrics = await riskTriggers.getCurrentMetrics();
    if (fallback.shouldFallback(tripData, riskMetrics)) {
      fareDetails.fallbackTriggered = true;
      // Optionally, here you could adjust fareDetails to reflect legacy pricing.
    } else {
      fareDetails.fallbackTriggered = false;
    }

    // Respond with the computed fare details.
    res.status(200).json({ success: true, data: fareDetails });
  } catch (error) {
    console.error('Error calculating fare:', error);
    res.status(500).json({
      success: false,
      message: 'Error calculating fare',
      error: error.message
    });
  }
});
*/
const PORT = process.env.PORT || 3001;
app.listen(PORT, () => {
  console.log(`Pricing service is running on port ${PORT}`);
});
