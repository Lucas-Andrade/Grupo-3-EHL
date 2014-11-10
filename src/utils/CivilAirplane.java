package utils;

import app.InvalidArgumentException;

public abstract class CivilAirplane extends Airship{
	

	public CivilAirplane(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan) throws InvalidArgumentException {
		super(flightID, statingPosition, flightPlan);
	}

	
}
