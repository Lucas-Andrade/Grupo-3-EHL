package main.java.cli.exceptions.commandparserexceptions;


/**
 * Thrown when a parsing error occurs, result from trying to reach the factory
 * of an unregistered string-command.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class UnknownCommandException extends CommandParserException
{
	/**
	 * Constructs a {@link InvalidCommandParametersSyntaxException} with the
	 * specified detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public UnknownCommandException( String message ) {
		
		super( message );
	}
}
