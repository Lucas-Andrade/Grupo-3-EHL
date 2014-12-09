package airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates;

import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;


/**
 * This class defines a geographical coordinate, as an abstract entity
 *
 * @author Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
 */

public abstract class AGeographicalCoordinate
{
	private double geographicalCoordinate;

	
	/**
	 * This constructor defines the features of the geographical coordinate, and if it isn't well defined
	 * it throws an IO exception.
	 * @param geographicalCoordinate
	 * @param max
	 * @param min
	 * @throws InvalidArgumentException
	 */
	public AGeographicalCoordinate( double geographicalCoordinate, double max, double min) throws InvalidArgumentException
	{
		if( geographicalCoordinate <= max && geographicalCoordinate >= min )
			this.geographicalCoordinate = geographicalCoordinate;
		
		else throw new InvalidArgumentException( "INVALID LATITUDE VALUE!" );
	}
	
	/**
	 * This method verifies if the geographical coordinate is well defined, if not, it throws an
	 * IO exception
	 * @param geographicalCoordinate
	 * @param min
	 * @throws InvalidArgumentException
	 */
	public AGeographicalCoordinate( double geographicalCoordinate, double min ) throws InvalidArgumentException
	{
		if( geographicalCoordinate >= min )
			this.geographicalCoordinate = geographicalCoordinate;
		
		else throw new InvalidArgumentException( "INVALID LATITUDE VALUE!" );
	}
	
	
	
	/**
	 * Returns the latitude's value.
	 * 
	 * @return The latitude's value.
	 */
	public double getGeographicalCoordinate()
	{
		return geographicalCoordinate;
	}
	
	
}



