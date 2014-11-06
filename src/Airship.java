import java.util.Date;
import java.util.LinkedList;


public abstract class Airship {
	
	private String flightID;
	private LinkedList<GeographicalPosition> lastknownGeograficalPositions;
	private int currentPassengers;
	private FlightPlan flightPlan;
	private int numberOfMinutesToTakeOff;
	private int numberOfMinutesToLand;
	private int numberOfMinutesToSwitchCorridor;
	
	public Airship(String flightID, int currentPassengers, GeographicalPosition statingPosition, FlightPlan flightPlan, int timeToTakeOff, int timeToLand, int timeToSwitchCorridor) {
		// TODO Auto-generated constructor stub
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
	
	public AltitudeCorridor getCurrentCorridor(Date current)
	{
		//TODO
		return flightPlan.getCurrentCorridor(current);
	}
	
	public void setNewArrivalHour(Date newArrivalHour)
	{
		//TODO
	}
	
	public String getObservations()
	{
		//TODO
		return null;
	}
}
