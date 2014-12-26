package main.java.cli.exceptions.factoryexceptions;



/**
 * Superclass for all {@link CommandFactory} exceptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class CommandFactoryException extends Exception
{
	
	/**
	 * Constructs a {@link CommandFactoryException} with no detail message.
	 */
	public CommandFactoryException() {
		
	}
	
	/**
	 * Constructs a {@link CommandFactoryException} with the specified detail
	 * message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public CommandFactoryException( String message ) {
		
		super( message );
	}
	
}