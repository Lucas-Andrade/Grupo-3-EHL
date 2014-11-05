
public abstract class Airplane {
	
	private String flightID;
	private GeographicalPosition geographicalPosition;
	private AltitudeCorridor AltitudeCorridor;
	private int currentPassengers;
	
	public Airplane(String flightID, AltitudeCorridor AltitudeCorridor, int currentPassengers, Latitude lat, Longitude lon, int alt) {
		// TODO Auto-generated constructor stub
	}
	
	public Airplane(String flightID, AltitudeCorridor AltitudeCorridor, int currentPassengers, GeographicalPosition geographicalPosition) {
		// TODO Auto-generated constructor stub
	}
	
	public AltitudeCorridor getAltitudeCorridor()
	{
		//TODO
		return null;
	}
	
	public String getflightID()
	{
		//TODO
		return null;
	}
	
	public int getCurrentPassengers()
	{
		//TODO
		return 0;
	}
	
	public GeographicalPosition getGeographicPosition()
	{
		//TODO
		return null;
	}
	
	public void updateGeographicPosition(GeographicalPosition newGeographicalPosition)
	{
		//TODO
	}
}
