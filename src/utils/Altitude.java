package utils;

import app.InvalidArgumentException;

/**
 * This class represents an Altitude value.
 * @author Hugo
 *
 */
public class Altitude {
	
	private double altitude;
	
	/**
	 * Constructs a value of altitude
	 * @param alt altitude value
	 */
	public Altitude(double alt)throws InvalidArgumentException
	{
		altitude = alt;
		if(alt < 0 )
			throw new InvalidArgumentException();
	}
	
	/**
	 * Gets the altitude value
	 * @return value of the altitude
	 */
	public double getAltitudeValue()
	{
		return altitude;
	}
	/**
	 * Increments the value of the current altitude with alt 
	 * @param alt value of the altitude increment 
	 */
	public void incrementAltitude(double alt)
	{
		this.altitude += alt;
	}
	
	/**
	 * Increments the value of the current altitude with alt
	 * @param alt object that increments altitude value
	 */
	public void incrementAltitude(Altitude alt)
	{
		this.altitude += alt.getAltitudeValue();
	}
	
	/**
	 * Sets a new altitude value
	 * @param alt new altitude value
	 */
	public void setAltitude(double alt) throws InvalidArgumentException
	{
		this.altitude = alt;
		if(alt < 0 )
			throw new InvalidArgumentException();
	}
	
	/**
	 * Sets a new altitude value
	 * @param alt object that contains the new altitude value
	 */
	public void setAltitude(Altitude alt)throws InvalidArgumentException
	{
		this.altitude = alt.getAltitudeValue();
	}
}
