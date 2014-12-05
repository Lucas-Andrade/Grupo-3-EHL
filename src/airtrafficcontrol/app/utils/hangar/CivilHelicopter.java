package airtrafficcontrol.app.utils.hangar;

import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.utils.airshipplan.FlightPlan;


/**
 * This class identifies the particular type of helicopter, the civil
 * helicopter
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public class CivilHelicopter extends Helicopter implements ICivil
{
	private int passengers;	//This variable is private, because only refers to this class and derivates
	
	
	/**
	 * This constructor identifies the civil helicopter, throwing an IOException if the aircraft 
	 * isn't properly defined
	 */
	public CivilHelicopter(String flightID,
			GeographicalPosition statingPosition, FlightPlan flightPlan, int passengers )
			throws InvalidArgumentException
	{
		super(flightID, statingPosition, flightPlan);
		this.passengers = passengers;
	}

	
	/**
	 * This method is inherited from super class, in order to get the passengers
	 * number
	 */
	@Override
	public int getPassengersNumber()
	{
		return passengers;
	}

	@Override
	/**
	 * @param airliner
	 * @return true if the CivilAirPlane has 0 passengers, false otherwise
	 */
	public boolean verifyEmptyPlaces()
	{
		if( getPassengersNumber() == 0 )
			return true;
		else 
			return false;
	}
}
