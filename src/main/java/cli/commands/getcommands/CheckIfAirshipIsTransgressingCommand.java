package main.java.cli.commands.getcommands;


import java.util.concurrent.Callable;
import main.java.cli.Optional;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;



public class CheckIfAirshipIsTransgressingCommand implements
		Callable< Optional< Airship > >
{
	
	// INSTANCE FIELDS
	
	/**
	 * The database where to search.
	 */
	private final Database< Airship > db;
	
	/**
	 * The flightId of the airships to be evaluated.
	 */
	private final String flightId;
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new instance of this command that checks if the airship with
	 * flight Id {@code flightId} in {@code database} is transgressing.
	 * 
	 * @param database
	 *            The database where the airship is supposed to be found.
	 * @param flightId
	 *            The flightId of the airships to be evaluated.
	 * @throws InvalidArgumentException
	 *             If either {@code database} or {@code flightId} are
	 *             {@code null}.
	 */
	public CheckIfAirshipIsTransgressingCommand( Database< Airship > database,
			String flightId ) throws InvalidArgumentException {
		
		if( database == null )
			throw new InvalidArgumentException(
					"Cannot instantiate command with null database." );
		if( flightId == null )
			throw new InvalidArgumentException(
					"Cannot instantiate command with a null flightId." );
		
		this.db = database;
		this.flightId = flightId;
	}
	
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	/**
	 * Returns the airship with flight Id {@code flightId} (given in the
	 * constructor) if it is in {@code database} (given in the constructor) and
	 * is transgressing its pre-established air corridor. Returns {@code null}
	 * if it is in {@code database} but not transgressing.
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method
	 * {@link Optional#get()} throws {@link NullPointerException} with the
	 * message <i>«{@code flightId} is not transgressing its air corridor.»</i>
	 * if the result is {@code null}.
	 * </p>
	 * <p>
	 * This method throws {@link NoSuchElementInDatabaseException} with the
	 * message <i>«{@code flightId} not found in
	 * {@code database.getDatabaseName()}»</i> if the airship is not in
	 * {@code database} .
	 * </p>
	 * 
	 * @return The airship with flight Id {@code flightId} if it is in
	 *         {@code database} and is transgressing its pre-established air
	 *         corridor or </br> {@code null} if it is in {@code database} but
	 *         not transgressing.
	 * @throws Exception
	 *             If the airship is not in {@code database} .
	 */
	@Override
	public Optional< Airship > call() throws Exception {
		
		Airship theAirship = db.getElementByIdentification( flightId ).get();
		// method get throws exception if this flightId is not in db
		
		if( !theAirship.isTransgressing() )
			theAirship = null;
		
		return new Optional< Airship >( theAirship, new NullPointerException(
				flightId + " is not transgressing its air corridor." ) );
	}
}
