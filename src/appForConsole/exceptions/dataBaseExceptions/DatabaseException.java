package appForConsole.exceptions.dataBaseExceptions;

import appForConsole.model.Database;

/**
 * Superclass for all {@link Database} exceptions.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings ("serial")
public class DatabaseException extends Exception {

	public DatabaseException() {

		super();
	}

	public DatabaseException(String message) {

		super(message);
	}

	public DatabaseException(String message, Throwable cause) {

		super(message, cause);
	}
}