package main.java.domain.commands.patchcommands;


import java.util.concurrent.Callable;
import main.java.domain.commands.CompletionStatus;
import main.java.domain.model.Database;
import main.java.domain.model.InMemoryDatabase;
import main.java.domain.model.users.User;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * Class whose instances are commands that change the password of a user which belongs in a given
 * {@link Database} of {@link User}s.
 * 
 * Implements the interface {@link Callable} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchUserPasswordCommand implements Callable< CompletionStatus > {
    
    // INSTANCE FIELDS
    
    /**
     * The users database that contains the user we want to patch.
     */
    private final Database< User > userDatabase;
    
    /**
     * The user identification giving by the username.
     */
    private final String username;
    
    /**
     * The new user password that will be the utilized.
     */
    private final String newPassword;
    
    /**
     * 
     * The user password that will be replaced.
     */
    private final String oldPassword;
    
    
    
    // CONSTRUCTOR
    
    /**
     * Creates a new instance of this command that changes the user password that belongs to
     * {@link #userDatabase}.
     * 
     * @param usersDatabase
     *            - The database of Users where to search.
     * @param username
     *            - The user's identification.
     * @param oldPassword
     *            - The password that will be replaced.
     * @param newPassword
     *            - The password that will replace the previous one.
     * 
     * @throws InvalidArgumentException
     *             If either {@code userDataBase}, {@code username}, {@code oldPassword} or
     *             {@code newPassword} are {@code null}.
     */
    public PatchUserPasswordCommand( Database< User > usersDatabase, String username,
                                     String oldPassword, String newPassword )
        throws InvalidArgumentException {
    
        if( usersDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        if( username == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null username." );
        
        if( newPassword == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null newPassword." );
        
        if( oldPassword == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null oldPassword." );
        
        this.username = username;
        this.userDatabase = usersDatabase;
        this.newPassword = newPassword;
        this.oldPassword = oldPassword;
    }
    
    
    
    // IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    /**
     * Changes the password of a user. This operation succeeds if the {@link User} with the username
     * {@code username} (given in the constructor) is contained in {@code usersDatabase} (given in
     * the constructor) and {@code oldPassword} (given in the constructor) matches the actual
     * password of that {@link User}.
     * <p>
     * Returns a string with information regarding the success or failure of the operation.
     * 
     * If there is no user in the database with the given username the method
     * {@link InMemoryDatabase#getElementByIdentification(String)
     * getElementByIdentification(String)} return and create a new {@link Optional} that will throw
     * {@link NoSuchElementInDatabaseException} when it's {@link Optional#get() get()} method is
     * called. This will also happen if someone tries to change the "MASTER" user's password.
     * </p>
     * 
     * 
     * An error message will be returned when this exception is caught by the try-catch.
     * 
     * @return {@code "User password successfully changed"} if so; <br/>
     *         <code>"Failed authentication. <i>message</i>"</code>
     */
    @Override
    public CompletionStatus call() {
    
        /* Implementation notes: Users are immutable, changing a password requires removing the user
         * from the database, creating a new one and adding it to the database */
        
        try {
            
            User originalUser = userDatabase.getElementByIdentification( username ).get();
            
            if( originalUser.authenticatePassword( oldPassword ) ) {
                
                userDatabase.removeByIdentification( username );
                User user =
                        new User( username, newPassword, originalUser.getEmail(),
                                  originalUser.getFullName() );
                userDatabase.add( user, user );
                
                return new CompletionStatus( true, "User password successfully changed." );
            }
            
            return new CompletionStatus( false, "Failure. Old password is incorrect." );
            
        }
        catch( InvalidArgumentException e ) {
            /* never happens. this class's constructor and the User's constructor insure that the
             * arguments username, newPassword, originalUser.getEmail(), originalUser.getFullName()
             * and user are non-null. as so getElementByIdentification, removeByIdentification,
             * new User and add will never throw this exception */
            throw new InternalErrorException( "INTERNAL ERROR IN PatchUserPasswordCommand! (1)", e );
        }
        catch( NoSuchElementInDatabaseException e ) {
            /* thrown by method getElementByIdentification().get() */
            return new CompletionStatus( false, "Failure. " + e.getMessage() );
        }
        catch( DatabaseException e ) {
            /* thrown by method removeByIdentification() */
            return new CompletionStatus( false, "Failure. Cannot change MASTER's password." );
        }
        catch( Exception e ) {
            /* no other Exceptions are expected */
            throw new InternalErrorException( "INTERNAL ERROR IN PatchUserPasswordCommand! (2)", e );
        }
        
    }
    
}
