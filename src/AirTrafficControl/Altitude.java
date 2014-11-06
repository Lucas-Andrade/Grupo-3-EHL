package AirTrafficControl;

public class Altitude {
	
	private double altitude;
	
	public Altitude(double alt)
	{
		altitude = alt;
	}
	
	public double getAltitudeValue()
	{
		return altitude;
	}
	
	public void incrementAltitude(double alt)
	{
		this.altitude += alt;
	}
	
	public void incrementAltitude(Altitude alt)
	{
		this.altitude += alt.getAltitudeValue();
	}
	
	public void setAltitude(double alt)
	{
		this.altitude = alt;
	}
	
	public void setAltitude(Altitude alt)
	{
		this.altitude = alt.getAltitudeValue();
	}
}
