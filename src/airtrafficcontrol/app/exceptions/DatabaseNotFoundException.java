package airtrafficcontrol.app.exceptions;


/**
 * Thrown when a method tries to reach a null or inexistent flights' database.
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
public class DatabaseNotFoundException extends Exception
{
	
	/**
	 * Constructs a new {@code DatabaseNotFoundException} with {@code null} as
	 * its detail message.
	 */
	public DatabaseNotFoundException() {
		super();
	}
	
	/**
	 * Constructs a new {@code DatabaseNotFoundException} with the specified
	 * detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public DatabaseNotFoundException( String message ) {
		super( message );
	}
}
