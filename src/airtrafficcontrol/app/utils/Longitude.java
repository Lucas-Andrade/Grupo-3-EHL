package airtrafficcontrol.app.utils;

import app.InvalidArgumentException;

/**
 * This class represents a Longitude value.
 * @author Hugo
 *
 */

public class Longitude {
	
	private double longitude;
	static final double MAX_LONGITUDE = 180;
	static final double MIN_LONGITUDE = -180;
	
	/**
	 * Constructs a value of longitude
	 * @param lon longitude value
	 */
	public Longitude(double lon) throws InvalidArgumentException
	{
		if(longitude < MAX_LONGITUDE && longitude > MIN_LONGITUDE )
		longitude = lon;
		else
			throw new InvalidArgumentException();
	}
	
	/**
	 * Gets the longitude value
	 * @return value of the longitude
	 */
	public double getLongitudeValue()
	{
		return longitude;
	}
	
	/**
	 * Increments the value of the current longitude with lon
	 * @param lon value of the longitude increment
	 */
	public void incrementLongitude(double lon)
	{
		this.longitude += lon;
	}
	
	/**
	 * Increments the value of the current longitude with lon
	 * @param lon object that increments longitude value
	 */
	public void incrementLongitude(Longitude lon)
	{
		this.longitude += lon.getLongitudeValue();
	}
	
	/**
	 *  Sets a new longitude value
	 * @param lon new longitude value
	 */
	public void setLongitude(double lon) throws InvalidArgumentException
	{
		if(longitude < MAX_LONGITUDE && longitude > MIN_LONGITUDE )
			this.longitude = lon;
			else
				throw new InvalidArgumentException();
	}
	
	/**
	 * Sets a new longitude value
	 * @param lon object that contains the new longitude value
	 */
	public void setLongitude(Longitude lon)
	{
		this.longitude = lon.getLongitudeValue();
	}
}