package main.java.cli.commands.getcommands;


import java.util.concurrent.Callable;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.AirshipPredicates;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.utils.Optional;
import main.java.cli.utils.exceptions.InternalErrorException;
import main.java.cli.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instance represent commands to get all the airships in a database
 * that have less than a certain number of passengers. Implements
 * {@code Callable<Optional<Iterable<Airship>>>}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsWithLessPassengersThanCommand implements
		Callable< Optional< Iterable< Airship >>>
{
	
	// INSTANCE FIELDS
	
	/**
	 * The database where to search.
	 */
	private final InMemoryAirshipsDatabase database;
	
	/**
	 * The maximum number of passengers allowed.
	 */
	private final int max;
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new instance of this command that gets all the airships in
	 * {@code database} that have less than a certain number of passengers.
	 * 
	 * @param database
	 *            The database where to search.
	 * @throws InvalidArgumentException
	 *             If {@code database==null}.
	 */
	public GetAirshipsWithLessPassengersThanCommand(
			InMemoryAirshipsDatabase database, int maximumNumberOfPassengers )
			throws InvalidArgumentException {
		
		if( database == null )
			throw new InvalidArgumentException(
					"Cannot instantiate command with null database." );
		if( maximumNumberOfPassengers < 0 )
			throw new InvalidArgumentException(
					"Cannot instantiate command with negative number of passengers." );
		
		this.database = database;
		this.max = maximumNumberOfPassengers;
	}
	
	
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	/**
	 * Returns the list of all airships in {@code database} (given in the
	 * constructor) that have less than a certain number of passengers
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
				.getAllElementsThat( new AirshipPredicates.HasPassagersNumberBelowAThreshold(
						max ) );
	}
	
}
