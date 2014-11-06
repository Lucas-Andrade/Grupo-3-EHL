package utils;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

/**
 * Creates an abstract class that envelops all the airplanes. 
 * @author Lucas Andrade
 *
 */
public abstract class Airship {
	
	private String flightID;
	private LinkedList<GeographicalPosition> lastKnownGeograficalPositions;
	private FlightPlan flightPlan;
	private int numberOfMinutesToTakeOff;
	private int numberOfMinutesToLand;
	private int numberOfMinutesToSwitchCorridor;
	protected boolean flying;
	
	/**
	 * constructs an airplane with an ID, a certain number of passengers, its take off coordinates and the flightPlan
	 * @param flightID - the ID of the plane
	 * @param currentPassengers - the number of passengers
	 * @param statingPosition - the coordinates where the airplane will take off
	 * @param flightPlan - the plan of the flight
	 */
	public Airship(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan) {
		this.flightID = flightID;
		this.lastKnownGeograficalPositions = new LinkedList<GeographicalPosition>();
		lastKnownGeograficalPositions.add(statingPosition);
		this.flightPlan = flightPlan;
		flying = false;
	}

	/**
	 * @return the ID of the plane
	 */
	public String getFlightID()
	{
		return flightID;
	}
	
	/**
	 * @return the geographical position of the airplane
	 */
	public GeographicalPosition getGeographicPosition()
	{
		return lastKnownGeograficalPositions.getFirst();
	}
	
	/**
	 * @param newGeographicalPosition - updates the geographical position to a new one
	 */
	public void updateGeographicPosition(GeographicalPosition newGeographicalPosition)
	{
		lastKnownGeograficalPositions.addFirst(newGeographicalPosition);
	}
	
	/**
	 * @return gets the corridor the airplane is planned to be in, at the time the method 
	 * was called
	 */
	public AltitudeCorridor getCurrentCorridor()
	{
		return flightPlan.getCurrentCorridor();
	}
	
	/**
	 * allows to set a new arrival time in case the airplane is running late
	 * @param newArrivalHour - the new planned hour for the arrival of the airplane in its destination
	 */
	public void setNewArrivalHour(Calendar newArrivalHour)
	{
		flightPlan.setNewArrivalHour(newArrivalHour, numberOfMinutesToLand);
	}
	
	/**
	 * gets an observation on the state of the plane. A string is returned with the information whether the
	 * airplane is just cruising normally, switching corridors, gaining altitude after take off, or even 
	 * making its descent
	 * @return a string with information on the status of the airplane
	 */
	public String getObservations()
	{
		AltitudeCorridor corridor = this.getCurrentCorridor();
		if (corridor == null)
			return verifyStatus();
		else
			return verifyAltitude(corridor);
	}
	
	/**
	 * sets flying to true
	 */
	protected void takeOff()
	{
		flying = true;
	}
	
	/**
	 * sets flying to false and the number of passengers to 0
	 */
	protected void land()
	{
		flying = false;
	}
	
	/**
	 * @return true if the airplane is flying
	 * @return false if the airplane is not flying
	 */
	public boolean isFlying()
	{
		return flying;
	}
	
	
	/**
	 * compares the altitude of the airplane with the corridor it is supposed to be in
	 * @param corridor - the corridor where the airplane is supposed to be in
	 * @return a string with the information of whether the airplane is cruising normally 
	 * (inside the corridor), or if the plane is outside of the corridor
	 */
	private String verifyAltitude(AltitudeCorridor corridor) {
		int min = corridor.getLowerLimit();
		int max = corridor.getUpperLimit();
		double altitude = lastKnownGeograficalPositions.getFirst().getAltitude();
		
		if (altitude <= max && altitude >= min) 
			return "";
		else
			return "WARNING: The airplane is outside of the corridor.";
	}

	/**
	 * verifies whether the plane is taking off, landing, or switching corridors
	 * @return a string with information on the status of the plane
	 */
	private String verifyStatus()
	{
		Calendar now = new GregorianCalendar();
		
		int dateComparison = now.compareTo((flightPlan.getFirstEvent()).getEndingHour());
		
		if (dateComparison <= 0)
			return "The air plane has took off and is gaining altitude.";
			
		dateComparison = now.compareTo((flightPlan.getLastEvent()).getStartingHour());
		if (dateComparison >= 0)
			return "The airplane has started its descent in order to land.";
		else
			return "The airplane is switching corridors.";
	}
	
	
	
	
	
	
	
	
	
	
	
}
