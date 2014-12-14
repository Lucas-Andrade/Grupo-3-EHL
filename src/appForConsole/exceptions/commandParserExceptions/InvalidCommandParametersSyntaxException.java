package appForConsole.exceptions.commandParserExceptions;

/**
 * Thrown when a parsing error occurs, result from trying to parse a string-command with illegal
 * syntax.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings ("serial")
public class InvalidCommandParametersSyntaxException extends CommandParserException {

	/**
	 * Constructs a {@link InvalidCommandParametersSyntaxException} with the specified detail
	 * message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public InvalidCommandParametersSyntaxException(String message) {

		super(message);
	}
}