package airtrafficcontrol.app.appforconsole.utils.hangar;

import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.FlightPlan;


/**
 * This class identifies a civil airplane
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public class CivilAirPlane extends AirPlane implements ICivil
{
	private int passengersNum;	// This variable is private because is only verified in this class
	
	
	/**
	 * This constructor identifies any civil airplane, and throws an IO exception if 
	 * this aircraft isn't properly defined
	 */
	public CivilAirPlane( 	String flightID,
							GeographicalPosition statingPosition,
							FlightPlan flightPlan,
							int passengers
						) throws InvalidArgumentException
	{
		super(flightID, statingPosition, flightPlan );
		this.passengersNum = passengers;
	}
	
	/**
	 * @return the number of passengers in the airplane
	 */
	public int getPassengersNumber()
	{
		return passengersNum;
	}
	
	/**
	 * sets the number of passengers to 0
	 */
	protected void removePassengers()
	{
		passengersNum = 0;
	}
	
		
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
	
	/**
	 * The number of minutes the airship has to land so that when he abandons
	 * the current established air corridor, this occurrence will not be reported
	 * as an error.
	 */
	public int getTimeToLand()
	{		
		return super.getPlan().getNumberOfMinutesToLand();
	}
	
	
	/**
	 * The number of minutes the airship has to take-off and reach the first air
	 * corridor.
	 */
	public int getTimeToTakeOff()
	{		
		return super.getPlan().getNumberOfMinutesToTakeOff();
	}
	
	
	/**
	 * The number of minutes the airship has to switch from an established
	 * altitude corridor to next one established.
	 */
	public int getTimeToSwitchCorridor()
	{		
		return super.getPlan().getNumberOfMinutesToSwitchCorridor();
	}
	
	
	
	/**
	 * This method defines the time (in minutes) for the civil airplane to land,
	 * and throws IO exception if this interlude is not respected
	 */
	public void setTimeToLand(int newTime) throws InvalidArgumentException
	
	{		
		
		super.getPlan().setNumberOfMinutesToLand(newTime);
	}
	
	
	/**
	 * The number of minutes the airship has to take-off and reach the first air
	 * corridor.
	 */
	public void setTimeToTakeOff(int newTime) throws InvalidArgumentException
	{		
		super.getPlan().setNumberOfMinutesToTakeOff(newTime);
	}
	
	
	/**
	 * The number of minutes the airship has to switch from an established
	 * altitude corridor to next one established.
	 */
	public void setTimeToSwitchCorridor(int newTime) throws InvalidArgumentException
	{		
		super.getPlan().setNumberOfMinutesToSwitchCorridor(newTime);
	}

}
