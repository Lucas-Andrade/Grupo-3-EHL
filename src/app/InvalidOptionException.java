package app;


/**
 * Exception thrown when it was received an instance of Option that is invalid
 * in the context of the operation being executed.
 */
public class InvalidOptionException extends Exception
{
	
	/**
	 * Constructs a new {@code InvalidOptionNumberException} with {@code null}
	 * as its detail message.
	 */
	public InvalidOptionException() {
		super();
	}
	
	/**
	 * Constructs a new {@code InvalidOptionNumberException} with the specified
	 * detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public InvalidOptionException( String message ) {
		super( message );
	}
}
