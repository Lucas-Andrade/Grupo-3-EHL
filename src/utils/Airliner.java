package utils;

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
	
	public Airliner(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan, int passengers) {
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
	 * sets flying to false and the number of passengers to 0
	 */
	@Override
	protected void land()
	{
		passengersNum = 0;
		flying = false;
	}
	
	/**
	 * sets a new number of minutes for the take off of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to take off
	 */
	public static void setNumberOfMinutesToTakeOff(int newTime)
	{
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
	public static void setNumberOfMinutesToLand(int newTime)
	{
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
	public static void setNumberOfMinutesToSwitchCorridor(int newTime)
	{
		numberOfMinutesToSwitchCorridor = newTime;
		newnumberOfMinutesToSwitchCorridor = newTime;
		newSwitch = true;
	}
	
	/**
	 * @return the number of minutes the airplanes of this class need to take off
	 */
	public static int getNumberOfMinutesToTakeOff()
	{
		return numberOfMinutesToTakeOff;
	}
	
	/**
	 * @return - the number of minutes the airplanes of this class need to land
	 */
	public static int getNumberOfMinutesToLand()
	{
		return numberOfMinutesToLand;
	}
	
	/**
	 * @return - the number of minutes the airplanes of this class need to switch lanes
	 */
	public static int getNumberOfMinutesToSwitchCorridor()
	{
		return numberOfMinutesToSwitchCorridor;
	}
}