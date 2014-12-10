package airtrafficcontrol.app.appforcommandline;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;


public class InMemoryAirshipDatabase extends InMemoryDatabase< Airship >
{
	
	/**
	 * The register of the {@link Airship airships} added by each {@link User
	 * user}. Each {@link User#username username} is mapped to a {@link List} of
	 * the {@link Airship#flightId flightId}s of the {@link Airship airships} he
	 * added to this database..
	 */
	private Map< String, List< String >> flightsByUserRegister;
	
	
	/**
	 * Creates an empty {@link InMemoryAirshipDatabase in-memory airships
	 * database} with no {@link Airship airships} .
	 */
	public InMemoryAirshipDatabase() {
		super();
		flightsByUserRegister = new HashMap< String, List< String > >();
	}
	
	
	/**
	 * Returns a list with the {@link Airship#flightId flightId}s of
	 * {@link Airship airships} stored in this database that were added by the
	 * {@link User} with the {@link User#username username} {@code username}.
	 * 
	 * @param username
	 * @return A list with the {@link Airship#flightId flightId}'s of
	 *         {@link Airship airships} stored in this database that were added
	 *         by the {@link User} with {@link User#username} {@code username}.
	 */
	public List< String > getAirshipsOfUser( String username ) {
		return flightsByUserRegister.get( username );
	}
	
	/**
	 * Returns a list with the {@link Airship#flightId flightId}s of
	 * {@link Airship airships} stored in this database that satisfy the
	 * specified {@code criteria}.
	 * 
	 * @param criteria
	 * @return
	 */
	public List< String > getAirshipsThat( Predicate< Airship > criteria ) {
		
		ArrayList< String > selectedAirships = new ArrayList<>();
		for( Map.Entry< String, Airship > entry : getAll().entrySet() )
			if( criteria.test( entry.getValue() ) )
				selectedAirships.add( entry.getKey() );
		
		return selectedAirships;
	}
	
	/**
	 * Returns a list with the {@link Airship#flightId flightId}s of
	 * {@link Airship airships} stored in this database that are out of their
	 * pre-established {@link AltitudeCorridor altitude corridor}.
	 * 
	 * @return A list with the {@link Airship#flightId flightId}s of
	 *         {@link Airship airships} stored in this database that are out of
	 *         the pre-established {@link AltitudeCorridor altitude corridor}s.
	 */
	public List< String > reportTransgressions() {
		
		List< String > transgressingAirships = new ArrayList<>();
		for( Map.Entry< String, Airship > entry : getAll().entrySet() )
		{
			if( entry.isTrangressing() )
				transgressingAirships.add( entry.getKey() );
		}
		return transgressingAirships;
	}
	
	/**
	 * Checks whether the {@link Airship airship} with {@link Airship#flightId
	 * flightId} {@code flightId} is out of its pre-established
	 * {@link AltitudeCorridor altitude corridor}.
	 * 
	 * @param flightId
	 *            The {@link Airship#flightId flightId} of the {@link Airship
	 *            airship} to be evaluated.
	 * @return {@code true} if the {@link Airship airship} with
	 *         {@link Airship#flightId flightId} {@code flightId} is in the
	 *         correct {@link AltitudeCorridor altitude corridor}; </br>
	 *         {@code false} if the {@link Airship airship} with
	 *         {@link Airship#flightId flightId} {@code flightId} is out of its
	 *         pre-established {@link AltitudeCorridor altitude corridor} or is
	 *         not in this database.
	 */
	public boolean checkIfThisAirshipIsInCorridor( String flightId ) {
		
		Airship theAirship = get( flightId );
		if( theAirship == null )
			return false;
		if( theAirship.isTransgressing() )
			return true;
		return false;
	}
	
	@Override
	public boolean add( Airship airship, User user ) {
		if( super.add( airship ) )
		{
			if( flightsByUserRegister.containsKey( user.getUsername() ) )
				flightsByUserRegister.get( user.getUsername() ).add(
						airship.getFlightId() );
			else flightsByUserRegister.put( user.getUsername(),
					new ArrayList<>().add( airship.getFlightId() ) );
		}
	}
}
