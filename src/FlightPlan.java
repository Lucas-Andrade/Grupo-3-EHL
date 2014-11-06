import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class FlightPlan {
	
	private Calendar departureHour;
	private Calendar arrivalHour;
	private List<AirCorridorInTime> corridors;
	
	public FlightPlan(Calendar departureHour, Calendar arrivalHour)
	{
		corridors = new ArrayList<>();
		this.departureHour = departureHour;
		this.arrivalHour = arrivalHour;
	}
	
	public AltitudeCorridor getCurrentCorridor() {
		
		Calendar now = new GregorianCalendar();
		
		if(now.compareTo(departureHour) < 0 || now.compareTo(arrivalHour) > 0)
			return null;
		
		for (int i = 0; i < corridors.size(); i++)
		{
			if (now.compareTo(corridors.get(i).getStartingHour()) >= 0)
			{
				return corridors.get(i).getCorridor();
			}
		}
		return null;
	}

	
	public void setNewArrivalHour(Calendar newArrivalHour) {
		// TODO Auto-generated method stub
		
	}





	public List<AirCorridorInTime> getListOfCorridors() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	AirCorridorInTime getFirstEvent()
	{
		//TODO
		return null;
	}
	
	AirCorridorInTime getLastEvent()
	{
		//TODO
		return null;
	}
}
