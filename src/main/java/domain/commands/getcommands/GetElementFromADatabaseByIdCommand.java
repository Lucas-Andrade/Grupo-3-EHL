package main.java.domain.commands.getcommands;


import java.util.concurrent.Callable;
import main.java.domain.model.Database;
import main.java.domain.model.Element;
import main.java.domain.model.InMemoryDatabase;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * Class whose instances are commands that get an element with a certain identification from a
 * database.
 * 
 * Implements the Interface {@link Callable} of {@link Optional} of {@link Iterable} of {@link E}.
 * 
 * @param <E>
 *            The type of the elements of the database.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetElementFromADatabaseByIdCommand< E extends Element > implements
        Callable< Optional< E >> {
    
    // INSTANCE FIELDS
    
    /**
     * The database where to search.
     */
    private final Database< E > database;
    
    /**
     * The identification of the element to get from {@link #database}.
     */
    private final String identification;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new instance of this command that gets the element of {@code database} which the
     * identification {@code identification}.
     * 
     * @param database
     *            - The database where to search.
     * @param identification
     *            - The identification of the element to get from {@code database}.
     * 
     * @throws InvalidArgumentException
     *             If either {@code database} or {@code identification} are {@code null}.
     */
    public GetElementFromADatabaseByIdCommand( Database< E > database, String identification )
        throws InvalidArgumentException {
        
        if( database == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        if( identification == null || identification.equals( "" ) )
            throw new InvalidArgumentException(
                                                "Cannot instantiate command with null identification." );
        
        this.database = database;
        this.identification = identification;
    }
    
    // IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    /**
     * Returns the element of {@code database} (given in the constructor) which has the
     * identification {@code identification} (given in the constructor).
     * <p>
     * This result is wrapped in an {@link Optional} instance. If {@code database} is instance of
     * {@link InMemoryDatabase}, this {@link Optional}'s method {@link Optional#get()} throws a
     * {@link NoSuchElementInDatabaseException} with the message <i>« {@code identification} not
     * found in {@code database.getDatabaseName()} »</i> if the result is {@code null}.
     * </p>
     * 
     * @return The element of {@code database} which has the identification {@code identification}.
     * 
     * @throws Exception
     *             This method will not throw exceptions.
     */
    @Override
    public Optional< E > call() throws Exception {
        
        return database.getElementByIdentification( identification );
    }
}