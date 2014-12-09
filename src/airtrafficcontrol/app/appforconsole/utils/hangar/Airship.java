package airtrafficcontrol.app.appforconsole.utils.hangar;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates.GeographicalPosition;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.AltitudeCorridor;
import airtrafficcontrol.app.appforconsole.utils.airshipplan.FlightPlan;


/**
 * tem apenas plano de voo e corredor!
 */

/**
 * Abstract class that represents an Airship with fligthPlan and Corridor.
 * Extends {@link AirCraft}
 *
 * @author Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José
 *         Oliveira
 */
public abstract class Airship extends AirCraft
{
	
	/**
	 * The flight plan for this airship (take-off hour, air corridors and
	 * landing hour).
	 */
	private FlightPlan flightPlan;
	
	
	
	// CONSTRUCTOR
	
	/**
	 * Constructs an airplane with the flight plan {@code flightPlan}.
	 * 
	 * @param flightID
	 *            The flight's ID.
	 * @param statingPosition
	 *            The coordinates where the airship will take-off.
	 * @param flightPlan
	 *            The plan of the flight.
	 * @throws InvalidArgumentException
	 *             If {@code flightID}, {@code statingPosition} or
	 *             {@code flightPlan} are null.
	 */
	public Airship( String flightID, GeographicalPosition statingPosition,
			FlightPlan flightPlan ) throws InvalidArgumentException {
		super( flightID, statingPosition );
		if( flightPlan == null )
			throw new InvalidArgumentException();
		this.flightPlan = flightPlan;
	}
	
	/**
	 * @return gets the corridor the airplane is planned to be in, at the time
	 *         the method was called
	 * @throws InvalidArgumentException
	 */
	public AltitudeCorridor getCurrentCorridor() {
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
	public void setNewArrivalHour( Calendar newArrivalHour )
			throws InvalidArgumentException {
		if( newArrivalHour == null )
			throw new InvalidArgumentException();
		
		flightPlan.setNewArrivalHour( newArrivalHour );
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
		if( corridor == null )
			return verifyStatus();
		else return verifyAltitude( corridor );
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
	 * compares the altitude of the airplane with the corridor it is supposed to
	 * be in
	 * 
	 * @param corridor
	 *            - the corridor where the airplane is supposed to be in
	 * @return a string with the information of whether the airplane is cruising
	 *         normally (inside the corridor), or if the plane is outside of the
	 *         corridor
	 * @throws InvalidArgumentException
	 */
	private String verifyAltitude( AltitudeCorridor corridor ) {
		double altitude = getGeographicPosition().getAltitude();
		
		if( altitude > corridor.getUpperLimit()
				|| altitude < corridor.getLowerLimit() )
			return "WARNING: The airplane is outside of the corridor.";
		else return "";
	}
	
	/**
	 * verifies whether the plane is taking off, landing, or switching corridors
	 * 
	 * @return a string with information on the status of the plane
	 */
	private String verifyStatus() {
		Calendar now = new GregorianCalendar();
		
		if( now.compareTo( getTakeOffDate() ) < 0 )
			return "The airplane has not taken off yet.";
		if( now.compareTo( getLandingDate() ) > 0 )
			return "The airplane has already landed.";
		
		int dateComparison = now.compareTo( (flightPlan.getFirstEvent())
				.getEndingHour() );
		
		if( dateComparison <= 0 )
			return "The air plane has took off and is gaining altitude.";
		
		dateComparison = now.compareTo( (flightPlan.getLastEvent())
				.getStartingHour() );
		if( dateComparison >= 0 )
			return "The airplane has started its descent in order to land.";
		else return "The airplane is switching corridors.";
	}
	
	
	
	// TODO: ver depois
	
	/**
	 * Creates a list of the details of this airship in a string array.
	 * 
	 * @throws InvalidArgumentException
	 */
	public String[] toStringArray() {
		
		GeographicalPosition pos = getGeographicPosition();
		DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd,hh:mm" );
		String[] details = new String[3];
		details[0] = new StringBuilder( "FlightID: " )
				.append( getFlightID() )
				.append( "\n\nTake-Off date: " )
				.append(
						dateFormat
								.format( getPlan().getTakeOffDate().getTime() ) )
				.append( "\nLanding date: " )
				.append(
						dateFormat
								.format( getPlan().getLandingDate().getTime() ) )
				.toString();
		details[1] = new StringBuilder( "\n\nGeographic Position\nLatitude: " )
				.append( (new Double( pos.getLatitude() )).toString() )
				.append( "º\nLongitude: " )
				.append( (new Double( pos.getLongitude() )).toString() )
				.append( "º\nAltitude: " )
				.append( (new Double( pos.getAltitude() )).toString() )
				.append( "meters" ).toString();
		try
		{
			details[2] = "\n\nObservations: " + getObservations();
		}
		catch( InvalidArgumentException e )
		{
			details[2] = "\n\nObservations: ERROR getting observations about the flight.";
		}
		return details;
		
	}
	
}
