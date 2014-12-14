package appForConsole.model.airships;

/**
 * Class whose instances will represent the correct flight path an {@link Airship} is allowed to be.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
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
	 * AirCorridor constructor that will receive as parameters the altitude limits between which an
	 * {@code Airship} can fly.
	 * 
	 * @param maxAltitude
	 *            - the maximum altitude an {@code Airship} is allowed to fly.
	 * @param minAltitude
	 *            - the minimum altitude an {@code Airship} is allowed to fly.
	 * 
	 * @throws IllegalArgumentException
	 *             If the minimum altitude is less than 0 or the maximum altitue is lower than the
	 *             minimum altitude
	 */
	public AirCorridor(double maxAltitude, double minAltitude) {

		if (minAltitude < 0 || maxAltitude < minAltitude)
			throw new IllegalArgumentException();

		this.maxAltitude = maxAltitude;
		this.minAltitude = minAltitude;
	}

	// Overrides

	/**
	 * Override of the {@link Object#toString() toString()} method from {@link Object}.
	 */
	@Override
	public String toString() {

		return new StringBuilder("\nMaximum Altitude Permited: ").append(maxAltitude)
				.append(" Minimum Altitude Permited: ").append(minAltitude).toString();
	}

	// Get Methods

	/**
	 * @return the {@code maxAltitude}.
	 */
	public double getMaxAltitude() {

		return maxAltitude;
	}

	/**
	 * @return the {@code minAltitude}.
	 */
	public double getMinAltitude() {

		return minAltitude;
	}
}