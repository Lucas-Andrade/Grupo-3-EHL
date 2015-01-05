package main.java.cli.model.airships;

import main.java.cli.exceptions.InvalidArgumentException;

/**
 * Class whose instances will represent the correct flight path an {@code Airship} is allowed to
 * use.
 */
public class AirCorridor {

	// Instance Fields

	/**
	 * {@code maxAltitude} - the maximum altitude an {@code Airship} is allowed to fly.
	 */
	private final double maxAltitude;

	/**
	 * {@code minAltitude} - the minimum altitude an {@code Airship} is allowed to fly.
	 */
	private final double minAltitude;

	// Constructor

	/**
	 * AirCorridor constructor that will receive as parameters altitude limits an {@code Airship}
	 * can fly.
	 * 
	 * If the minimum altitude is less than 0 or the maximum altitue is lower than the minimum
	 * altitude it will throw an {@link IllegalArgumentException}.
	 * 
	 * @param maxAltitude
	 *            The air corridor's maximum altitude.
	 * @param minAltitude
	 *            The air corridor's minimum altitude.
	 * @throws InvalidArgumentException
	 *             If {@code minAltitude<0} or {@code maxAltitude<minAltitude}.
	 */
	public AirCorridor(double maxAltitude, double minAltitude) throws InvalidArgumentException {

		if (minAltitude < 0)
			throw new InvalidArgumentException(
					"Minimum altitude of an air corridor must be greater than 0.");
		if (maxAltitude < minAltitude)
			throw new InvalidArgumentException(
					"Maximum altitude of an air corridor must be greater than the minimum altitude.");

		this.maxAltitude = maxAltitude;
		this.minAltitude = minAltitude;
	}

	// Overrides

	/**
	 * Override of the {@code toString()} method from {@code Object}.
	 */
	@Override
	public String toString() {

		return new StringBuilder("\nMaximum Altitude Permited: ").append(maxAltitude)
				.append(" Minimum Altitude Permited: ").append(minAltitude).toString();
	}

	// Get Methods

	/**
	 * @return returns the {@code maxAltitude}.
	 */
	public double getMaxAltitude() {

		return maxAltitude;
	}

	/**
	 * @return returns the {@code minAltitude}.
	 */
	public double getMinAltitude() {

		return minAltitude;
	}
}
