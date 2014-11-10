package utils;
import java.util.Calendar;

import app.InvalidArgumentException;

/**
 * allows to create objects that have a property AltitudeCorridor, where the airplane is supposed to be 
 * in the time interval defined by the starting and ending hours.
 * @author Lucas
 *
 */
public class AirCorridorInTime {
	
	private Calendar startingHour;
	private Calendar endingHour;
	private AltitudeCorridor corridor;
	
	/**
	 * constructs a new AirCorridorInTime by setting up all its properties
	 * @param start - the hour the airplane is supposed to go to the corridor
	 * @param end - the hour the airplane is supposed to leave the corridor
	 * @param cor - the corridor
	 */
	public AirCorridorInTime(Calendar start, Calendar end, AltitudeCorridor cor) throws InvalidArgumentException
	{
		corridor = cor; 
		
		if (start.compareTo(end) > 0)
		{
			endingHour = start;
			startingHour = end;
		}
		else
		{
			endingHour = end;
			startingHour = start;
		}
		if(start==null || end==null || cor==null) throw new InvalidArgumentException();
	}
	
	/**
	 * @return the hour the airplane is supposed to go to the corridor
	 */
	public Calendar getStartingHour()
	{
		return startingHour;
	}
	
	/**
	 * @return the hour the airplane is supposed to leave the corridor
	 */
	public Calendar getEndingHour()
	{
		return endingHour;
	}
	
	/**
	 * sets a new starting hour
	 * @param newHour - the hour the airplane is supposed to go to the corridor
	 */
	public boolean setStartingHour(Calendar newHour)throws InvalidArgumentException
	{
		if(newHour==null)
			throw new InvalidArgumentException();
		
		if(endingHour.compareTo(newHour) < 0)
			return false;
		else
		{
			startingHour = newHour;
			return true;
		}
		
		
	}
	
	/**
	 * sets a new ending hour
	 * @param newHour - the hour the airplane is supposed to leave the corridor
	 */
	public boolean setEndingHour(Calendar newHour)throws InvalidArgumentException
	{
		if(newHour==null)
			throw new InvalidArgumentException();
		
		if(startingHour.compareTo(newHour) > 0)
			return false;
		else
		{
			endingHour = newHour;
			return true;
		}
	}
	
	/**
	 * @return the corridor where the airplane is supposed to be
	 */
	public AltitudeCorridor getCorridor()
	{
		return corridor;
	}
	
}
