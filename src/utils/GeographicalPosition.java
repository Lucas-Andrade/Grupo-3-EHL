package utils;

public class GeographicalPosition {
	
	private Latitude latitude;
	private Longitude longitude;
	private Altitude altitude;
	
	public GeographicalPosition(Latitude lat, Longitude lon, Altitude alt)
	{
		latitude = lat;
		longitude = lon;
		altitude = alt;
	}
	
	public GeographicalPosition(double lat, double lon, double alt)
	{
		Latitude latitude = new Latitude(lat);
		Longitude lontitude = new Longitude(lon);
		Altitude altitude = new Altitude(alt);
	}
	
	public double getLongitude()
	{
		return longitude.getLongitudeValue();
	}
	
	public double getLatitude()
	{
		return latitude.getLatitudeValue();
	}
	
	public double getAltitude()
	{
		return altitude.getAltitudeValue();
	}
	
	public void incrementPosition(double lat, double lon, double alt)
	{
		latitude.incrementLatitude(lat);
		longitude.incrementLongitude(lon);
		altitude.incrementAltitude(alt);
	}
	
	public void incrementPosition(Latitude lat, Longitude lon, Altitude alt)
	{
		latitude.incrementLatitude(lat);
		longitude.incrementLongitude(lon);
		altitude.incrementAltitude(alt);
	}
	
	public void setPosition(double lat, double lon, double alt)
	{
		latitude.setLatitude(lat);
		longitude.setLongitude(lon);
		altitude.setAltitude(alt);
	}
	
	public void setPosition(Latitude lat, Longitude lon, Altitude alt)
	{
		latitude.setLatitude(lat);
		longitude.setLongitude(lon);
		altitude.setAltitude(alt);
	}
	
	public void incrementAltitude(double alt)
	{
		altitude.incrementAltitude(alt);
	}
	
	public void setAltitude(double alt)
	{
		altitude.setAltitude(alt);
	}
	
	public void incrementAltitude(Altitude alt)
	{
		altitude.incrementAltitude(alt);
	}
	
	public void setAltitude(Altitude alt)
	{
		altitude.incrementAltitude(alt);
	}
	
	public void incrementLatitude(double lat)
	{
		latitude.incrementLatitude(lat);
	}
	
	public void incrementLatitude(Latitude lat)
	{
		latitude.incrementLatitude(lat);
	}
	
	public void setLatitude(double lat)
	{
		latitude.setLatitude(lat);
	}
	
	public void setLatitude(Latitude lat)
	{
		latitude.setLatitude(lat);
	}
	
	public void incrementLongitude(double lon)
	{
		longitude.incrementLongitude(lon);
	}
	
	public void incrementLongitude(Longitude lon)
	{
		longitude.incrementLongitude(lon);
	}
	
	public void setLongitude(double lon)
	{
		longitude.setLongitude(lon);
	}
	
	public void setLongitude(Longitude lon)
	{
		longitude.setLongitude(lon);
	}
}
