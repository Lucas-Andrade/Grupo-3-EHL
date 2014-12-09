package airtrafficcontrol.app.appforconsole.utils;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
/**
 * 
 * super class of all the civilian airplanes
 *
 *@author Eva Gomes
 *@author Hugo Leal
 *@author Lucas Andrade
 */
public abstract class CivilAirplane extends Airship{
	
/**
 * Constructs a new civilian airplane, with all its properties already defined
 * 
 * @param flightID - the ID of the flight
 * @param statingPosition - the coordinates of where the airplane will take off
 * @param flightPlan - the plan of the flight
 */
	public CivilAirplane(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan)throws InvalidArgumentException {
		super(flightID, statingPosition, flightPlan);

	}
	
}
