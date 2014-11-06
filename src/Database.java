import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class Database {

	private Map<String, Airship> database;
	
	private Database()
	{
		//TODO
		database = new HashMap<>();
	}
	
	public boolean addAirplane(String id, Airship airplane)
	{
		//TODO
		return false;
	}
	
	public boolean removeAirplane(String id)
	{
		//TODO
		return false;
	}
	
	public boolean removeAirplane(Airship airplane)
	{
		//TODO
		return false;
	}
	
	public int removeAirplanesWithZeroPassengers()
	{
		//TODO
		return 0;
	}
	
	public String[] reportAirplanesOutOfCorridor()
	{
		//TODO
		return null;
	}
}
