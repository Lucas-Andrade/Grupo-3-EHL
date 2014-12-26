package main.java.cli.commands.getcommands;


import java.util.concurrent.Callable;
import main.java.cli.Optional;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.Element;
import main.java.cli.model.InMemoryDatabase;


/**
 * Class whose instance represent commands to get all the elements in a
 * database. Implements {@code Callable<Optional<Iterable<E>>>}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <E>
 *            The type of the elements to get.
 */
public class GetAllElementsInADatabaseCommand< E extends Element > implements
		Callable< Optional< Iterable< E >>>
{
	
	// INSTANCE FIELDS
	/**
	 * The database whose elements are to get.
	 */
	private final Database< E > database;
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new instance of this command that gets all the elements of
	 * {@code database}.
	 * 
	 * @param database
	 *            The database whose elements are to get.
	 * @throws InvalidArgumentException
	 *             If {@code database==null}.
	 */
	public GetAllElementsInADatabaseCommand( Database< E > database )
			throws InvalidArgumentException {
		
		if( database == null )
			throw new InvalidArgumentException(
					"Cannot instantiate command with null database." );
		
		this.database = database;
	}
	
	
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	/**
	 * Returns the list of all elements in {@code database} (given in the
	 * constructor).
	 * <p>
	 * This result is wrapped in an {@link Optional} instance. If
	 * {@code database} is instance of {@link InMemoryDatabase}, this
	 * {@link Optional}'s method {@link Optional#get()} throws
	 * {@link InternalErrorException} if the result is {@code null} (since this
	 * is never supposed to happen) and whose method {@link Optional#toString()}
	 * returns the string <i>« {@code getDatabaseName()} is empty.»</i> if the
	 * result is an empty list.
	 * </p>
	 * 
	 * @return The list of all elements in {@code database}.
	 * @throws Exception
	 *             This method will not throw exceptions.
	 */
	@Override
	public Optional< Iterable< E >> call() throws Exception {
		
		return database.getAllElements();
	}
}
