package tests;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;

import app.InvalidArgumentException;
import utils.AltitudeCorridor;
import utils.FlightPlan;
import utils.GeographicalPosition;

/**
 * Creates an abstract class that envelops all the airplanes.
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
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
	 * constructs an airplane with an ID, a certain number of passengers, its
	 * take off coordinates and the flightPlan
	 * 
	 * @param flightID
	 *            - the ID of the plane
	 * @param currentPassengers
	 *            - the number of passengers
	 * @param statingPosition
	 *            - the coordinates where the airplane will take off
	 * @param flightPlan
	 *            - the plan of the flight
	 */
	public Airship(String flightID, GeographicalPosition statingPosition,
			FlightPlan flightPlan) {
		this.flightID = flightID;
		this.lastKnownGeograficalPositions = new LinkedList<GeographicalPosition>();
		lastKnownGeograficalPositions.add(statingPosition);
		this.flightPlan = flightPlan;
	}

	/**
	 * @return the ID of the plane
	 */
	public String getFlightID() {
		return flightID;
	}

	/**
	 * @return the geographical position of the airplane
	 */
	public GeographicalPosition getGeographicPosition() {
		return lastKnownGeograficalPositions.getFirst();
	}

	/**
	 * all the last known geographic positions of the aircraft
	 * 
	 * @return an array of objects of type object, where all the known
	 *         geographic positions of the aircraft are saved as
	 *         GeographicalPosition objects
	 */
	public Object[] getLastKnownGeographicPosition() {
		return lastKnownGeograficalPositions.toArray();
	}

	/**
	 * @param newGeographicalPosition
	 *            - updates the geographical position to a new one
	 */
	public void updateGeographicPosition(
			GeographicalPosition newGeographicalPosition) {
		positionWasUpdated = true;
		lastKnownGeograficalPositions.addFirst(newGeographicalPosition);
	}

	/**
	 * @return gets the corridor the airplane is planned to be in, at the time
	 *         the method was called
	 * @throws InvalidArgumentException
	 */
	public AltitudeCorridor getCurrentCorridor()
			throws InvalidArgumentException {
		return flightPlan.getCurrentCorridor();
	}

	/**
	 * allows to set a new arrival time in case the airplane is running late
	 * 
	 * @param newArrivalHour
	 *            - the new planned hour for the arrival of the airplane in its
	 *            destination
	 * @throws InvalidArgumentException
	 */
	public void setNewArrivalHour(Calendar newArrivalHour)
			throws InvalidArgumentException {
		flightPlan.setNewArrivalHour(newArrivalHour, numberOfMinutesToLand);
	}

	/**
	 * @return the flight plan of the airplane
	 */
	public FlightPlan getPlan() {
		return flightPlan;
	}

	/**
	 * gets an observation on the state of the plane. A string is returned with
	 * the information whether the airplane is just cruising normally, switching
	 * corridors, gaining altitude after take off, or even making its descent
	 * 
	 * @return a string with information on the status of the airplane
	 * @throws InvalidArgumentException
	 */
	public String getObservations() throws InvalidArgumentException {
		AltitudeCorridor corridor = this.getCurrentCorridor();
		if (corridor == null)
			return verifyStatus();
		else
			return verifyAltitude(corridor);
	}

	/**
	 * @return the date and hour the airplane is supposed to take off
	 */
	public Calendar getTakeOffDate() {
		return flightPlan.getTakeOffDate();
	}

	/**
	 * @return the date and hour the airplane is supposed to land
	 */
	public Calendar getLandingDate() {
		return flightPlan.getLandingDate();
	}

	/**
	 * @return true if the position was updated since the last time
	 *         positionToString() was called
	 * @return false if it was not
	 */
	public boolean wasPositionUpdated() {
		return positionWasUpdated;
	}

	/**
	 * @return returns a string with the id, position, and observations about
	 *         the airplane
	 * @throws InvalidArgumentException
	 */
	public String positionToString() throws InvalidArgumentException {
		StringBuilder builder = new StringBuilder();
		GeographicalPosition pos = getGeographicPosition();
		builder.append(flightID).append(" ").append(pos.getLatitude())
				.append(" ").append(pos.getLongitude()).append(" ")
				.append(pos.getAltitude()).append(" ")
				.append(getObservations());
		return builder.toString();
	}

	/**
	 * sets positionWasUpdated to false
	 */
	protected void setToNotUpdated() {
		positionWasUpdated = false;
	}

	/**
	 * compares the altitude of the airplane with the corridor it is supposed to
	 * be in
	 * 
	 * @param corridor
	 *            - the corridor where the airplane is supposed to be in
	 * @return a string with the information of whether the airplane is cruising
	 *         normally (inside the corridor), or if the plane is outside of the
	 *         corridor
	 */
	private String verifyAltitude(AltitudeCorridor corridor) {
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
	 * 
	 * @return a string with information on the status of the plane
	 */
	private String verifyStatus() {
		Calendar now = new GregorianCalendar();

		if (now.compareTo(getTakeOffDate()) < 0)
			return "The airplane has not taken off yet.";
		if (now.compareTo(getLandingDate()) > 0)
			return "The airplane has already landed.";

		int dateComparison = now.compareTo((flightPlan.getFirstEvent())
				.getEndingHour());

		if (dateComparison <= 0)
			return "The air plane has took off and is gaining altitude.";

		dateComparison = now.compareTo((flightPlan.getLastEvent())
				.getStartingHour());
		if (dateComparison >= 0)
			return "The airplane has started its descent in order to land.";
		else
			return "The airplane is switching corridors.";
	}

	/**
	 * Creates a list of the details of this airship in a string array.
	 * 
	 * @throws InvalidArgumentException
	 */
	public String[] toStringArray() throws InvalidArgumentException {

		GeographicalPosition pos = getGeographicPosition();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd,hh:mm");
		String[] details = new String[3];
		details[0] = new StringBuilder("FlightID: ")
				.append(flightID)
				.append("\n\nTake-Off date: ")
				.append(dateFormat.format(getPlan().getTakeOffDate().getTime()))
				.append("\nLanding date: ")
				.append(dateFormat.format(getPlan().getLandingDate().getTime()))
				.toString();
		details[1] = new StringBuilder("\n\nGeographic Position\nLatitude: ")
				.append((new Double(pos.getLatitude())).toString())
				.append("�\nLongitude: ")
				.append((new Double(pos.getLongitude())).toString())
				.append("�\nAltitude: ")
				.append((new Double(pos.getAltitude())).toString())
				.append("meters").toString();
		details[2] = "\n\nObservations: " + getObservations();
		return details;

	}

}
