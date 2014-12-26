package main.java.cli.model.users;


import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.DatabaseException;
import main.java.cli.model.Database;
import main.java.cli.model.InMemoryDatabase;
import main.java.cli.model.airships.Airship;


/**
 * Class whose instances represent in-memory databases of {@link User}s. An
 * in-memory database exists only during the runtime. All
 * {@link InMemoryUsersDatabase} have a default user stored which can be used to
 * add another users to the database: this user's username is "MASTER" and
 * password is "master".</br> Implements {@link Database}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryUsersDatabase extends InMemoryDatabase< User >
{
	
	// CONSTRUTOR
	/**
	 * Creates a new instance of {@link InMemoryUserDatabase} with name
	 * {@code databaseName} and automatically generates a default {@link User}
	 * that will be added to it and that will serve as an administrator that
	 * will allow the first new {@code User} to be added.
	 * 
	 * @param databaseName
	 *            This database's name.
	 * @throws InvalidArgumentException
	 *             If {@code databaseName==null}.
	 */
	public InMemoryUsersDatabase( String databaseName )
			throws InvalidArgumentException {
		
		super( databaseName );
		try
		{
			User master = new User( "MASTER", "master", "master@master" );
			this.add( master, master );
		}
		catch( InvalidArgumentException e )
		{
			// this never happens cause user's constructor wasn't given null nor
			// empty-string arguments and method add of InMemoryDatabase was
			// called with non-null arguments
		}
	}
	
	
	
	// OVERRIDES OF InMemoryDatabase'S METHODS
	
	/**
	 * Stores the {@link Airship airship} {@code airship} in this database,
	 * added by the {@link User user} {@code userWhoIsAddingThisAirship}.
	 * 
	 * @param element
	 *            The element to be added to this database.
	 * @param userWhoIsAddingThisAirship
	 *            The user who added this element to the database.
	 * @return {@code true} if the user was successfully added;</br>
	 *         {@code false} if there's already a user with the same username or
	 *         email.
	 * @throws InvalidArgumentException
	 *             If either {@code airship} or {@code user} are {@code null}.
	 */
	@Override
	public boolean add( User userToAdd, User userWhoIsPosting )
			throws InvalidArgumentException {
		
		try
		{
			for( User user : getAllElements().get() )
				if( user.getEmail().equals( userToAdd.getEmail() ) )
					return false;
		}
		catch( Exception e )
		{// never happens because getAllElements never returns null optionals so
			// get never throws the exception
		}
		
		return super.add( userToAdd, userWhoIsPosting );
	}
	
	/**
	 * Override of the method {@link InMemoryDatabase#remove()}.
	 * 
	 * @param username
	 *            The username of the user to be removed.
	 * @return {@code true} if the user was successfully removed; </br>
	 *         {@code false} otherwise.
	 * @throws DatabaseException
	 *             When trying to remove the user with username "MASTER" from
	 *             this database.
	 * @throws InvalidArgumentException
	 *             If {@code element==null}.
	 */
	@Override
	public boolean removeByIdentification( String username )
			throws DatabaseException, InvalidArgumentException {
		
		if( username.equals( "MASTER" ) )
			throw new DatabaseException( "Cannot remove the MASTER user." );
		
		return super.removeByIdentification( username );
	}
	
}
