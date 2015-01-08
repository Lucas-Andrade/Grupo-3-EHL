package main.java.utils.exceptions.parsingexceptions.factoriesexceptions;

import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.utils.exceptions.parsingexceptions.ParsingException;

/**
 * Superclass for all {@link StringsToCommandsFactory} exceptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings ("serial")
public class FactoryException extends ParsingException {

	/**
	 * Constructs a {@link FactoryException} with no detail message.
	 */
	public FactoryException() {

	}

	/**
	 * Constructs a {@link FactoryException} with the specified detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public FactoryException(String message) {

		super(message);
	}

}