package airtrafficcontrol.app.appforconsole.exceptions;


/**
 * Exception thrown when it was received an invalid flightID. A valid flightID
 * is a sequence of alphanumeric characters that can not be an empty string
 * (""), contain paragraph characters (\n) nor contain characters that are not
 * numbers nor letters (!"#$%.:,;- , the space character, etc).
 *
 * @author Eva Gomes
 * @author Hugo Leal
 * @author Lucas Andrade
 */
@SuppressWarnings( "serial" )
public class InvalidFlightIDException extends Exception
{
	
	/**
	 * Constructs a new {@code InvalidFlightIDException} with {@code null} as
	 * its detail message.
	 */
	public InvalidFlightIDException() {
		super();
	}
	
	/**
	 * Constructs a new {@code InvalidFlightIDException} with the specified
	 * detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public InvalidFlightIDException( String message ) {
		super( message );
	}
	
}
