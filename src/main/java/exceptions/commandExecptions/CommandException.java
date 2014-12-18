package main.java.exceptions.commandExecptions;

import main.java.commands.CommandFactory;

/**
 * Superclass for all {@link CommandFactory} exceptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings ("serial")
public class CommandException extends Exception {

	public CommandException() {

		super();
	}

	public CommandException(String message) {

		super(message);
	}

	public CommandException(String message, Throwable cause) {

		super(message, cause);
	}
}