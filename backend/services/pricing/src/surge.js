/**
 * surge.js
 * Implements surge multiplier calculation based on demand and supply.
 *
 * Basic Algorithm:
 *   - If (demand > supply), then surge = 1 + (demand - supply) * factor.
 *   - Otherwise, surge = 1.0.
 * The factor can be tuned via configuration.
 */
/*
async function computeSurge(demand, supply) {
  try {
    if (typeof demand !== 'number' || typeof supply !== 'number') {
      throw new Error('Invalid demand or supply value');
    }
    // Example surge factor (could be moved to config if desired)
    const factor = 0.1;
    let surgeMultiplier = 1.0;
    if (demand > supply) {
      const extra = demand - supply;
      surgeMultiplier += extra * factor;
    }
    // Return the multiplier rounded to two decimal places.
    return parseFloat(surgeMultiplier.toFixed(2));
  } catch (error) {
    console.error('Error in computeSurge:', error);
    // Fallback to a default multiplier.
    return 1.0;
  }
}

module.exports = { computeSurge };
*/
