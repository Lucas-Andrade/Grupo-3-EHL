package airtrafficcontrol.app.appforconsole.utils.aircraftcoordinates;


import airtrafficcontrol.app.appforconsole.exceptions.InvalidArgumentException;


/**
 * Class whose instances represent a latitude value.
 * @author (Revisão) Filipa Estiveira, Filipa Gonçalves, Gonçalo Carvalho, José Oliveira
  */
public class Latitude extends AGeographicalCoordinate
{
	private static final double MAX_LATITUDE = 90;
	private static final double MIN_LATITUDE = -90;

	/**
	 * Constructs an instance of {@link Latitude} with value {@code lat}.
	 * 
	 * @param lat
	 *            The latitude value.
	 * @throws InvalidArgumentException
	 *             If {@code lat > MAX_LATITUDE} or {@code lat < MIN_LATITUDE}.
	 */
	public Latitude( double latitude ) throws InvalidArgumentException
	{
		super( latitude, MAX_LATITUDE, MIN_LATITUDE );
	}
	
}
