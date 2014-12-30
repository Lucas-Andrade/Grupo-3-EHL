package main.java.cli.model.airships;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import main.java.cli.Optional;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.DatabaseException;
import main.java.cli.model.InMemoryDatabase;
import main.java.cli.model.users.User;


/**
 * Class whose instances represent in-memory databases of {@link Airship}s. An
 * in-memory database exists only during the runtime. </br> Implements
 * {@link Database}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryAirshipsDatabase extends InMemoryDatabase< Airship >
{
	
	// Instance Field
	/**
	 * The container {@link Map} where all the {@link Airship}s will be stored
	 * by {@link User}:
	 * <ul>
	 * <li>The keys are Strings with the users identifications;</li>
	 * <li>The values will be a {@link List} of all the {@link Airship}s added
	 * by the user whose identification is stored in the corresponding key.</li>
	 * </ul>
	 */
	private Map< String, List< Airship >> flightsByUserRegister;
	
	// Constructor
	/**
	 * Creates an empty {@link InMemoryAirshipDatabase} with the name
	 * {@code databaseName}.
	 * 
	 * @param databaseName
	 *            This database's name.
	 * @throws InvalidArgumentException
	 *             If {@code databaseName==null}.
	 */
	public InMemoryAirshipsDatabase( String databaseName )
			throws InvalidArgumentException {
		
		super( databaseName );
		flightsByUserRegister = new HashMap< String, List< Airship >>();
	}
	
	
	
	// OVERRIDES OF METHODS OF InMemoryDatabase
	
	/**
	 * Stores the {@link Airship} {@code airship} in this database, added by the
	 * {@link User} {@code userWhoIsAddingThisAirship}.
	 * 
	 * @param element
	 *            The element to be added to this database.
	 * @param userWhoIsAddingThisAirship
	 *            The user who is adding this element to the database.
	 * @return {@code true} if the element was successfully added;</br>
	 *         {@code false} if there's already an airship with the same flight
	 *         id.
	 * @throws InvalidArgumentException
	 *             If either {@code airship} or {@code user} are {@code null}.
	 */
	@Override
	public boolean add( Airship airship, User user )
			throws InvalidArgumentException {
		
		if( super.add( airship, user ) )
		{
			addAirshipToItsUsersListOfAirships( airship, user );
			return true;
		}
		
		return false;
	}
	
	/**
	 * Removes the {@code Element} with identification {@code identification}
	 * from this database.
	 * 
	 * @param flightId
	 *            The flightId of the airship to be removed.
	 * @return {@code true} if the airship was successfully removed; </br>
	 *         {@code false} otherwise.
	 * @throws DatabaseException
	 *             When trying to perform an forbidden operation in a database.
	 * @throws InvalidArgumentException
	 *             If {@code flightId==null}.
	 */
	@Override
	public boolean removeByIdentification( String flightId )
			throws InvalidArgumentException, DatabaseException {
		
		if( super.removeByIdentification( flightId ) )
		{
			removeAirshipFromItsUsersListOfAirships( flightId );
			return true;
		}
		
		return false;
	}
	
	
	
	// OTHER PUBLIC METHODS
	
	/**
	 * Returns a list with the {@link Airship}s stored in this database that
	 * were added by the {@link User} with the username {@code username}.
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method
	 * {@link Optional#get()} throws {@link InternalErrorException} if the
	 * result is {@code null} (since this is never supposed to happen) and whose
	 * method {@link Optional#toString()} returns the string <i>«No airship
	 * added by {@code username}.»</i>.
	 * </p>
	 * 
	 * @param username
	 *            The username of the user whose airships are to get.
	 * @return A list of {@link Airship}s stored in this database that were
	 *         added by the {@link User} with username {@code username}.
	 */
	public Optional< Iterable< Airship >> getAirshipsOfUser( String username ) {
		
		List< Airship > list = flightsByUserRegister.get( username );
		if( list == null )
			list = new ArrayList<>();
		
		try
		{
			return new Optional< Iterable< Airship >>(
					list,
					new InternalErrorException(
							"Not supposed to generate null list in getAirshipsOfUser()" ),
					"No airship added by " + username );
		}
		catch( InvalidArgumentException e )
		{// never happens because new InternalErrorException() is not null
			return null;
		}
	}
	
	/**
	 * by G Problems: Optional not used, IndexOutOfBoundsException extends
	 * RuntimeException
	 * by E @ G: See if this solves! 
	 * 
	 * 
	 * Returns an {@link Iterable} with the {@code nrOfAirshipsToGet}
	 * {@link Airship}s stored in this database that are closer to the
	 * {@link GeographicPosition} {@code reference}. The altitudes are not taken
	 * in consideration.
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method
	 * {@link Optional#get()} throws {@link InternalErrorException} if the
	 * result is {@code null} (since this is never supposed to happen) and whose
	 * method {@link Optional#toString()} returns the string <i>«
	 * {@code databaseName} is empty.»</i>.
	 * </p>
	 * 
	 * 
	 * @param reference
	 *            The reference latitude and longitude.
	 * @param nrOfAirshipsToGet
	 *            The number of closer airships to get.
	 * @return an {@link Airship} {@code List} sorted by the distance from each
	 *         {@code Airship} {@link GeographicPosition} to a
	 *         {@code GeographicPositionReference}.
	 * @throws InvalidArgumentException
	 *             If {@code nrOfAirshipsToGet<0}.
	 */
	public Optional< Iterable< Airship >> getAirshipsCloserTo(
			GeographicPosition reference, int nrOfAirshipsToGet )
			throws InvalidArgumentException {
		
		
		if( nrOfAirshipsToGet < 0 )
			throw new InvalidArgumentException(
					"Number of airships cannot be negative." );
		
		
		List< Airship > airshipsList = new ArrayList< Airship >();
		try
		{
			airshipsList.addAll( getAll().get().values() );
		}
		catch( Exception e )
		{ // never happens because getAll() never has null values
			System.out.println( "ERROR1 in getAirshipsCloserTo()" );
		}
		airshipsList.sort( new AirshipComparators.ComparatorByDistance(
				reference ) );
		
		
		if( nrOfAirshipsToGet <= airshipsList.size() )
			airshipsList = airshipsList.subList( 0, nrOfAirshipsToGet );
		try
		{
			return new Optional< Iterable< Airship >>(
					airshipsList,
					new InternalErrorException(
							"Not supposed to generate null list in getAirshipsCloserTo()" ),
					getDatabaseName() + " is empty." );
		}
		catch( InvalidArgumentException e )
		{// never happens cause the value given is instance of collection and
			// the string given is not null
			System.out.println( "ERROR2 in getAirshipsCloserTo()" );
			return null;
		}
		
	}
	
	
	// AUXILIARY PRIVATE METHODS
	
	// used in method add
	/**
	 * Updates the list of airships of {@code user} by adding {@code airship} to
	 * it.
	 * 
	 * @param airship
	 *            The airship to be added to {@code user}'s list of airships.
	 * @param user
	 *            The user whose list is to be updated.
	 */
	private void addAirshipToItsUsersListOfAirships( Airship airship, User user ) {
		
		if( flightsByUserRegister.containsKey( user.getIdentification() ) )
			flightsByUserRegister.get( user.getIdentification() ).add( airship );
		
		else
		{
			List< Airship > newListForNewUser = new ArrayList<>();
			newListForNewUser.add( airship );
			flightsByUserRegister.put( user.getIdentification(),
					newListForNewUser );
		}
	}
	
	// used in method removeByIdentification
	/**
	 * Updates the list of airships of the owner of the {@link Airship} with the
	 * given {@code flightId} by removing it.
	 * 
	 * @param flightId
	 *            The flightId of the airship to be removed from its owner's
	 *            list of airships.
	 */
	private void removeAirshipFromItsUsersListOfAirships( String flightId ) {
		
		for( Entry< String, List< Airship >> entry : flightsByUserRegister
				.entrySet() )
		{
			List< Airship > list = entry.getValue();
			for( Airship airship : list )
				if( airship.getIdentification().equals( flightId ) )
				{
					list.remove( airship );
					if( list.isEmpty() )
						flightsByUserRegister.remove( entry.getKey() );
					return;
				}
		}
	}
	
	
	
}
