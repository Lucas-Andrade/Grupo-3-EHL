package utils;

public abstract class MilitaryAirplane extends Airship{
	
	private boolean carriesArmament;
	
	public MilitaryAirplane(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan, boolean armament) {
		super(flightID, statingPosition, flightPlan);
		carriesArmament = armament;
	}
	
	public boolean hasArmament()
	{
		return carriesArmament;
	}
}
