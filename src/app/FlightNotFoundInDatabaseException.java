package app;


/**
 * Thrown when a method tries to reach a flight in a flights' database that
 * doesn't contain it.
 * 
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class FlightNotFoundInDatabaseException extends Exception
{
	
	/**
	 * Constructs a new {@code InexistentFlightException} with {@code null} as
	 * its detail message.
	 */
	public FlightNotFoundInDatabaseException() {
		super();
	}
	
	/**
	 * Constructs a new {@code InexistentFlightException} with the specified
	 * detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public FlightNotFoundInDatabaseException( String message ) {
		super( message );
	}
}
