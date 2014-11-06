
public class Longitude {
	
	private double longitude;
	
	public Longitude(double lon)
	{
		longitude = lon;
	}
	
	public double getLongitudeValue()
	{
		return longitude;
	}
	
	public void incrementLongitude(double lon)
	{
		this.longitude += lon;
	}
	
	public void incrementLongitude(Longitude lon)
	{
		this.longitude += lon.getLongitudeValue();
	}
	
	public void setLongitude(double lon)
	{
		this.longitude = lon;
	}
	
	public void setLongitude(Longitude lon)
	{
		this.longitude = lon.getLongitudeValue();
	}
}
