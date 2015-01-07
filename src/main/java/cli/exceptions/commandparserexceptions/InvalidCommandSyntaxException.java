package main.java.cli.exceptions.commandparserexceptions;


/**
 * Thrown when a parsing error occurs, result from trying to parse a
 * string-command with illegal syntax.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class InvalidCommandSyntaxException extends
		CommandParserException
{
	
	/**
	 * Constructs a {@link InvalidCommandSyntaxException} with the
	 * specified detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public InvalidCommandSyntaxException( String message ) {
		
		super( message );
	}
}