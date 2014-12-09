package airtrafficcontrol.app.appforconsole.utils.hangar;

import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.CivilAirplane;
import airtrafficcontrol.app.appforconsole.utils.FlightPlan;
import airtrafficcontrol.app.appforconsole.utils.GeographicalPosition;

/**
 * Creates an airliner
 * 
 *
 *@author Eva Gomes
 *@author Hugo Leal
 *@author Lucas Andrade
 */
public class Airliner extends CivilAirplane{
	
	private static int numberOfMinutesToTakeOff = 8;
	private static int numberOfMinutesToLand = 10;
	private static int numberOfMinutesToSwitchCorridor = 4;
	private static int newnumberOfMinutesToTakeOff;
	private static int newnumberOfMinutesToLand;
	private static int newnumberOfMinutesToSwitchCorridor;
	private static boolean newTakeOff = false;
	private static boolean newLand = false;
	private static boolean newSwitch = false;
	private int passengersNum;
	
	public Airliner(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan, int passengers) throws InvalidArgumentException {
		super(flightID, statingPosition, flightPlan);
		
		passengersNum = passengers;
		
		if (newTakeOff)
			numberOfMinutesToTakeOff = newnumberOfMinutesToTakeOff;
		if (newLand)
			numberOfMinutesToLand = newnumberOfMinutesToLand;
		if (newSwitch)
			numberOfMinutesToSwitchCorridor = newnumberOfMinutesToSwitchCorridor;
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
	 * sets a new number of minutes for the take off of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to take off
	 */
	public void setNumberOfMinutesToTakeOff(int newTime) throws InvalidArgumentException
	{			
		if (newTime == 0)
			throw new InvalidArgumentException();
		
		numberOfMinutesToTakeOff = newTime;
		newnumberOfMinutesToTakeOff = newTime;
		newTakeOff = true;
	}
	
	/**
	 * sets a new number of minutes for the land of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to land
	 */
	public void setNumberOfMinutesToLand(int newTime) throws InvalidArgumentException
	{
		if (newTime == 0)
			throw new InvalidArgumentException();
		
		numberOfMinutesToLand = newTime;
		newnumberOfMinutesToLand = newTime;
		newLand = true;
	}
	
	/**
	 * sets a new number of minutes for switching lanes of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to switch lanes
	 */
	public void setNumberOfMinutesToSwitchCorridor(int newTime) throws InvalidArgumentException
	{
		if (newTime == 0)
			throw new InvalidArgumentException();
		
		numberOfMinutesToSwitchCorridor = newTime;
		newnumberOfMinutesToSwitchCorridor = newTime;
		newSwitch = true;
	}
	
	/**
	 * @return the number of minutes the airplanes of this class need to take off
	 */
	public int getNumberOfMinutesToTakeOff()
	{
		return numberOfMinutesToTakeOff;
	}
	
	/**
	 * @return - the number of minutes the airplanes of this class need to land
	 */
	public int getNumberOfMinutesToLand()
	{
		return numberOfMinutesToLand;
	}
	
	/**
	 * @return - the number of minutes the airplanes of this class need to switch lanes
	 */
	public int getNumberOfMinutesToSwitchCorridor()
	{
		return numberOfMinutesToSwitchCorridor;
	}
	
	/**
	 * Verifies if the airliner is with 0 passengers
	 * @param airliner
	 * @return true if the airliner has 0 passengers else returns false
	 */
	public boolean isEmpty(Airliner airliner)
	{
		if(airliner.getPassengersNumber() == 0)
			return true;
		else 
			return false;
	}
}
