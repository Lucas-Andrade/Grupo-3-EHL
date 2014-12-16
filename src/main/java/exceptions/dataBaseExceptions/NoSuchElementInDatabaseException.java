package main.java.exceptions.dataBaseExceptions;

@SuppressWarnings ("serial")
public class NoSuchElementInDatabaseException extends DatabaseException {

	public NoSuchElementInDatabaseException(String message) {

		super(message);
	}
}