package utils;

import app.InvalidArgumentException;

/**
 * This class represents a Latitude value.
 * @author Hugo
 *
 */
public class Latitude {
	
	private double latitude;
	static final double MAX_LATITUDE = 90;
	static final double MIN_LATITUDE = -90;
	/**
	 * Constructs a value of latitude
	 * @param lat latitude value
	 */
	public Latitude(double lat) throws InvalidArgumentException
	{
		if(latitude < MAX_LATITUDE && latitude > MIN_LATITUDE )
		latitude = lat;
		else
			throw new InvalidArgumentException();
	}
	
	/**
	 * Gets the latitude value
	 * @return value of the latitude
	 */
	public double getLatitudeValue()
	{
		return latitude;
	}
	
	/**
	 * Increments the value of the current latitude with lat
	 * @param lat value of the latitude increment 
	 */
	public void incrementLatitude(double lat)
	{
		this.latitude += lat;
	}
	
	/**
	 * Increments the value of the current latitude with lat
	 * @param lat object that increments latitude value
	 */
	public void incrementLatitude(Latitude lat)
	{
		this.latitude += lat.getLatitudeValue();
	}
	
	/**
	 * Sets a new latitude value
	 * @param lat new latitude value
	 */
	public void setLatitude(double lat)throws InvalidArgumentException
	{
		if(latitude < MAX_LATITUDE && latitude > MIN_LATITUDE )
			this.latitude = lat;
			else
				throw new InvalidArgumentException();
		
	}
	
	/**
	 * Sets a new latitude value
	 * @param lat object that contains the new latitude value
	 */
	public void setLatitude(Latitude lat)
	{
		this.latitude = lat.getLatitudeValue();
	}
}
