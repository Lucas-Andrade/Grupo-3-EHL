package airtrafficcontrol.app.utils;

import app.InvalidArgumentException;

/**
 * Creates a military transport airplane. 
 * 
 *
 *@author Eva Gomes
 *@author Hugo Leal
 *@author Lucas Andrade
 */
public class Transport extends MilitaryAirplane{
	
	private static int numberOfMinutesToTakeOff = 10;
	private static int numberOfMinutesToLand = 12;
	private static int numberOfMinutesToSwitchCorridor = 5;
	private static int newnumberOfMinutesToTakeOff;
	private static int newnumberOfMinutesToLand;
	private static int newnumberOfMinutesToSwitchCorridor;
	private static boolean newTakeOff = false;
	private static boolean newLand = false;
	private static boolean newSwitch = false;
	
	/**
	 * Creates a new airplane, with all its properties 
	 * @param flightID - the id of the flight
	 * @param statingPosition - the take off coordinates
	 * @param flightPlan - the plan of the flight
	 * @param armament - whether it carries armament or not
	 * @throws InvalidArgumentException 
	 */
	public Transport(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan, boolean armament) throws InvalidArgumentException {
		super(flightID, statingPosition, flightPlan, armament);
		if (newTakeOff)
			numberOfMinutesToTakeOff = newnumberOfMinutesToTakeOff;
		if (newLand)
			numberOfMinutesToLand = newnumberOfMinutesToLand;
		if (newSwitch)
			numberOfMinutesToSwitchCorridor = newnumberOfMinutesToSwitchCorridor;
		
		if(flightID==null || statingPosition == null || flightPlan == null)
			throw new InvalidArgumentException();
	}
	
	/**
	 * sets a new number of minutes for the take off of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to take off
	 */
	public void setNumberOfMinutesToTakeOff(int newTime)throws InvalidArgumentException
	{
		numberOfMinutesToTakeOff = newTime;
		newnumberOfMinutesToTakeOff = newTime;
		newTakeOff = true;
		if (newTime == 0)
			throw new InvalidArgumentException();
	}
	
	/**
	 * sets a new number of minutes for the land of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to land
	 */
	public void setNumberOfMinutesToLand(int newTime)throws InvalidArgumentException
	{
		numberOfMinutesToLand = newTime;
		newnumberOfMinutesToLand = newTime;
		newLand = true;
		if (newTime == 0)
			throw new InvalidArgumentException();
	}
	
	/**
	 * sets a new number of minutes for switching lanes of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to switch lanes
	 */
	public void setNumberOfMinutesToSwitchCorridor(int newTime)throws InvalidArgumentException
	{
		numberOfMinutesToSwitchCorridor = newTime;
		newnumberOfMinutesToSwitchCorridor = newTime;
		newSwitch = true;
		if (newTime == 0)
			throw new InvalidArgumentException();
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
}
