package main.java.domain.commands.getcommands;


import java.util.concurrent.Callable;
import main.java.domain.model.Database;
import main.java.domain.model.Element;
import main.java.domain.model.InMemoryDatabase;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are commands that get all the elements in a database.
 * 
 * Implements the Interface {@link Callable} of {@link Optional} of {@link Iterable} of {@link E}.
 *
 * @param <E>
 *            The type of the elements to get.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllElementsInADatabaseCommand< E extends Element > implements
        Callable< Optional< Iterable< E >>> {
    
    // INSTANCE FIELDS
    
    /**
     * The database whose elements are to get.
     */
    private final Database< E > database;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new instance of this command that gets all the elements from {@code database}.
     * 
     * @param database
     *            - The database whose elements are to get.
     * 
     * @throws InvalidArgumentException
     *             If the {@code database} is null.
     */
    public GetAllElementsInADatabaseCommand( Database< E > database )
        throws InvalidArgumentException {
        
        if( database == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        this.database = database;
    }
    
    // IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    /**
     * Returns the list of all elements in the {@code database} (given in the constructor).
     * <p>
     * This result is wrapped in an {@link Optional} instance. If {@code database} is instance of
     * {@link InMemoryDatabase}, this {@link Optional}'s method {@link Optional#get()} throws
     * {@link InternalErrorException} if the result is {@code null} (since this is never supposed to
     * happen) and whose method {@link Optional#toString()} returns the string <i>«
     * {@code getDatabaseName()} is empty.»</i> if the result is an empty list.
     * </p>
     * 
     * @return The list of all the elements in the {@code database}.
     * 
     * @throws Exception
     *             This method will not throw exceptions.
     */
    @Override
    public Optional< Iterable< E >> call() throws Exception {
        
        return database.getAllElements();
    }
}