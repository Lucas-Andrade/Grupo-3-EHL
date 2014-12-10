package airtrafficcontrol.app.appforcommandline;
/**
 * Class whose instances will represent a civil airship. This type of airships is distinguished from
 * the other because they have an extra field that represents the number of passengers the airship
 * has.
 * 
 * Extends {@link Airship}.
 */
public class CivilAirship extends Airship {

	/**
	 * {@code passengers} - int variable that represents the number of passengers the airship has.
	 */
	private final int passengers;

	public CivilAirship(double latitude, double longitude, double altitude, double maxAltitude,
			double minAltitude, int passengers) {

		super(latitude, longitude, altitude, maxAltitude, minAltitude);

		this.passengers = passengers;
	}

	public int getPassengers() {

		return passengers;
	}
}
