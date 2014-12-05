package airtrafficcontrol.app.utils.hangar;

import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.utils.airshipplan.FlightPlan;




/**
 * 
 * class of civil aircraft used to transport cargo
 *
 * @author Eva Gomes, Hugo Leal, Lucas Andrade
 *  
 * @author (revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */


public class MilitaryAirPlane extends AirPlane implements IMilitary{

	private boolean hasArmament;
	
	/**
	 * creates a new military airplane with all its properties
	 * 
	 * @param flightID - the ID of the flight
	 * @param statingPosition - the coordinates of where the airplane will take off
	 * @param flightPlan - the plan of the flight
	 */
	public MilitaryAirPlane(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan, boolean hasArmament) 
			throws InvalidArgumentException {
		super(flightID, statingPosition, flightPlan);
		this.hasArmament = hasArmament;		
	}

	
	/**
	 * This method differentiates military from civil aircrafts
	 */
	@Override
	public boolean hasArmament() {
		return this.hasArmament;
	}
	
}
