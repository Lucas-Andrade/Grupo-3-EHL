import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;


public class Database {

	private Map<String, Airplane> database;
	
	private Database()
	{
		//TODO
		database = new HashMap<>();
	}
	
	public boolean addAirplane(String id, Airplane airplane)
	{
		//TODO
		return false;
	}
	
	public boolean removeAirplane(String id)
	{
		//TODO
		return false;
	}
	
	public boolean removeAirplane(Airplane airplane)
	{
		//TODO
		return false;
	}
	
	public boolean removeAirplanesWithZeroPassengers()
	{
		//TODO
		return false;
	}
	
	public String[] reportAirplanesOutOfCorridor()
	{
		//TODO
		return null;
	}
}
