package airtrafficcontrol.app.utils.newStuff;


import airtrafficcontrol.app.exceptions.InvalidArgumentException;
import airtrafficcontrol.app.utils.Latitude;


/**
 * Class whose instances represent a latitude value.
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class Latitude2 extends GeographicalCoordinate
{
	
	// CAMPOS - herda value, MAX_VALUE, MIN_VALUE
	
	
	// CONSTRUTOR
	
	/**
	 * Constructs an instance of {@link Latitude} with value {@code lat}.
	 * 
	 * @param lat
	 *            The latitude value.
	 * @throws InvalidArgumentException
	 *             If {@code lat > MAX_LATITUDE} or {@code lat < MIN_LATITUDE}.
	 */
	public Latitude2( double lat ) throws InvalidArgumentException {
		super( lat, 90, -90 );
	}
	
	
	
	// METODOS
	
	
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
	public void incrementLatitude( Latitude2 incrementLat )
			throws InvalidArgumentException { // ERA BOM TERMOS GENERICOS!
	
		if( incrementLat == null )
			throw new InvalidArgumentException(
					"INVALID INCREMENT VALUE FOR THE CURRENT LATITUDE!" );
		if( this.getValue() + incrementLat.getValue() > this.MAX_VALUE
				|| this.getValue() + incrementLat.getValue() < this.MIN_VALUE )
			throw new InvalidArgumentException(
					"INVALID INCREMENT VALUE FOR THE CURRENT LATITUDE!" );
		
		this.incrementGeographicalCoordinate( incrementLat.getValue() );
	}
	
	
	/**
	 * Sets a new latitude value.
	 * 
	 * @param lat
	 *            The new latitude value.
	 * @throws InvalidArgumentException
	 *             If {@code lat} is {@code null}.
	 */
	public void setLatitude( Latitude2 newLat ) throws InvalidArgumentException {
		// ERA BOM TERMOS GENERICOS
		
		if( newLat.getValue() <= this.MAX_VALUE
				|| newLat.getValue() >= this.MIN_VALUE )
			this.setGeographicalCoordinate( newLat.getValue() );
		
		else throw new InvalidArgumentException( "INVALID LATITUDE VALUE!" );
	}
}