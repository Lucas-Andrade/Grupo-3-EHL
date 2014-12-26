package main.java.cli.exceptions.databaseexceptions;


import java.text.MessageFormat;


/**
 * Thrown when trying to reach an element that is not in a database.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
@SuppressWarnings( "serial" )
public class NoSuchElementInDatabaseException extends DatabaseException
{
	
	/**
	 * Constructs a {@link DatabaseException} with the specified detail message.
	 * 
	 * @param message
	 *            The detail message.
	 */
	public NoSuchElementInDatabaseException( String message ) {
		
		super( message );
	}
	
	/**
	 * Constructs a {@link DatabaseException} with the message <i>«
	 * {@code elementIdentification} not found in {@code databaseName}.»</i>.
	 * 
	 * @param elementIdentification
	 *            The element which wasn't found.
	 * @param databaseName
	 *            The database where it was expected the element to be in.
	 */
	public NoSuchElementInDatabaseException( String elementIdentification, String databaseName ) {
		
		super( MessageFormat
				.format( "{0} not found in {1}.", elementIdentification, databaseName ) );
	}
}
