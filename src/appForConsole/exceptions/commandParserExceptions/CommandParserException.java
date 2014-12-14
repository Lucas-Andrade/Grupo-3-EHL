package appForConsole.exceptions.commandParserExceptions;

import appForConsole.CommandParser;

/**
 * Superclass for all {@link CommandParser} exceptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings ("serial")
public class CommandParserException extends Exception {

	/**
	 * Constructs a {@link CommandParserException} with no detail message.
	 */
	public CommandParserException() {

	}

	/**
	 * Constructs a {@link CommandParserException} with the specified detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public CommandParserException(String message) {

		super(message);
	}
}