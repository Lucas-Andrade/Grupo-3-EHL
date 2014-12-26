package main.java.cli.commands.getcommands;


import java.util.concurrent.Callable;
import main.java.cli.Optional;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.AirshipPredicates;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;


/**
 * Class whose instance represent commands to get all the airships in a database
 * that are transgressing their pre-established air corridors. Implements
 * {@code Callable<Optional<Iterable<Airship>>>}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllTransgressorAirshipsCommand implements
		Callable< Optional< Iterable< Airship >>>
{
	
	// INSTANCE FIELDS
	/**
	 * The database where to search.
	 */
	private final InMemoryAirshipsDatabase database;
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new instance of this command that gets all the airships in
	 * {@code database} that are transgressing their pre-established air
	 * corridors.
	 * 
	 * @param database
	 *            The database where to search.
	 * @throws InvalidArgumentException
	 *             If {@code database==null}.
	 */
	public GetAllTransgressorAirshipsCommand( InMemoryAirshipsDatabase database )
			throws InvalidArgumentException {
		
		if( database == null )
			throw new InvalidArgumentException(
					"Cannot instantiate command with null database." );
		
		this.database = database;
	}
	
	
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	/**
	 * Returns the list of all airships in {@code database} (given in the
	 * constructor) that are transgressing their pre-established air corridors.
	 * <p>
	 * This result is wrapped in an {@link Optional} instance. If
	 * {@code database} is instance of {@link InMemoryDatabase}, this
	 * {@link Optional}'s method {@link Optional#get()} throws
	 * {@link InternalErrorException} if the result is {@code null} (since this
	 * is never supposed to happen) and whose method {@link Optional#toString()}
	 * returns the string <i>«No such element in {@code getDatabaseName()}»</i>
	 * if the result is an empty list.
	 * </p>
	 * 
	 * @return The list of all elements in {@code database} that are
	 *         transgressing their pre-established air corridors.
	 * @throws Exception
	 *             This method will not throw exceptions.
	 */
	@Override
	public Optional< Iterable< Airship >> call() throws Exception {
		
		return database
				.getAllElementsThat( new AirshipPredicates.IsTrangressingItsAirCorridor() );
	}
	
}
