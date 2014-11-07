package app;


/**
 * Exception thrown when the number introduced by the user, corresponding to the
 * action he wants to perform from the {@code Option Menu}, is not between 1 and
 * the number of the last option in the {@code Option Menu}.
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
