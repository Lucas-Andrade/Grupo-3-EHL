package airtrafficcontrol.app.utils;


import airtrafficcontrol.app.exceptions.InvalidArgumentException;


/**
 * Class whose instances represent a latitude value.
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class Latitude
{
	
	// CAMPOS
	
	/**
	 * The latitude value.
	 */
	private double latitude;
	
	/**
	 * Latitude's maximum value.
	 */
	public static final double MAX_LATITUDE = 90;
	
	/**
	 * Latitude's minimum value.
	 */
	public static final double MIN_LATITUDE = -90;
	
	
	// CONSTRUTOR
	
	/**
	 * Constructs an instance of {@link Latitude} with value {@code lat}.
	 * 
	 * @param lat
	 *            The latitude value.
	 * @throws InvalidArgumentException
	 *             If {@code lat > MAX_LATITUDE} or {@code lat < MIN_LATITUDE}.
	 */
	public Latitude( double lat ) throws InvalidArgumentException {
		
		if( lat <= MAX_LATITUDE && lat >= MIN_LATITUDE )
			latitude = lat;
		
		else throw new InvalidArgumentException( "INVALID LATITUDE VALUE!" );
	}
	
	
	
	// METODOS
	
	/**
	 * Returns the latitude's value.
	 * 
	 * @return The latitude's value.
	 */
	public double getLatitudeValue() {
		return latitude;
	}
	
	/**
	 * Increments the value of the current latitude with the value {@code lat}.
	 * 
	 * @param lat
	 *            The value of the latitude with which to increment the current
	 *            latitude.
	 * @throws InvalidArgumentException
	 *             If {@code latitude+lat>MAX_LATITUDE} or
	 *             {@code latitude+lat<MIN_LATITUDE}.
	 */
	public void incrementLatitude( double lat ) throws InvalidArgumentException {
		
		if( latitude + lat > MAX_LATITUDE || latitude + lat < MIN_LATITUDE )
			throw new InvalidArgumentException(
					"INVALID INCREMENT VALUE FOR THE CURRENT LATITUDE!" );
		
		this.latitude += lat;
	}
	
	/**
	 * Increments the value of the current latitude with the value represented
	 * by {@code lat}.
	 * 
	 * @param lat
	 *            The instance of Latitude with which to increment the current
	 *            latitude.
	 * @throws InvalidArgumentException
	 *             If {@code lat} is {@code null} or if
	 *             {@code latitude+lat>MAX_LATITUDE} or
	 *             {@code latitude+lat<MIN_LATITUDE}.
	 */
	public void incrementLatitude( Latitude lat )
			throws InvalidArgumentException {
		
		if( lat == null )
			throw new InvalidArgumentException(
					"INVALID INCREMENT VALUE FOR THE CURRENT LATITUDE!" );
		if( latitude + lat.getLatitudeValue() > MAX_LATITUDE
				|| latitude + lat.getLatitudeValue() < MIN_LATITUDE )
			throw new InvalidArgumentException(
					"INVALID INCREMENT VALUE FOR THE CURRENT LATITUDE!" );
		
		this.latitude += lat.getLatitudeValue();
	}
	
	/**
	 * Sets a new latitude value.
	 * 
	 * @param lat
	 *            The new latitude value.
	 * @throws InvalidArgumentException
	 *             If {@code lat > MAX_LATITUDE} or {@code lat < MIN_LATITUDE}.
	 */
	public void setLatitude( double lat ) throws InvalidArgumentException {
		
		if( lat <= MAX_LATITUDE && lat >= MIN_LATITUDE )
			this.latitude = lat;
		
		else throw new InvalidArgumentException( "INVALID LATITUDE VALUE!" );
		
	}
	
	/**
	 * Sets a new latitude value.
	 * 
	 * @param lat
	 *            The new latitude value.
	 * @throws InvalidArgumentException
	 *             If {@code lat} is {@code null}.
	 */
	public void setLatitude( Latitude lat ) throws InvalidArgumentException {
		
		if( lat == null )
			throw new InvalidArgumentException( "INVALID LATITUDE VALUE!" );
		
		this.latitude = lat.getLatitudeValue();
	}
}
