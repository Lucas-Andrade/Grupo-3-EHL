import java.util.HashMap;
import java.util.Map;


public class Database {

	private Map<String, Airplane> database;
	
	private Database()
	{
		database = new HashMap<>();
	}
	
	public boolean addAirplane(String id, Airplane airplane)
	{
		return false;
	}
	
	public boolean removeAirplane(String id)
	{
		return false;
	}
	
	public boolean removeAirplane(Airplane airplane)
	{
		return false;
	}
	
	public boolean removeAirplanesWithZeroPassengers()
	{
		return false;
	}
}
