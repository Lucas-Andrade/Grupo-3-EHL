package airtrafficcontrol.app.utils.aircraftcoordinates;

import airtrafficcontrol.app.exceptions.InvalidArgumentException;




/**
 * Class whose instances represent geographical positions defined by a value of latitude, a value of longitude and
 * a value of altitude.
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */
public class GeographicalPosition
{
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
	 * @param latitude
	 *            latitude
	 * @param lon
	 *            longitude
	 * @param alt
	 *            altitude
	 */
	public GeographicalPosition( Latitude latitude, Longitude longitude, Altitude altitude )
	{
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
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
	 * @return longitude value
	 */
	public double getLongitude()
	{
		return longitude.getGeographicalCoordinate();
	}
	
	
	/**
	 * @return value of latitude
	 */
	public double getLatitude()
	{
		return latitude.getGeographicalCoordinate();
	}
	
	
	/**
	 * @return value of altitude
	 */
	public double getAltitude()
	{
		return altitude.getGeographicalCoordinate();
	}
	
}