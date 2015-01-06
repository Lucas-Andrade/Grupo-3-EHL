package main.java.cli.utils.exceptions;


import main.java.cli.utils.Optional;


/**
 * Thrown when an {@link Optional} instance has a {@code null} value and the
 * exception {@code toBeThrownIfNull} given in the constructor was {@code null}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class NullValueInOptionalException extends Exception
{
	
	/**
	 * Constructs an {@link NullValueInOptionalException}.
	 */
	public NullValueInOptionalException() {
		
		super();
	}
	
	/**
	 * Constructs an {@link NullValueInOptionalException} with the specified
	 * detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public NullValueInOptionalException( String message ) {
		
		super( message );
	}
}
