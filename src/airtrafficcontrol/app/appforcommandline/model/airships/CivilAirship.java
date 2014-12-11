package airtrafficcontrol.app.appforcommandline.model.airships;

/**
 * Class whose instances will represent a civil airship. This type of airships is distinguished from
 * the other because they have an extra field that represents the number of passengers the airship
 * has.
 * 
 * Extends {@link Airship}.
 */
public class CivilAirship extends Airship {

	// Instance Fields

	/**
	 * {@code passengers} - int variable that represents the number of passengers the airship has.
	 */
	private final int passengers;

	// Constructor

	public CivilAirship(double latitude, double longitude, double altitude, double maxAltitude,
			double minAltitude, int passengers) {

		super(latitude, longitude, altitude, maxAltitude, minAltitude);

		if (passengers < 0)
			throw new IllegalArgumentException("The number of passengers cannot be less than 0");

		this.passengers = passengers;
	}

	// Get Methods

	public int getPassengers() {

		return passengers;
	}
}
