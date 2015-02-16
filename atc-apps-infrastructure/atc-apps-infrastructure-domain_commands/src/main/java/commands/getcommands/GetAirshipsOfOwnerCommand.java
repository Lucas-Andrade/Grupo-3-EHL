package commands.getcommands;


import java.util.concurrent.Callable;
import utils.Optional;
import databases.Database;
import elements.Airship;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;


/**
 * Class whose instances are commands that get all the airships in a database that were added by a
 * specific user.
 * 
 * Implements the Interface {@link Callable} of {@link Optional} of {@link Iterable} of
 * {@link Airship}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsOfOwnerCommand implements Callable< Optional< Iterable< Airship >>> {
    
    // INSTANCE FIELDS
    
    /**
     * The database where to search.
     */
    private final Database< Airship > airshipDatabase;
    
    /**
     * The username of the user whose airships are to get from {@link #airshipDatabase}.
     */
    private final String username;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new instance of this command that gets the airships from the {@code database} that
     * were added by the user with the given {@code ownerUsername}.
     * 
     * @param airshipDatabase
     *            - The airship database where to search.
     * @param ownerUsername
     *            - The username of the user whose airships are to get from {@code airshipDatabase}.
     * 
     * @throws InvalidArgumentException
     *             If either {@code airshipDatabase} or {@code ownerUsername} are {@code null}.
     */
    public GetAirshipsOfOwnerCommand( Database< Airship > airshipDatabase, String ownerUsername )
        throws InvalidArgumentException {
    
        if( airshipDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        if( ownerUsername == null || ownerUsername.equals( "" ) )
            throw new InvalidArgumentException( "Cannot instantiate command with a null username." );
        
        this.airshipDatabase = airshipDatabase;
        this.username = ownerUsername;
    }
    
    // IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    /**
     * Returns the list of all airships in the {@code airshipDatabase} (given in the constructor)
     * which were added by the user with {@code ownerUsername} (given in the constructor).
     * <p>
     * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()}
     * throws {@link InternalErrorException} if the result is {@code null} (since this is never
     * supposed to happen) and whose method {@link Optional#toString()} returns the string <i>«No
     * airship added by {@code ownerUsername}.»</i> if the result is an empty list.
     * </p>
     * 
     * @return The list of all airships in {@code database} which were added by the user with
     *         username {@code ownerUsername}.
     */
    @Override
    public Optional< Iterable< Airship >> call() {
    
        try {
            return airshipDatabase.getElementsByUser( username );
        }
        catch( InvalidArgumentException e ) {
            throw new InternalErrorException( "UNEXPECTED ERROR IN GetAirshipsOfOwnerCommand! username"
                                        + " SHOULD NOT BE null", e );
        }
    }
    
}
