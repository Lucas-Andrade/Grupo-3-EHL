package airtrafficcontrol.app.appforcommandline;


public class MilitaryAirship extends Airship {

	private final boolean hasWeapons;
	
	public MilitaryAirship(double latitude, double longitude, double altitude, double maxAltitude,
			double minAltitude, boolean hasWeapons) {

		super(latitude, longitude, altitude, maxAltitude, minAltitude);
		
		this.hasWeapons = hasWeapons;
	}
	
	public boolean hasWeapons() {
	
		return hasWeapons;
	}
}
