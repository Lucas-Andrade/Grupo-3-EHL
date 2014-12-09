package airtrafficcontrol.app.appforconsole.utils;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;


/**
 * allows to build the plan of the flight
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 *
 */
public class FlightPlan
{
	
	// CAMPOS
	
	/**
	 * The departure hour.
	 */
	private Calendar departureHour;
	
	/**
	 * The arrival hour.
	 */
	private Calendar arrivalHour;
	
	/**
	 * The plan of changing of air corridors.
	 */
	private List< AirCorridorInTime > corridors;
	
	
	// CONSTRUTOR
	
	
	/**
	 * Creates a new instance of FlightPlan. Stores the hour when the take-off
	 * and the landing will happen and initializes a new empty list where the
	 * plan of the flight will be saved (the air corridor changes).
	 * 
	 * @param departureHour
	 *            When the plane takes off.
	 * @param arrivalHour
	 *            When the plane lands.
	 * @throws InvalidArgumentException
	 */
	public FlightPlan( Calendar departureHour, Calendar arrivalHour )
			throws InvalidArgumentException {
		
		if( departureHour == null || arrivalHour == null )
			throw new InvalidArgumentException( "INVALID NULL HOUR!" );
		if( departureHour.compareTo( arrivalHour ) >= 0 )
			throw new InvalidArgumentException(
					"INVALID HOURS, DEPARTURE MUST OCCUR BEFORE ARRIVAL!" );
		
		corridors = new ArrayList<>();
		this.departureHour = departureHour;
		this.arrivalHour = arrivalHour;
	}
	
	
	
	// METODOS
	
	
	/**
	 * adds a new event to the list
	 * 
	 * @param newEvent
	 *            - event to add
	 * @throws InvalidArgumentException
	 */
	public boolean addEvent( AirCorridorInTime newEvent )
			throws InvalidArgumentException {
		if( newEvent == null )
			throw new InvalidArgumentException();
		
		Calendar startOfNew = newEvent.getStartingHour();
		Calendar endOfNew = newEvent.getEndingHour();
		
		if( startOfNew.compareTo( departureHour ) < 0
				|| endOfNew.compareTo( arrivalHour ) > 0 )
			return false;
		
		
		int corridorsNum = corridors.size();
		
		if( corridorsNum != 0 )
		{
			Calendar endOfPrevious = corridors.get( corridorsNum - 1 )
					.getEndingHour();
			
			if( endOfPrevious.compareTo( startOfNew ) == 0 )
			{
				corridors.add( newEvent );
				return true;
			}
			else return false;
		}
		
		corridors.add( newEvent );
		return true;
	}
	
	/**
	 * @return the corridor where the airplane is supposed to be in, at the time
	 *         when the method was called
	 * @throws InvalidArgumentException
	 */
	public AltitudeCorridor getCurrentCorridor() {
		
		Calendar now = new GregorianCalendar();
		try
		{
			return getCorridorAtTime( now );
		}
		catch( InvalidArgumentException e )
		{
			return null;
			// this catch will never happen as «new GregorianCalendar()» always
			// produces a non-null instance of Calendar
		}
	}
	
	/**
	 * returns the corridor where the airplane is supposed to be at the date
	 * passed as parameter
	 * 
	 * @param time
	 *            - date when we want to know the corridor
	 * @return corridor where the airplane is supposed to be, at the date passed
	 *         as parameter
	 * @throws InvalidArgumentException
	 */
	public AltitudeCorridor getCorridorAtTime( Calendar time )
			throws InvalidArgumentException {
		
		if( time == null )
			throw new InvalidArgumentException();
		
		if( time.compareTo( departureHour ) < 0
				|| time.compareTo( arrivalHour ) > 0 )
			return null;
		
		for( int i = 0; i < corridors.size(); i++ )
		{
			AirCorridorInTime corridor = corridors.get( i );
			if( time.compareTo( corridor.getStartingHour() ) >= 0
					&& time.compareTo( corridor.getEndingHour() ) <= 0 ) { return corridor
					.getCorridor(); }
		}
		
		return null;
		
		// TODO
		// throw new AirCorridorNotFoundException();
	}
	
	/**
	 * sets a new hour for the plane's landing
	 * 
	 * @param newArrivalHour
	 *            - the hour when the plane is now supposed to land
	 * @param numberOfMinutesToLand
	 *            - the number of minutes the plane needs to descend from the
	 *            last corridor it was in, until it lands
	 * @throws InvalidArgumentException
	 */
	public void setNewArrivalHour( Calendar newArrivalHour,
			int numberOfMinutesToLand ) throws InvalidArgumentException {
		arrivalHour = newArrivalHour;
		
		if( newArrivalHour == null )
			throw new InvalidArgumentException();
		
		int lengthOfList = corridors.size();
		AirCorridorInTime lastEvent = corridors.get( lengthOfList - 1 );
		lastEvent.setHours(lastEvent.getStartingHour(), newArrivalHour );
		
		newArrivalHour.add( 12, -numberOfMinutesToLand );
		lastEvent.setHours( newArrivalHour, lastEvent.getEndingHour() );
		
		corridors.set( lengthOfList - 1, lastEvent );
		
		AirCorridorInTime secondToLastEvent = corridors.get( lengthOfList - 2 );
		secondToLastEvent.setHours(secondToLastEvent.getStartingHour(), newArrivalHour );
		corridors.set( lengthOfList - 2, secondToLastEvent );
	}
	
	/*
	 * @return the list of all the AirCorridorInTime
	 */
	public List< AirCorridorInTime > getListOfCorridors() {
		return corridors;
	}
	
	/**
	 * @return the first event in the plan
	 */
	public AirCorridorInTime getFirstEvent() {
		return corridors.get( 0 );
	}
	
	/**
	 * @return the last event in the plan
	 */
	public AirCorridorInTime getLastEvent() {
		return corridors.get( corridors.size() - 1 );
	}
	
	/**
	 * @return the date and hour of when is the take off supposed to happen
	 */
	public Calendar getTakeOffDate() {
		return departureHour;
	}
	
	/**
	 * @return the date and hour of when the airplane is supposed to happen
	 */
	public Calendar getLandingDate() {
		return arrivalHour;
	}
	
	// /**
	// * adds an event in the middle of the flight
	// * @param newEvent - the new event to be added
	// * @return - true if the event was successfully added
	// * @throws InvalidArgumentException
	// */
	// public boolean addMidFlightPlan(AirCorridorInTime newEvent, int
	// timeToSwitchCorridor) throws InvalidArgumentException
	// {
	// Calendar startOfNewEvent = newEvent.getStartingHour();
	// Calendar endOfNewEvent = newEvent.getEndingHour();
	//
	// if ((startOfNewEvent).compareTo(departureHour) < 0 ||
	// (endOfNewEvent).compareTo(arrivalHour) > 0)
	// return false; //the new event cannot be after landing or before take off
	//
	// if (startOfNewEvent.compareTo(getFirstEvent().getEndingHour()) < 0)//the
	// new event cannot interrupt take off
	// {
	// startOfNewEvent = getFirstEvent().getEndingHour();
	// newEvent.setStartingHour(startOfNewEvent);
	// }
	//
	// if (endOfNewEvent.compareTo(getLastEvent().getStartingHour()) > 0)//the
	// new event cannot interrupt landing
	// {
	// endOfNewEvent = getLastEvent().getStartingHour();
	// newEvent.setEndingHour(endOfNewEvent);
	// }
	//
	// for (int i = 0; i < corridors.size(); i++)
	// {
	// if (corridors.get(i).getEndingHour().compareTo(startOfNewEvent) > 0) //
	// Accommodate the new event
	// {
	// Calendar endOfSwitchingCorridor =
	// ReadListOfFlights.defensiveCopyOfTheDate(startOfNewEvent);
	// endOfSwitchingCorridor.add(Calendar.MINUTE, timeToSwitchCorridor);
	//
	// corridors.get(i).setEndingHour(startOfNewEvent);
	// corridors.add(i + 1, new AirCorridorInTime(startOfNewEvent,
	// endOfSwitchingCorridor, null));
	// corridors.add(i + 2, new AirCorridorInTime(endOfSwitchingCorridor,
	// endOfNewEvent, newEvent.getCorridor()));
	// }
	//
	// if (corridors.get(i).getEndingHour().compareTo(endOfNewEvent) < 0)
	// {
	// corridors.remove(i); // remove events that should occur during the new
	// event
	// }
	//
	// if (corridors.get(i).getStartingHour().compareTo(endOfNewEvent) < 0 &&
	// corridors.get(i).getEndingHour().compareTo(endOfNewEvent) > 0)
	// {
	// if (corridors.get(i).getCorridor() == null) //if the airplane was to be
	// switching corridors at this time, the event is pushed a bit forward in
	// time.
	// {
	// Calendar switchingCorridor =
	// ReadListOfFlights.defensiveCopyOfTheDate(endOfNewEvent);
	// corridors.get(i).setStartingHour(switchingCorridor);
	//
	// switchingCorridor.add(Calendar.MINUTE, timeToSwitchCorridor);
	// corridors.get(i).setEndingHour(switchingCorridor);
	//
	// corridors.get(i + 1).setStartingHour(switchingCorridor);
	// }
	// else // if the airplane was supposed to be in another corridor at this
	// time, it should be given time to get there
	// {
	// Calendar switchingCorridor =
	// ReadListOfFlights.defensiveCopyOfTheDate(endOfNewEvent);
	// switchingCorridor.add(Calendar.MINUTE, timeToSwitchCorridor);
	//
	// corridors.add(i, new AirCorridorInTime(endOfNewEvent, switchingCorridor,
	// null));
	//
	// corridors.get(i + 1).setStartingHour(switchingCorridor);
	// }
	// }
	// }
	//
	//
	// return true;
	// }
	
}
