package main.java.domain.model.users;


import main.java.domain.model.Database;
import main.java.domain.model.InMemoryDatabase;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;


/**
 * Class whose instances represent in-memory databases of {@link User}s. An in-memory database
 * exists only during the runtime.
 * <p>
 * All {@link InMemoryUsersDatabase} have a default user stored since created; this user's username
 * is {@code MASTER} and password is {@code master}. The existence of {@code MASTER} allows new
 * users to be added to this database, since method {@link #add(User, User)} requires that new users
 * must be added by other users.
 * </p>
 * <p>
 * Implements {@link Database}.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryUsersDatabase extends InMemoryDatabase< User > {
    
    // Constructor
    
    /**
     * Creates a new {@link InMemoryUserDatabase} named {@code databaseName} and automatically adds
     * to it a {@link User} with username {@code MASTER}, password {@code master} and email
     * {@code master@master}.
     * 
     * @param databaseName
     *            This database's name.
     * 
     * @throws InvalidArgumentException
     *             If {@code databaseName} is null.
     */
    public InMemoryUsersDatabase( String databaseName ) throws InvalidArgumentException {
    
        super( databaseName );
        
        try {
            User master = new User( "MASTER", "master", "master@master" );
            this.add( master, master );
        }
        catch( InvalidArgumentException e ) {
            throw new InternalErrorException( "UNEXPECTED ERROR IN InMemoryUsersDatabase! (1)", e );
            // this never happens cause user's constructor wasn't given null nor empty-string
            // arguments and method add of InMemoryDatabase was called with non-null arguments
        }
        
    }
    
    // OVERRIDES OF InMemoryDatabase'S METHODS
    
    /**
     * Stores an {@link #User} in this database, added by another {@link User}.
     * 
     * <p>
     * If there is another {@link User} with the same {@code username} or {@code email} in
     * {@code this} database, {@code userToAdd} will not be added.
     * </p>
     * 
     * @param userToAdd
     *            - The {@code User} to be added to this database.
     * @param userWhoIsPosting
     *            - The {@code User} who is adding {@code userToAdd} to the database.
     * 
     * @return {@code true} if {@code userToAdd} was successfully added; <br/>
     *         {@code false} otherwhise.
     * 
     * @throws InvalidArgumentException
     *             If either of the given {@code Users} are {@code null}.
     */
    @Override
    public boolean add( User userToAdd, User userWhoIsPosting ) throws InvalidArgumentException {
    
        try {
            for( User user : getAllElements().get() )
                if( user.getEmail().equals( userToAdd.getEmail() ) )
                    return false;
            
        }
        catch( Exception e ) {
            throw new InternalErrorException( "UNEXPECTED ERROR IN InMemoryUsersDatabase! (2)", e );
            // never happens because getAllElements never returns null optionals so the get() method
            // never throws the exception!
        }
        
        return super.add( userToAdd, userWhoIsPosting );
    }
    
    /**
     * Removes the {@code user} with the given {@code username} from this database. Override of the
     * method {@link InMemoryDatabase#remove() remove()}.
     * 
     * If an {@code user} with the given {@code username} doesn't exist in {@code this} database no
     * {@code user} will be removed.
     * 
     * @param username
     *            - The username of the user to be removed.
     * @return {@code true} if the user was successfully removed and {@code false} otherwise.
     * 
     * @throws DatabaseException
     *             When trying to remove the {@link User} with username "MASTER" from this database.
     * @throws InvalidArgumentException
     *             If the given {@code username} is null.
     */
    @Override
    public boolean removeByIdentification( String username )
        throws DatabaseException, InvalidArgumentException {
    
        if( username.equals( "MASTER" ) )
            throw new DatabaseException( "Cannot remove the MASTER user." );
        
        return super.removeByIdentification( username );
    }
    
    // Gets
    
    /**
     * Returns a list with the {@link #User users} stored in this database that were added by the
     * {@link User} with the given {@code username}.
     * 
     * If there isn't such a list the method will return an empty list.
     * <p>
     * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()
     * get()} throws {@link InternalErrorException} if the result is {@code null} (since this is
     * never supposed to happen) and whose method {@link Optional#toString() toString()} returns the
     * string <i>«No user added by {@code username}.»</i>.
     * </p>
     * 
     * @param username
     *            - The username of the user whose added users we want to get.
     * 
     * @return A list of {@link #User users} stored in this database that were added by the
     *         {@link User} with the given {@code username}.
     * 
     * @see Optional
     */
    @Override
    public Optional< Iterable< User >> getElementsByUser( String username ) {
    
        throw new UnsupportedOperationException(
                                                 "InMemoryUsersDatabase does not suport this operation" );
    }
}
