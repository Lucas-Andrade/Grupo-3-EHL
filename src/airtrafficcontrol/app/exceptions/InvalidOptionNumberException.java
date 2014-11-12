package airtrafficcontrol.app.exceptions;

import airtrafficcontrol.app.OptionsMenu;


/**
 * Exception thrown when it was received an invalid number of option (that is
 * not between 1 and the number of the last option in an {@link OptionsMenu}) in
 * an operation which involves only the numbers of the options available in a
 * certain {@link OptionsMenu}.
 */
public class InvalidOptionNumberException extends Exception
{
	
	/**
	 * Constructs a new {@code InvalidOptionNumberException} with {@code null}
	 * as its detail message.
	 */
	public InvalidOptionNumberException() {
		super();
	}
	
	/**
	 * Constructs a new {@code InvalidOptionNumberException} with the specified
	 * detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public InvalidOptionNumberException( String message ) {
		super( message );
	}
}
