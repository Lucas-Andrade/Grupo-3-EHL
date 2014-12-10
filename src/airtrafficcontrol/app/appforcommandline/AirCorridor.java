package airtrafficcontrol.app.appforcommandline;
/**
 * Class whose instances will represent the correct flight path an {@code Airship} is allowed to
 * use.
 */
public class AirCorridor {

	/**
	 * {@code maxAltitude} - the maximum altitude an {@code Airship} is allowed to fly.
	 */
	private final double maxAltitude;

	/**
	 * {@code minAltitude} - the minimum altitude an {@code Airship} is allowed to fly.
	 */
	private final double minAltitude;

	/**
	 * AirCorridor constructor that will receive as parameters altitude limits an {@code Airship}
	 * can fly.
	 * 
	 * If the minimum altitude is less than 0 or the maximum altitue is lower than the minimum
	 * altitude it will throw an {@link IllegalArgumentException}.
	 * 
	 * @param maxAltitude
	 * @param minAltitude
	 */
	public AirCorridor(double maxAltitude, double minAltitude) {

		if (minAltitude < 0 || maxAltitude < minAltitude)
			throw new IllegalArgumentException();

		this.maxAltitude = maxAltitude;
		this.minAltitude = minAltitude;
	}

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

	/**
	 * Override of the {@code toString()} method from {@code Object}.
	 */
	@Override
	public String toString() {

		return new StringBuilder("\nMaximum Altitude Permited: ").append(maxAltitude)
				.append("Minimum Altitude Permited: ").append(maxAltitude).toString();
	}
}
