package airtrafficcontrol.app.utils;


import airtrafficcontrol.app.exceptions.InvalidArgumentException;


/**
 * Class whose instances represent geographical positions defined by a value of latitude, a value of longitude and
 * a value of altitude.
 * 
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class GeographicalPosition
{
	
	//CAMPOS 
	
	/**
	 * This geographical position's latitude.
	 */
	private Latitude latitude;
	
	/**
	 * This geographical position's longitude.
	 */
	private Longitude longitude;
	
	/**
	 * This geographical position's altitude.
	 */
	private Altitude altitude;
	
	
	
	//CONSTRUTOR
	
	
	/**
	 * Constructs an instance of {@link GeographicalPosition} with latitude {@code lat}, longitude {@code lon} and altitude {@code alt}.
	 * 
	 * @param lat
	 *            latitude
	 * @param lon
	 *            longitude
	 * @param alt
	 *            altitude
	 */
	public GeographicalPosition( Latitude lat, Longitude lon, Altitude alt ) {
		latitude = lat;
		longitude = lon;
		altitude = alt;
	}
	
	
	
	/**
	 * Constructs a GeographicalPosition with latitude, longitude and altitude
	 * defined by lan, lon and alt
	 * 
	 * @param lat
	 *            latitude value
	 * @param lon
	 *            longitude value
	 * @param alt
	 *            altitude value
	 */
	public GeographicalPosition( double lat, double lon, double alt )
			throws InvalidArgumentException {
		latitude = new Latitude( lat );
		longitude = new Longitude( lon );
		altitude = new Altitude( alt );
	}
	
	
	
	/**
	 * Gets the longitude value
	 * 
	 * @return longitude value
	 */
	public double getLongitude() {
		return longitude.getLongitudeValue();
	}
	
	/**
	 * Gets the latitude value
	 * 
	 * @return value of latitude
	 */
	public double getLatitude() {
		return latitude.getLatitudeValue();
	}
	
	/**
	 * Gets the altitude value
	 * 
	 * @return value of altitude
	 */
	public double getAltitude() {
		return altitude.getAltitudeValue();
	}
	
	/**
	 * Increments the value of the current {@link GeographicalPosition} with
	 * {@code lat}, {@code lon} and {@code alt}.
	 * 
	 * @param lat
	 *            The value to increment to the current latitude.
	 * @param lon
	 *            The value to increment to the current longitude.
	 * @param alt
	 *            The value to increment to the current altitude.
	 * @throws InvalidArgumentException
	 *             If the values with which to increment the current ones cause
	 *             the sum to exceed the maximum values of latitude and
	 *             longitude or to go below the minimum values possible for
	 *             latitude, longitude and altitude.
	 */
	public void incrementPosition( double lat, double lon, double alt )
			throws InvalidArgumentException {
		
		latitude.incrementLatitude( lat );
		longitude.incrementLongitude( lon );
		altitude.incrementAltitude( alt );
	}
	
	/**
	 * Increments the value of the current {@link GeographicalPosition} with the
	 * values represented by the instances {@code lat}, {@code lon} and
	 * {@code alt}.
	 * 
	 * @param lat
	 *            The {@link Latitude} to increment to the current latitude.
	 * @param lon
	 *            The {@link Longitude} to increment to the current longitude.
	 * @param alt
	 *            The {@link Altitude} to increment to the current altitude.
	 * @throws InvalidArgumentException
	 *             If the values with which to increment the current ones cause
	 *             the sum to exceed the maximum values of latitude and
	 *             longitude or to go below the minimum values possible for
	 *             latitude, longitude and altitude.
	 */
	public void incrementPosition( Latitude lat, Longitude lon, Altitude alt )
			throws InvalidArgumentException {
		latitude.incrementLatitude( lat );
		longitude.incrementLongitude( lon );
		altitude.incrementAltitude( alt );
	}
	
	/**
	 * Sets a new GeographicalPosition with lat, lon and alt
	 * 
	 * @param lat
	 *            new latitude value
	 * @param lon
	 *            new longitude value
	 * @param alt
	 *            new altitude value
	 */
	public void setPosition( double lat, double lon, double alt )
			throws InvalidArgumentException {
		latitude.setLatitude( lat );
		longitude.setLongitude( lon );
		altitude.setAltitude( alt );
	}
	
	/**
	 * Sets a new GeographicalPosition with lat, lon and alt
	 * 
	 * @param lat
	 *            object with the new latitude value
	 * @param lon
	 *            object with the new longitude value
	 * @param alt
	 *            object with the new altitude value
	 */
	public void setPosition( Latitude lat, Longitude lon, Altitude alt )
			throws InvalidArgumentException {
		latitude.setLatitude( lat );
		longitude.setLongitude( lon );
		altitude.setAltitude( alt );
	}
	
	/**
	 * Increments the value of the current altitude
	 * 
	 * @param alt
	 *            value of altitude incrementation
	 */
	public void incrementAltitude( double alt ) {
		altitude.incrementAltitude( alt );
	}
	
	/**
	 * Sets a new value for altitude
	 * 
	 * @param alt
	 *            new value of altitude
	 */
	public void setAltitude( double alt ) throws InvalidArgumentException {
		altitude.setAltitude( alt );
	}
	
	/**
	 * Increments the value of the current altitude
	 * 
	 * @param alt
	 *            object that increments the value of altitude
	 */
	public void incrementAltitude( Altitude alt ) {
		altitude.incrementAltitude( alt );
	}
	
	/**
	 * Sets a new value for altitude
	 * 
	 * @param alt
	 *            object that contains the new altitude value
	 */
	public void setAltitude( Altitude alt ) throws InvalidArgumentException {
		altitude.setAltitude( alt );
	}
	
	/**
	 * Increments the value of the latitude of the current
	 * {@link GeographicalPosition} by {@code lat}.
	 * 
	 * @param lat
	 *            The value to increment to the current latitude.
	 * @throws InvalidArgumentException
	 *             If the value with which to increment the current ones cause
	 *             the sum to exceed the {@link Latitude#MAX_LATITUDE maximum
	 *             latitude} possible or to go below the
	 *             {@link Latitude#MIN_LATITUDE minimum latitude} possible.
	 */
	public void incrementLatitude( double lat ) throws InvalidArgumentException {
		latitude.incrementLatitude( lat );
	}
	
	/**
	 * Increments the value of the latitude of the current
	 * {@link GeographicalPosition} with the value represented by {@code lat}.
	 * 
	 * @param lat
	 *            The {@link Latitude} to increment to the current latitude.
	 * @throws InvalidArgumentException
	 *             If the value with which to increment the current ones cause
	 *             the sum to exceed the {@link Latitude#MAX_LATITUDE maximum
	 *             latitude} possible or to go below the
	 *             {@link Latitude#MIN_LATITUDE minimum latitude} possible.
	 */
	public void incrementLatitude( Latitude lat )
			throws InvalidArgumentException {
		latitude.incrementLatitude( lat );
	}
	
	/**
	 * Sets a new value for the latitude of this {@link GeographicalPosition}.
	 * 
	 * @param lat
	 *            The new value of latitude.
	 * @throws InvalidArgumentException
	 *             If {@code lat<}{@link {@link Latitude#MIN_LATITUDE
	 *             MIN_LATITUDE} or if {@code lat>}{@link
	 *             {@link Latitude#MAX_LATITUDE MAX_LATITUDE}.
	 */
	public void setLatitude( double lat ) throws InvalidArgumentException {
		latitude.setLatitude( lat );
	}
	
	/**
	 * Sets a new value for latitude
	 * 
	 * @param lat
	 *            object that contains the new latitude value
	 * @throws InvalidArgumentException
	 */
	public void setLatitude( Latitude lat ) throws InvalidArgumentException {
		latitude.setLatitude( lat );
	}
	
	/**
	 * Increments the value of the current longitude
	 * 
	 * @param lon
	 *            value of longitude incrementation
	 */
	public void incrementLongitude( double lon ) {
		longitude.incrementLongitude( lon );
	}
	
	/**
	 * Increments the value of the current longitude
	 * 
	 * @param lon
	 *            object that increments the value of longitude
	 */
	public void incrementLongitude( Longitude lon ) {
		longitude.incrementLongitude( lon );
	}
	
	/**
	 * Sets a new value for longitude
	 * 
	 * @param lon
	 *            new value of longitude
	 */
	public void setLongitude( double lon ) throws InvalidArgumentException {
		longitude.setLongitude( lon );
	}
	
	/**
	 * Sets a new value for longitude
	 * 
	 * @param lon
	 *            object that contains the new longitude value
	 */
	public void setLongitude( Longitude lon ) {
		longitude.setLongitude( lon );
	}
	
}