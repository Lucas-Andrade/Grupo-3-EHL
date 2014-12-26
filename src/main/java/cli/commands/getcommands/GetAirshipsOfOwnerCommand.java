package main.java.cli.commands.getcommands;


import java.util.concurrent.Callable;
import main.java.cli.Optional;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;


/**
 * Class whose instances represent commands to get all the airships in a
 * database that were added by a certain user. Implements
 * {@code Callable<Optional<Iterable<E>>>}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsOfOwnerCommand implements
		Callable< Optional< Iterable< Airship > >>
{
	
	// INSTANCE FIELDS
	
	/**
	 * The database where to search.
	 */
	private final InMemoryAirshipsDatabase db;
	
	/**
	 * The username of the user whose airships are to get from {@link #db}.
	 */
	private final String username;
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new instance of this command that gets the airships of
	 * {@code database} that were added by the user with username
	 * {@code ownerUsername}.
	 * 
	 * @param database
	 *            The database where to search.
	 * @param ownerUsername
	 *            The username of the user whose airships are to get from
	 *            {@code database}.
	 * @throws InvalidArgumentException
	 *             If either {@code database} or {@code ownerUsername} are
	 *             {@code null}.
	 */
	public GetAirshipsOfOwnerCommand( InMemoryAirshipsDatabase database,
			String ownerUsername ) throws InvalidArgumentException {
		
		if( database == null )
			throw new InvalidArgumentException(
					"Cannot instantiate command with null database." );
		if( ownerUsername == null )
			throw new InvalidArgumentException(
					"Cannot instantiate command with a null username." );
		
		this.db = database;
		this.username = ownerUsername;
	}
	
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	/**
	 * Returns the list of all airships in {@code database} (given in the
	 * constructor) which were added by the user with username
	 * {@code ownerUsername} (given in the constructor).
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method
	 * {@link Optional#get()} throws {@link InternalErrorException} if the
	 * result is {@code null} (since this is never supposed to happen) and whose
	 * method {@link Optional#toString()} returns the string <i>«No airship
	 * added by {@code ownerUsername}.»</i> if the result is an empty list.
	 * </p>
	 * 
	 * @return The list of all airships in {@code database} which were added by
	 *         the user with username {@code ownerUsername}.
	 * @throws Exception
	 *             This method will not throw exceptions.
	 */
	@Override
	public Optional< Iterable< Airship > > call() throws Exception {
		
		return db.getAirshipsOfUser( username );
	}
}
