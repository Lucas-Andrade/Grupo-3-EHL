package utils;
/**
 * This class represents GeographicalPosition defined by Latitude, Longitude and Altitude
 * @author Hugo
 *
 */
public class GeographicalPosition {
	
	private Latitude latitude;
	private Longitude longitude;
	private Altitude altitude;
	
	/**
	 * Constructs a GeographicalPosition with latitude, longitude and altitude
	 * @param lat latitude
	 * @param lon longitude
	 * @param alt altitude
	 */
	public GeographicalPosition(Latitude lat, Longitude lon, Altitude alt)
	{
		latitude = lat;
		longitude = lon;
		altitude = alt;
	}
	
	/**
	 * Constructs a GeographicalPosition with latitude, longitude and altitude
	 * defined by lan, lon and alt
	 * @param lat latitude value
	 * @param lon longitude value
	 * @param alt altitude value
	 */
	public GeographicalPosition(double lat, double lon, double alt)
	{
		latitude = new Latitude(lat);
		longitude = new Longitude(lon);
		altitude = new Altitude(alt);
	}
	
	/**
	 * Gets the longitude value
	 * @return longitude value
	 */
	public double getLongitude()
	{
		return longitude.getLongitudeValue();
	}
	
	/**
	 * Gets the latitude value
	 * @return value of latitude
	 */
	public double getLatitude()
	{
		return latitude.getLatitudeValue();
	}
	
	/**
	 * Gets the altitude value
	 * @return value of altitude
	 */
	public double getAltitude()
	{
		return altitude.getAltitudeValue();
	}
	
	/**
	 * Increments the value of the current GeographicalPosition with lat, lon and alt
	 * @param lat latitude incrementation value
	 * @param lon longitude incrementation value
	 * @param alt altitude incrementation value
	 */
	public void incrementPosition(double lat, double lon, double alt)
	{
		latitude.incrementLatitude(lat);
		longitude.incrementLongitude(lon);
		altitude.incrementAltitude(alt);
	}
	
	/**
	 * Increments the value of the current GeographicalPosition with lat, lon and alt
	 * @param lat object that increments latitude value
	 * @param lon object that increments longitude value
	 * @param alt object that increments altitude value
	 */
	public void incrementPosition(Latitude lat, Longitude lon, Altitude alt)
	{
		latitude.incrementLatitude(lat);
		longitude.incrementLongitude(lon);
		altitude.incrementAltitude(alt);
	}
	
	/**
	 * Sets a new GeographicalPosition with lat, lon and alt
	 * @param lat new latitude value
	 * @param lon new longitude value
	 * @param alt new altitude value
	 */
	public void setPosition(double lat, double lon, double alt)
	{
		latitude.setLatitude(lat);
		longitude.setLongitude(lon);
		altitude.setAltitude(alt);
	}
	
	/**
	 * Sets a new GeographicalPosition with lat, lon and alt
	 * @param lat object with the new latitude value
	 * @param lon object with the new longitude value
	 * @param alt object with the new altitude value
	 */
	public void setPosition(Latitude lat, Longitude lon, Altitude alt)
	{
		latitude.setLatitude(lat);
		longitude.setLongitude(lon);
		altitude.setAltitude(alt);
	}
	
	/**
	 * Increments the value of the current altitude
	 * @param alt value of altitude incrementation
	 */
	public void incrementAltitude(double alt)
	{
		altitude.incrementAltitude(alt);
	}
	
	/**
	 * Sets a new value for altitude
	 * @param alt new value of altitude
	 */
	public void setAltitude(double alt)
	{
		altitude.setAltitude(alt);
	}
	
	/**
	 * Increments the value of the current altitude
	 * @param alt object that increments the value of altitude
	 */
	public void incrementAltitude(Altitude alt)
	{
		altitude.incrementAltitude(alt);
	}
	
	/**
	 * Sets a new value for altitude
	 * @param alt object that contains the new altitude value
	 */
	public void setAltitude(Altitude alt)
	{
		altitude.setAltitude(alt);
	}
	
	/**
	 * Increments the value of the current latitude
	 * @param lat value of altitude incrementation
	 */
	public void incrementLatitude(double lat)
	{
		latitude.incrementLatitude(lat);
	}
	
	/**
	 * Increments the value of the current latitude
	 * @param lat object that increments the value of latitude
	 */
	public void incrementLatitude(Latitude lat)
	{
		latitude.incrementLatitude(lat);
	}
	
	/**
	 * Sets a new value for latitude
	 * @param lat new value of latitude
	 */
	public void setLatitude(double lat)
	{
		latitude.setLatitude(lat);
	}
	
	/**
	 * Sets a new value for latitude
	 * @param lat object that contains the new latitude value
	 */
	public void setLatitude(Latitude lat)
	{
		latitude.setLatitude(lat);
	}
	
	/**
	 * Increments the value of the current longitude
	 * @param lon value of longitude incrementation
	 */
	public void incrementLongitude(double lon)
	{
		longitude.incrementLongitude(lon);
	}
	
	/**
	 * Increments the value of the current longitude
	 * @param lon object that increments the value of longitude
	 */
	public void incrementLongitude(Longitude lon)
	{
		longitude.incrementLongitude(lon);
	}
	
	/**
	 * Sets a new value for longitude
	 * @param lon new value of longitude
	 */
	public void setLongitude(double lon)
	{
		longitude.setLongitude(lon);
	}
	
	/**
	 * Sets a new value for longitude
	 * @param lon object that contains the new longitude value
	 */
	public void setLongitude(Longitude lon)
	{
		longitude.setLongitude(lon);
	}
	
}