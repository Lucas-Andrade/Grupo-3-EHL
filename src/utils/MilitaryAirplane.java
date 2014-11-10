package utils;

import app.InvalidArgumentException;

/**
 * Abstract class of all the military airplanes
 * 
 *
 *@author Eva Gomes
 *@author Hugo Leal
 *@author Lucas Andrade
 */
public abstract class MilitaryAirplane extends Airship{
	
	private boolean carriesArmament;
	
	public MilitaryAirplane(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan, boolean armament) throws InvalidArgumentException {
		super(flightID, statingPosition, flightPlan);
		carriesArmament = armament;
		
		if(flightID==null || statingPosition == null || flightPlan == null)
			throw new InvalidArgumentException();
	}
	
	public boolean hasArmament()
	{
		return carriesArmament;
	}

}
