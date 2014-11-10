package utils;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import app.InvalidArgumentException;

/**
 * Creates an abstract class that envelops all the airplanes. 
 *@author Eva Gomes
 *@author Hugo Leal
 *@author Lucas Andrade
 *
 */
public abstract class Airship {
	
	private String flightID;
	private LinkedList<GeographicalPosition> lastKnownGeograficalPositions;
	private FlightPlan flightPlan;
	public int numberOfMinutesToTakeOff;
	public int numberOfMinutesToLand;
	public int numberOfMinutesToSwitchCorridor;
	private boolean positionWasUpdated = false;
	
	/**
	 * constructs an airplane with an ID, a certain number of passengers, its take off coordinates and the flightPlan
	 * @param flightID - the ID of the plane
	 * @param currentPassengers - the number of passengers
	 * @param statingPosition - the coordinates where the airplane will take off
	 * @param flightPlan - the plan of the flight
	 * @throws InvalidArgumentException 
	 */
	public Airship(String flightID, GeographicalPosition statingPosition, FlightPlan flightPlan) throws InvalidArgumentException {
		this.flightID = flightID;
		this.lastKnownGeograficalPositions = new LinkedList<GeographicalPosition>();
		lastKnownGeograficalPositions.add(statingPosition);
		this.flightPlan = flightPlan;
		
		if(flightID==null || statingPosition == null || flightPlan == null)
			throw new InvalidArgumentException();
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
	 * all the last known geographic positions of the aircraft
	 * @return an array of objects of type object, where all the known geographic positions of the
	 * aircraft are saved as GeographicalPosition objects 
	 */
	public Object[] getLastKnownGeographicPosition()
	{
		return lastKnownGeograficalPositions.toArray();
	}
	
	/**
	 * @param newGeographicalPosition - updates the geographical position to a new one
	 * @throws InvalidArgumentException 
	 */
	public void updateGeographicPosition(GeographicalPosition newGeographicalPosition) throws InvalidArgumentException
	{
		positionWasUpdated = true;
		lastKnownGeograficalPositions.addFirst(newGeographicalPosition);
		
		if(newGeographicalPosition==null)
			throw new InvalidArgumentException();
	}
	
	/**
	 * @return gets the corridor the airplane is planned to be in, at the time the method 
	 * was called
	 * @throws InvalidArgumentException 
	 */
	public AltitudeCorridor getCurrentCorridor() throws InvalidArgumentException
	{
		return flightPlan.getCurrentCorridor();
	}
	
	/**
	 * allows to set a new arrival time in case the airplane is running late
	 * @param newArrivalHour - the new planned hour for the arrival of the airplane in its destination
	 * @throws InvalidArgumentException 
	 */
	public void setNewArrivalHour(Calendar newArrivalHour) throws InvalidArgumentException
	{
		flightPlan.setNewArrivalHour(newArrivalHour, numberOfMinutesToLand);
		if(newArrivalHour==null)
			throw new InvalidArgumentException();
	}
	
	/**
	 * @return the flight plan of the airplane
	 */
	public FlightPlan getPlan()
	{
		return flightPlan;
	}
	
	/**
	 * gets an observation on the state of the plane. A string is returned with the information whether the
	 * airplane is just cruising normally, switching corridors, gaining altitude after take off, or even 
	 * making its descent
	 * @return a string with information on the status of the airplane
	 * @throws InvalidArgumentException 
	 */
	public String getObservations() throws InvalidArgumentException
	{
		AltitudeCorridor corridor = this.getCurrentCorridor();
		if (corridor == null)
			return verifyStatus();
		else
			return verifyAltitude(corridor);
	}
	
	/**
	 * @return the date and hour the airplane is supposed to take off
	 */
	public Calendar getTakeOffDate()
	{
		return flightPlan.getTakeOffDate();
	}
	
	/**
	 * @return the date and hour the airplane is supposed to land
	 */
	public Calendar getLandingDate()
	{
		return flightPlan.getLandingDate();
	}
	
	/**
	 * @return true if the position was updated since the last time positionToString() was called
	 * @return false if it was not
	 */
	public boolean wasPositionUpdated()
	{
		return positionWasUpdated;
	}
	
	/**
	 * @return returns a string with the id, position, and observations about the airplane
	 * @throws InvalidArgumentException 
	 */
	public String positionToString() throws InvalidArgumentException
	{
		StringBuilder builder = new StringBuilder();
		GeographicalPosition pos = getGeographicPosition();
		builder.append(flightID).append(" ").append(pos.getLatitude()).append(" ").append(pos.getLongitude())
		.append(" ").append(pos.getAltitude()).append(" ").append(getObservations());
		return builder.toString();
	}
	
	/**
	 * sets positionWasUpdated to false
	 */
	protected void setToNotUpdated()
	{
		positionWasUpdated = false;
	}
	
	/**
	 * compares the altitude of the airplane with the corridor it is supposed to be in
	 * @param corridor - the corridor where the airplane is supposed to be in
	 * @return a string with the information of whether the airplane is cruising normally 
	 * (inside the corridor), or if the plane is outside of the corridor
	 * @throws InvalidArgumentException 
	 */
	private String verifyAltitude(AltitudeCorridor corridor) throws InvalidArgumentException {
		double min = corridor.getLowerLimit();
		double max = corridor.getUpperLimit();
		double altitude = getGeographicPosition().getAltitude();
		
		if (altitude > max || altitude < min) 
			return "WARNING: The airplane is outside of the corridor.";
		else
			return "";
	}

	/**
	 * verifies whether the plane is taking off, landing, or switching corridors
	 * @return a string with information on the status of the plane
	 */
	private String verifyStatus()
	{
		Calendar now = new GregorianCalendar();
		
		if (now.compareTo(getTakeOffDate()) < 0)
			return "The airplane has not taken off yet.";
		if (now.compareTo(getLandingDate()) > 0)
			return "The airplane has already landed.";
		
		int dateComparison = now.compareTo((flightPlan.getFirstEvent()).getEndingHour());
		
		if (dateComparison <= 0)
			return "The air plane has took off and is gaining altitude.";
			
		dateComparison = now.compareTo((flightPlan.getLastEvent()).getStartingHour());
		if (dateComparison >= 0)
			return "The airplane has started its descent in order to land.";
		else
			return "The airplane is switching corridors.";
	}
	
	/**
	 * sets a new number of minutes for the take off of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to take off
	 * @throws InvalidArgumentException 
	 */
	public abstract void setNumberOfMinutesToTakeOff(int newTime) throws InvalidArgumentException;
	
	/**
	 * sets a new number of minutes for the land of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to land
	 * @throws InvalidArgumentException 
	 */
	public abstract void setNumberOfMinutesToLand(int newTime) throws InvalidArgumentException;
	
	/**
	 * sets a new number of minutes for switching lanes of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to switch lanes
	 * @throws InvalidArgumentException 
	 */
	public abstract void setNumberOfMinutesToSwitchCorridor(int newTime) throws InvalidArgumentException;
	
	/**
	 * @return the number of minutes the airplanes of this class need to take off
	 */
	public abstract int getNumberOfMinutesToTakeOff();
	
	/**
	 * @return - the number of minutes the airplanes of this class need to land
	 */
	public abstract int getNumberOfMinutesToLand();
	
	/**
	 * @return - the number of minutes the airplanes of this class need to switch lanes
	 */
	public abstract int getNumberOfMinutesToSwitchCorridor();
	
//	/**
//	 * adds an event in the middle of the flight
//	 * @param newEvent - the new event to be added
//	 * @return - true if the event was successfully added
//	 * @throws InvalidArgumentException
//	 */
//	public boolean addMidFlightPlan(AirCorridorInTime newEvent) throws InvalidArgumentException
//	{
//		return flightPlan.addMidFlightPlan(newEvent, numberOfMinutesToSwitchCorridor);
//	}
}


