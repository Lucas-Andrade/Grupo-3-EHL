package utils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * allows to build the plan of the flight
 * @author Lucas
 *
 */
public class FlightPlan {
	
	private Calendar departureHour;
	private Calendar arrivalHour;
	private List<AirCorridorInTime> corridors;
	
	/**
	 * initializes a new empty list where the plan of the flight will be saved, and allows to save 
	 * the hour when the take off and landing are supposed to happen
	 * @param departureHour - when the plane takes off
	 * @param arrivalHour - then the plane lands
	 */
	public FlightPlan(Calendar departureHour, Calendar arrivalHour)
	{
		corridors = new ArrayList<>();
		this.departureHour = departureHour;
		this.arrivalHour = arrivalHour;
	}
	
	/**
	 * adds a new event to the list
	 * @param newEvent - event to add
	 */
	public boolean addEvent(AirCorridorInTime newEvent)
	{
		Calendar startOfNew = newEvent.getStartingHour();
		Calendar endOfNew = newEvent.getEndingHour();
		
		if (startOfNew.compareTo(departureHour) < 0 || endOfNew.compareTo(arrivalHour) > 0)
			return false;
		
		
		int corridorsNum = corridors.size();
		
		if (corridorsNum != 0)
		{
			Calendar endOfPrevious = corridors.get(corridorsNum - 1).getEndingHour();
			
			if (endOfPrevious.compareTo(startOfNew) == 0)
			{
				corridors.add(newEvent);
				return true;
			}
			else
				return false;
		}
		
		corridors.add(newEvent);
		return true;
	}
	
	/**
	 * @return the corridor where the airplane is supposed to be in, at the time
	 * when the method was called
	 */
	public AltitudeCorridor getCurrentCorridor() {
		
		Calendar now = new GregorianCalendar();
		return getCorridorAtTime(now);
	}
	
	/**
	 * returns the corridor where the airplane is supposed to be at the date passed as parameter
	 * @param time - date when we want to know the corridor
	 * @return corridor where the airplane is supposed to be, at the date passed as parameter
	 */
	public AltitudeCorridor getCorridorAtTime(Calendar time) {
		
		if(time.compareTo(departureHour) < 0 || time.compareTo(arrivalHour) > 0)
			return null;
		
		for (int i = 0; i < corridors.size(); i++)
		{
			AirCorridorInTime corridor = corridors.get(i);
			if (time.compareTo(corridor.getStartingHour()) >= 0 && time.compareTo(corridor.getEndingHour()) <= 0)
			{
				return corridors.get(i).getCorridor();
			}
		}
		return null;
	}
	
	/**
	 * sets a new hour for the plane's landing
	 * @param newArrivalHour - the hour when the plane is now supposed to land
	 * @param numberOfMinutesToLand - the number of minutes the plane needs to descend from the last
	 * corridor it was in, until it lands
	 */
	public void setNewArrivalHour(Calendar newArrivalHour, int numberOfMinutesToLand) {
		arrivalHour = newArrivalHour;
		
		int lengthOfList = corridors.size();
		AirCorridorInTime lastEvent = corridors.get(lengthOfList - 1);
		lastEvent.setEndingHour(newArrivalHour);
		
		newArrivalHour.add(12, -numberOfMinutesToLand);
		lastEvent.setStartingHour(newArrivalHour);
		
		corridors.set(lengthOfList - 1, lastEvent);
		
		AirCorridorInTime secondToLastEvent = corridors.get(lengthOfList - 2);
		secondToLastEvent.setEndingHour(newArrivalHour);
		corridors.set(lengthOfList - 2, secondToLastEvent);
	}

	/*
	 * @return the list of all the AirCorridorInTime
	 */
	public List<AirCorridorInTime> getListOfCorridors() {
		return corridors;
	}
	
	/**
	 * @return the first event in the plan
	 */
	public AirCorridorInTime getFirstEvent()
	{
		return corridors.get(0);
	}
	
	/**
	 * @return the last event in the plan
	 */
	public AirCorridorInTime getLastEvent()
	{
		return corridors.get(corridors.size() - 1);
	}
}
