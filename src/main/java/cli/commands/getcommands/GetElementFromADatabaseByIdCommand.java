package main.java.cli.commands.getcommands;


import java.util.concurrent.Callable;
import main.java.cli.Optional;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.model.Database;
import main.java.cli.model.Element;
import main.java.cli.model.InMemoryDatabase;


/**
 * Class whose instances represent commands to get an element with a certain
 * identification from a database. Implements {@code Callable<Optional<E>>}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <E>
 *            The type of the elements of the database.
 */
public class GetElementFromADatabaseByIdCommand< E extends Element > implements
		Callable< Optional< E >>
{
	
	// INSTANCE FIELDS
	
	/**
	 * The database where to search.
	 */
	private final Database< E > db;
	
	/**
	 * The identification of the element to get from {@link #db}.
	 */
	private final String id;
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new instance of this command that gets the element of
	 * {@code database} which the identification {@code identification}.
	 * 
	 * @param database
	 *            The database where to search.
	 * @param identification
	 *            The identification of the element to get from {@code database}
	 * @throws InvalidArgumentException
	 *             If either {@code database} or {@code identification} are
	 *             {@code null}.
	 */
	public GetElementFromADatabaseByIdCommand( Database< E > database,
			String identification ) throws InvalidArgumentException {
		
		if( database == null )
			throw new InvalidArgumentException(
					"Cannot instantiate command with null database." );
		if( identification == null )
			throw new InvalidArgumentException(
					"Cannot instantiate command with null identification." );
		
		this.db = database;
		this.id = identification;
	}
	
	
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	/**
	 * Returns the element of {@code database} (given in the constructor) which
	 * has the identification {@code identification} (given in the constructor).
	 * <p>
	 * This result is wrapped in an {@link Optional} instance. If
	 * {@code database} is instance of {@link InMemoryDatabase}, this
	 * {@link Optional}'s method {@link Optional#get()} throws a
	 * {@link NoSuchElementInDatabaseException} with the message <i>«
	 * {@code identification} not found in {@code database.getDatabaseName()}
	 * »</i> if the result is {@code null}.
	 * </p>
	 * 
	 * @return The element of {@code database} which has the identification
	 *         {@code identification}.
	 * @throws Exception
	 *             This method will not throw exceptions.
	 */
	@Override
	public Optional< E > call() throws Exception {
		
		return db.getElementByIdentification( id );
	}
	
}