package main.java.domain.commands;


import java.util.concurrent.Callable;
import main.java.domain.model.Database;
import main.java.domain.model.users.User;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * Class whose instances are commands that authenticate a {@code password} for a {@code User} with
 * the given {@code username}.
 * 
 * Implements the Interface {@link Callable} of {@link Callable} of {@link User}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AuthenticateUserCommand implements Callable< Optional< User > > {
    
    private User user;
    private String password;
    private String username;
    
    /**
     * Creates a new instance of this command that authenticate a {@code password} for the
     * {@code User} with the given {@code username}.
     * 
     * @param username
     * @param password
     * @param usersDatabase
     * 
     * @throws Exception
     *             If there is no {@code user} with the {@code username} in {@code database} .
     * @throws InvalidArgumentException
     *             If the {@code usersDatabase} is null.
     */
    public AuthenticateUserCommand( String username, String password, Database< User > usersDatabase )
        throws NoSuchElementInDatabaseException, InvalidArgumentException {
        
        
        if( usersDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        this.username = username;
        this.password = password;
        
        try {
            this.user = usersDatabase.getElementByIdentification( username ).get();
        }
        catch( Exception e ) {
            throw (NoSuchElementInDatabaseException)e;
        }
    }
    
    /**
     * Return the authenticated {@code user}.
     * 
     * This result is wrapped in an {@link Optional} instance. If the given password does not match
     * with user password, the {@code Optional} wraps {@code null} and the {@link Optional#get()}
     * method throws a {@link WrongLoginPasswordException} with the message <i>« "Wrong password:
     * {@code username}'s password is not {@code password}. »</i> if the result is {@code null}.
     * 
     * @return The authenticated {@code user}, if the password is correct.
     */
    @Override
    public Optional< User > call() {
        
        if( !user.authenticatePassword( password ) )
            user = null;
        return new Optional< User >( user, new WrongLoginPasswordException( username, password ) );
    }
}
