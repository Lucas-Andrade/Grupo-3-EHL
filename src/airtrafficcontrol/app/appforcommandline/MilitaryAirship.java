package airtrafficcontrol.app.appforcommandline;

public class MilitaryAirship extends Airship {

	// Instance Fields

	private final boolean hasWeapons;

	// Constructor

	public MilitaryAirship(double latitude, double longitude, double altitude, double maxAltitude,
			double minAltitude, boolean hasWeapons) {

		super(latitude, longitude, altitude, maxAltitude, minAltitude);

		this.hasWeapons = hasWeapons;
	}

	// Get Methods

	public boolean hasWeapons() {

		return hasWeapons;
	}
}
