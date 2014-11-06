package AirTrafficControl;

public class Latitude {
	
	private double latitude;
	
	public Latitude(double lat)
	{
		latitude = lat;
	}
	
	public double getLatitudeValue()
	{
		return latitude;
	}
	
	public void incrementLatitude(double lat)
	{
		this.latitude += lat;
	}
	
	public void incrementLatitude(Latitude lat)
	{
		this.latitude += lat.getLatitudeValue();
	}
	
	public void setLatitude(double lat)
	{
		this.latitude = lat;
	}
	
	public void setLatitude(Latitude lat)
	{
		this.latitude = lat.getLatitudeValue();
	}
}
