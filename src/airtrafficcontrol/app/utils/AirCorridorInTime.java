package airtrafficcontrol.app.utils;


import java.util.Calendar;
import airtrafficcontrol.app.exceptions.InvalidArgumentException;


/**
 * Class whose instances represent an air corridor where it is expected that a
 * certain airship flies during a certain period of time. These class's
 * instances are supposed to always be properties of instances of
 * {@link Airship}'s subclasses.
 * 
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 *
 */
public class AirCorridorInTime
{
	
	// CAMPOS
	
	/**
	 * The hour from which the airship should be in this altitude corridor.
	 */
	private Calendar startingHour;
	
	/**
	 * The hour until which the airship should be in this altitude corridor.
	 */
	private Calendar endingHour;
	
	/**
	 * The altitude corridor in question.
	 */
	private AltitudeCorridor corridor;
	
	
	
	// CONSTRUCTOR
	
	/**
	 * Constructs a new instance of {@link AirCorridorInTime} by setting up all
	 * its properties. If {@code start} is a date before {@code end}, the dates
	 * are automatically swapped.
	 * 
	 * @param start
	 *            The approximate hour the airplane is supposed to enter the
	 *            corridor.
	 * @param end
	 *            The approximate hour the airplane is supposed to leave the
	 *            corridor.
	 * @param altCorridor
	 *            The altitude corridor.
	 */
	public AirCorridorInTime( Calendar start, Calendar end,
			AltitudeCorridor altCorridor ) throws InvalidArgumentException {
		
		if( start == null || end == null )
			throw new InvalidArgumentException(
					"INVALID NULL DATES FOR CORRIDOR!" );
		
		corridor = altCorridor;
		
		if( start.compareTo( end ) > 0 )
		{
			endingHour = start;
			startingHour = end;
		}
		else
		{
			endingHour = end;
			startingHour = start;
		}
	}
	
	
	
	// GETTER's
	
	
	/**
	 * Returns the approximate hour the airplane is supposed to enter the
	 * corridor.
	 * 
	 * @return The approximate hour the airplane is supposed to enter the
	 *         corridor.
	 */
	public Calendar getStartingHour() {
		return startingHour;
	}
	
	
	/**
	 * Returns the approximate hour the airplane is supposed to leave the
	 * corridor.
	 * 
	 * @return The approximate hour the airplane is supposed to leave the
	 *         corridor.
	 */
	public Calendar getEndingHour() {
		return endingHour;
	}
	
	
	/**
	 * Returns the altitude air corridor where the airship is supposed to be.
	 * 
	 * @return The altitude air corridor where the airship is supposed to be.
	 */
	public AltitudeCorridor getCorridor() {
		return corridor;
	}
	
	
	
	// SETTER's
	
	
	/**
	 * Sets the approximate hours the airplane is supposed to enter and to leave
	 * the altitude corridor.
	 * 
	 * @param newStartingHour
	 *            The approximate hour the airplane is supposed to enter the
	 *            corridor.
	 * @param newEndingHour
	 *            The approximate hour the airplane is supposed to leave the
	 *            corridor.
	 */
	public boolean setHours( Calendar newStartingHour, Calendar newEndingHour )
			throws InvalidArgumentException {
		
		if( newStartingHour == null || newEndingHour == null )
			throw new InvalidArgumentException(
					"INVALID NULL DATES FOR CORRIDOR!" );
		
		if( newEndingHour.compareTo( newStartingHour ) < 0 )
			return false;
		else
		{
			startingHour = newStartingHour;
			endingHour = newEndingHour;
			return true;
		}
		
		
	}
	
	/**
	 * Sets an approximate hour the airplane is supposed to enter the corridor.
	 * 
	 * @param newHour
	 *            The approximate hour the airplane is supposed to enter the
	 *            corridor.
	 */
	public boolean setStartingHour( Calendar newHour )
			throws InvalidArgumentException {
		if( newHour == null )
			throw new InvalidArgumentException();
		
		if( endingHour.compareTo( newHour ) < 0 )
			return false;
		else
		{
			startingHour = newHour;
			return true;
		}
		
		
	}
	
	/**
	 * sets a new ending hour
	 * 
	 * @param newHour
	 *            - the hour the airplane is supposed to leave the corridor
	 */
	public boolean setEndingHour( Calendar newHour )
			throws InvalidArgumentException {
		if( newHour == null )
			throw new InvalidArgumentException();
		
		if( startingHour.compareTo( newHour ) > 0 )
			return false;
		else
		{
			endingHour = newHour;
			return true;
		}
	}
	
}
