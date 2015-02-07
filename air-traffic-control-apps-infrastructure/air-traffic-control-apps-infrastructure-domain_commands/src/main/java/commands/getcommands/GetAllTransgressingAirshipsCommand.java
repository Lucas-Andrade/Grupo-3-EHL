package commands.getcommands;


import java.util.concurrent.Callable;

import utils.AirshipPredicates;
import utils.Optional;
import databases.Database;
import databases.InMemoryDatabase;
import elements.Airship;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;


/**
 * Class whose instances are commands that get all the airships in a database that are transgressing
 * their pre-established air corridors.
 * 
 * Implements the Interface {@link Callable} of {@link Optional} of {@link Iterable} of
 * {@link Airship}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllTransgressingAirshipsCommand implements Callable< Optional< Iterable< Airship >>> {
    
    // INSTANCE FIELDS
    
    /**
     * The database where to search.
     */
    private final Database< Airship > airshipsDatabase;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new instance of this command that gets all the airships in {@code database} that
     * are transgressing their pre-established air corridors.
     * 
     * @param airshipsDatabase
     *            - The airships database where to search.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is null.
     */
    public GetAllTransgressingAirshipsCommand( Database< Airship > airshipsDatabase )
        throws InvalidArgumentException {
    
        if( airshipsDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        this.airshipsDatabase = airshipsDatabase;
    }
    
    // IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    /**
     * Returns the list of all airships in {@code database} (given in the constructor) that are
     * transgressing their pre-established air corridors.
     * <p>
     * This result is wrapped in an {@link Optional} instance. If {@code database} is instance of
     * {@link InMemoryDatabase}, this {@link Optional}'s method {@link Optional#get()} throws
     * {@link InternalErrorException} if the result is {@code null} (since this is never supposed to
     * happen) and whose method {@link Optional#toString()} returns the string <i>«No such element
     * in {@code getDatabaseName()}»</i> if the result is an empty list.
     * </p>
     * 
     * @return The list of all elements in {@code database} that are transgressing their
     *         pre-established air corridors.
     * 
     * @throws Exception
     *             This method will not throw exceptions.
     */
    @Override
    public Optional< Iterable< Airship >> call() throws Exception {
    
        return airshipsDatabase.getAllElementsThat( new AirshipPredicates.IsTrangressingItsAirCorridor() );
    }
}
