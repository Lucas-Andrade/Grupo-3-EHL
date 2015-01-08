package main.java.utils.exceptions;



/**
 * Thrown when a method receives an invalid value for a certain argument.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class InvalidArgumentException extends Exception
{
	
	/**
	 * Constructs an {@link InvalidArgumentException} with no detail message.
	 */
	public InvalidArgumentException() {
		
	}
	
	/**
	 * Constructs an {@link InvalidArgumentException} with the specified detail
	 * message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public InvalidArgumentException( String message ) {
		
		super( message );
	}
}
