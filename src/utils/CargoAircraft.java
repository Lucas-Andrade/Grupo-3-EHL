package utils;

import app.InvalidArgumentException;

public class CargoAircraft extends CivilAirplane{

	private static int numberOfMinutesToTakeOff = 13;
	private static int numberOfMinutesToLand = 15;
	private static int numberOfMinutesToSwitchCorridor = 7;
	private static int newnumberOfMinutesToTakeOff;
	private static int newnumberOfMinutesToLand;
	private static int newnumberOfMinutesToSwitchCorridor;
	private static boolean newTakeOff = false;
	private static boolean newLand = false;
	private static boolean newSwitch = false;
	
	public CargoAircraft(String flightID, GeographicalPosition statingPosition,
			FlightPlan flightPlan) throws InvalidArgumentException {
		super(flightID, statingPosition, flightPlan);
		
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
	public void setNumberOfMinutesToTakeOff(int newTime) throws InvalidArgumentException
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
	public void setNumberOfMinutesToLand(int newTime) throws InvalidArgumentException
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
	public void setNumberOfMinutesToSwitchCorridor(int newTime) throws InvalidArgumentException
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
