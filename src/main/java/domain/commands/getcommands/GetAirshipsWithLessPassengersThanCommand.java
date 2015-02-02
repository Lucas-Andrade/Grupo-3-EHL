package main.java.domain.commands.getcommands;


import java.util.concurrent.Callable;
import main.java.domain.model.Database;
import main.java.domain.model.InMemoryDatabase;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.AirshipPredicates;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are commands that get all the airships in a database that have less than a
 * certain number of passengers.
 *
 * Implements the Interface {@link Callable} of {@link Optional} of {@link Iterable} of
 * {@link Airship}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsWithLessPassengersThanCommand implements
        Callable< Optional< Iterable< Airship >>> {
    
    // INSTANCE FIELDS
    
    /**
     * The database where to search.
     */
    private final Database< Airship > airshipDatabase;
    
    /**
     * The maximum number of passengers allowed.
     */
    private final int max;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new instance of this command that gets all the airships in {@code database} that
     * have less than a certain number of passengers.
     * 
     * @param airshipDatabase
     *            - The airships database where to search.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipDatabase} is null.
     */
    public GetAirshipsWithLessPassengersThanCommand( Database< Airship > airshipDatabase,
                                                     int maximumNumberOfPassengers )
        throws InvalidArgumentException {
        
        if( airshipDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        if( maximumNumberOfPassengers < 0 )
            throw new InvalidArgumentException(
                                                "Cannot instantiate command with negative number of passengers." );
        
        this.airshipDatabase = airshipDatabase;
        this.max = maximumNumberOfPassengers;
    }
    
    // IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    /**
     * Returns the list of all airships in {@code database} (given in the constructor) that have
     * less than a certain number of passengers
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
        
        return airshipDatabase.getAllElementsThat( new AirshipPredicates.HasPassagersNumberBelowAThreshold(
                                                                                                            max ) );
    }
}