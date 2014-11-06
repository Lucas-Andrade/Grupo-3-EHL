package AirTrafficControl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Database {

	private Map<String, Airship> database;
	
	private Database()
	{
		database = new HashMap<>();
	}
	
	public boolean addAirplane(Airship airplane)
	{
		if(database.containsKey(airplane.getFlightID()))
		{
			return false;
		}
		else
		{
			database.put(airplane.getFlightID(), airplane);
			return true;
		}
	}
	
	public boolean removeAirplane(String id)
	{
		if(database.containsKey(id))
		{
			database.remove(id);
			return true;
		}
		else
			return false;
	}
	
	public boolean removeAirplane(Airship airplane)
	{
		String id = airplane.getFlightID();
		if(database.containsKey(id))
		{
			database.remove(id);
			return true;
		}
		else
			return false;
	}
	
	public int removeAirplanesWithZeroPassengers()
	{
		Set<String> idSet = database.keySet();
		Iterator<String> iterator = idSet.iterator();
		int count = 0;
		
		while (iterator.hasNext())
		{
			Airship airplane = database.get(iterator.next());
			if(airplane.getClass().isInstance(Airliner.class) && ((Airliner) airplane).getPassengersNumber() == 0)
			{
				removeAirplane(airplane.getFlightID());
				count++;
			}
		}
		return count;
	}
	
	public String[] reportAirplanesOutOfCorridor()
	{
		ArrayList<String> airplanesOut = new ArrayList<>();
		
		Set<String> idSet = database.keySet();
		Iterator<String> iterator = idSet.iterator();
		
		while (iterator.hasNext())
		{
			Airship airplane = database.get(iterator.next());
			AltitudeCorridor corridor = airplane.getCurrentCorridor();
			double altitude = airplane.getGeographicPosition().getAltitude();
			
			if(altitude < corridor.getLowerLimit() || altitude > corridor.getUpperLimit())
				airplanesOut.add(airplane.getFlightID());
		}
		
		int transgressorsNumber = airplanesOut.size();
		String[] arrayOfAirplanesOut = new String[transgressorsNumber];
		for (int i = 0; i < transgressorsNumber; i++)
			arrayOfAirplanesOut[i] = airplanesOut.get(i);
		
		return arrayOfAirplanesOut;
	}
}
