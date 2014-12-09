package airtrafficcontrol.app.appforconsole.utils.airshipplan;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;


/**
 * Allows to build the flight plan
 *
 * @author Eva Gomes, Hugo Leal, Lucas Andrade
 * @author (revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public class FlightPlan
{
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
	
	
	/**
	 * The number of minutes the airship has to take-off and reach the first air
	 * corridor.
	 */
	private int numberOfMinutesToTakeOff;
	
	/**
	 * The number of minutes the airship has to land so that when he abandons
	 * the current established air corridor, this occurrence will not be reported
	 * as an error.
	 */
	private int numberOfMinutesToLand;
	
	/**
	 * The number of minutes the airship has to switch from an established
	 * altitude corridor to next one established.
	 */
	private int numberOfMinutesToSwitchCorridor;
	
	private int newnumberOfMinutesToTakeOff;
	private int newnumberOfMinutesToLand;
	private int newnumberOfMinutesToSwitchCorridor;
	private boolean newTakeOff = false;
	private boolean newLand = false;
	private boolean newSwitch = false;
	
	
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
	public FlightPlan( Calendar departureHour, Calendar arrivalHour, int MinutesToTakeOff,
			int MinutesToLand, int MinutesToSwitchCorridor  )
			throws InvalidArgumentException
	{		
		if( departureHour == null || arrivalHour == null )
			throw new InvalidArgumentException( "INVALID NULL HOUR!" );
		if( departureHour.compareTo( arrivalHour ) >= 0 )
			throw new InvalidArgumentException(
					"INVALID HOURS, DEPARTURE MUST OCCUR BEFORE ARRIVAL!" );
		if( MinutesToTakeOff <= 0 || MinutesToLand <=0 || MinutesToSwitchCorridor <= 0)
			throw new InvalidArgumentException(
					"THE PARAMETERS OF THE FLIGTH MUST BE POSITIVE!" );
				
		corridors = new ArrayList<>();
		this.departureHour = departureHour;
		this.arrivalHour = arrivalHour;
		this.numberOfMinutesToTakeOff = MinutesToTakeOff;
		this.numberOfMinutesToLand = MinutesToLand;
		this.numberOfMinutesToSwitchCorridor = MinutesToSwitchCorridor;
		
		if (newTakeOff)
			numberOfMinutesToTakeOff = newnumberOfMinutesToTakeOff;
		if (newLand)
			numberOfMinutesToLand = newnumberOfMinutesToLand;
		if (newSwitch)
			numberOfMinutesToSwitchCorridor = newnumberOfMinutesToSwitchCorridor;
	}

	/**
	 * adds a new event to the list
	 * 
	 * @param newEvent
	 *            - event to add
	 * @throws InvalidArgumentException
	 */
	public boolean addEvent( AirCorridorInTime newEvent )
			throws InvalidArgumentException
	{
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
	public AltitudeCorridor getCurrentCorridor()
	{		
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
			throws InvalidArgumentException
	{		
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
	public void setNewArrivalHour( Calendar newArrivalHour) throws InvalidArgumentException
	{
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
	public List< AirCorridorInTime > getListOfCorridors()
	{
		return corridors;
	}
	
	/**
	 * @return the first event in the plan
	 */
	public AirCorridorInTime getFirstEvent()
	{
		return corridors.get( 0 );
	}
	
	/**
	 * @return the last event in the plan
	 */
	public AirCorridorInTime getLastEvent()
	{
		return corridors.get( corridors.size() - 1 );
	}
	
	/**
	 * @return the date and hour of when is the take off supposed to happen
	 */
	public Calendar getTakeOffDate()
	{
		return departureHour;
	}
	
	/**
	 * @return the date and hour of when the airplane is supposed to happen
	 */
	public Calendar getLandingDate()
	{
		return arrivalHour;
	}



	
	
	/**
	 * sets a new number of minutes for the take off of this class' airplanes.
	 * this will affect all the airplanes of this type, that were already constructed
	 * and all that will be constructed in the future
	 * @param newTime - the new number of minutes this class of airplane needs to take off
	 */
	public void setNumberOfMinutesToTakeOff(int newTime) throws InvalidArgumentException
	{
		if (newTime == 0)
			throw new InvalidArgumentException();
		
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
	public void setNumberOfMinutesToLand(int newTime) throws InvalidArgumentException
	{
		if (newTime == 0)
			throw new InvalidArgumentException();
		
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
	public void setNumberOfMinutesToSwitchCorridor(int newTime) throws InvalidArgumentException
	{
		if (newTime == 0)
			throw new InvalidArgumentException();
		
		numberOfMinutesToSwitchCorridor = newTime;
		newnumberOfMinutesToSwitchCorridor = newTime;
		newSwitch = true;
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
