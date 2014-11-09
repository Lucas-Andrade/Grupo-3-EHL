package utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Allows to create a database of airships
 * 
 *
 *@author Eva Gomes
 *@author Hugo Leal
 *@author Lucas Andrade
 */
public class Database {

	private Map<String, Airship> database;
	
	/**
	 * constructs a new empty database
	 */
	public Database()
	{
		database = new HashMap<>();
	}

	/**
	 * tries to add an airplane to the database. The airplane will not be added if
	 * there is already an airplane with the same flight ID
	 * @param airplane - airplane to add
	 * @return true if the airplane was successfully added
	 * @return false if the airplane was not added
	 */
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
	
	/**
	 * removes the airplane with the specified ID from the database
	 * @param id - the flight ID of the plane to remove
	 * @return true if the airplane was successfully removed
	 * @return false if the specified ID was not found in the database
	 */
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
	
	/**
	 * removes the specified airplane from the database
	 * @param airplane - airplane to remove
	 * @return true if the airplane was successfully removed
	 * @return false if the airplane was not found in the database
	 */
	public boolean removeAirplane(Airship airplane)
	{
		String id = airplane.getFlightID();
		return removeAirplane(id);
	}
	
	/**
	 * removes from the database the airplanes of the class airliner (or any of its subclasses) that happens
	 * to have 0 passengers 
	 * @return the number of airplanes that were removed from the database
	 */
	public int removeAirplanesWithZeroPassengers()
	{
		Set<String> idSet = database.keySet();
		Iterator<String> iterator = idSet.iterator();
		int count = 0;
		ArrayList<String> toRemove = new ArrayList<>();
		
		while (iterator.hasNext())
		{
			Airship airplane = database.get(iterator.next());
			if((airplane instanceof utils.Airliner) && ((Airliner) airplane).getPassengersNumber() == 0)
			{
				toRemove.add(airplane.getFlightID());
				count++;
			}
		}
		
		for (int i = 0; i < toRemove.size(); i++)
			removeAirplane(toRemove.get(i));
		
		return count;
	}
	
	/**
	 * @return the whole database
	 */
	public Map<String, Airship> getDatabase()
	{
		return database;
	}
	
	/**
	 * tries to get an airplane with the identification id
	 * @param id - the flight ID to look for
	 * @return the airplane with the ID passed as parameter
	 * @return null if the airplane was not found
	 */
	public Airship getAirplane(String id)
	{
		if (database.containsKey(id))
			return database.get(id);
		else
			return null;
	}
	
	/**
	 * Verifies if the database contains the airplane id
	 * @param id the airplane id
	 * @return true if the id is on database else returns false
	 */
	public boolean contains (String id)
	{
		return database.containsKey(id);
	}
	
	/**
	 * Sets the property positionWasUpdated off all the airplanes in the database to false
	 */
	protected void setAllAirplanesToNotUpdated()
	{
		Set<String> idSet = database.keySet();
		Iterator<String> iterator = idSet.iterator();
		while(iterator.hasNext())
		{
			database.get(iterator.next()).setToNotUpdated();
		}
	}
}
