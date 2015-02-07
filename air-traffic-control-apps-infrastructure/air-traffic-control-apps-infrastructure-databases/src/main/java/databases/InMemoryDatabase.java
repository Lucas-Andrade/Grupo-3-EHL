package databases;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import utils.Optional;
import elements.Element;
import elements.User;
import exceptions.DatabaseException;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.NoSuchElementInDatabaseException;


/**
 * Abstract class whose subclasses' instances represent in-memory databases of {@link Element}s that
 * have an identification. An in-memory database exists only during the runtime.
 * 
 * @param <T>
 *            The type of the elements stored in the database.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class InMemoryDatabase< T extends Element > implements Database< T > {
    
    // INSTANCE FIELDS
    
    /**
     * This database's name
     */
    private String databaseName;
    
    /**
     * The {@link Map} container of {@link #Element}s. Stores {@link Element}s as values and the
     * corresponding keys are their identifications (obtained via
     * {@link Element#getIdentification()}).
     */
    private Map< String, T > database;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link InMemoryDatabase} with name {@code databaseName}.
     * 
     * @throws InvalidArgumentException
     *             If the {@code databaseName} is null.
     */
    public InMemoryDatabase( String databaseName ) throws InvalidArgumentException {
        
        if( databaseName == null )
            throw new InvalidArgumentException( "Cannot instantiate database with null name." );
        
        this.databaseName = databaseName;
        this.database = new HashMap< String, T >();
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM THE Database INTERFACE
    
    // Add and Remove
    
    /**
     * Stores the {@link Element} {@code element} in this database, added by the {@link User}
     * {@code user}.
     * <p>
     * If there is already an {@link Element} with the same identification (obtained via
     * {@link Element#getIdentification()}) as {@code element} in this database, {@code element}
     * will not be added.
     * </p>
     * 
     * @param element
     *            - The element to be added to this database.
     * @param user
     *            - The user who is adding {@code element} to this database.
     * 
     * @return {@code true} if the element was successfully added; <br/>
     *         {@code false} otherwise.
     * 
     * @throws InvalidArgumentException
     *             If either {@code element} or {@code user} are {@code null}.
     */
    @Override
    public boolean add( T element, User user ) throws InvalidArgumentException {
        
        if( element == null )
            throw new InvalidArgumentException( "Cannot add null elements to database." );
        
        if( user == null )
            throw new InvalidArgumentException( "Elements cannot be added by a null user." );
        
        if( database.containsKey( element.getIdentification() ) )
            return false;
        
        database.put( element.getIdentification(), element );
        return true;
    }
    
    /**
     * Removes the {@link Element} with identification {@code identification} from this database. If
     * this database does not contain an {@code Element} with the given identification, the method
     * returns {@code false}.
     * 
     * @param identification
     *            - The identification of the element to be removed.
     * 
     * @return {@code true} if the element was successfully removed; <br/>
     *         {@code false} otherwise.
     * 
     * @throws DatabaseException
     *             If this is a forbidden operation in this database, or the given string argument
     *             makes it a forbidden operation.
     * @throws InvalidArgumentException
     *             If {@code identification} is null.
     */
    @Override
    public boolean removeByIdentification( String identification )
        throws DatabaseException, InvalidArgumentException {
        
        if( identification == null )
            throw new InvalidArgumentException( "Cannot remove element with null identification." );
        
        if( !database.containsKey( identification ) )
            return false;
        
        database.remove( identification );
        return true;
    }
    
    // Gets
    
    /**
     * Returns the element of this database with the given {@code identification} (uses method
     * {@link Element#getIdentification() getIdentification()}).
     * <p>
     * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()
     * get()} throws a {@link NoSuchElementInDatabaseException} with the message <i>«
     * {@code identification} not found in {@link #getDatabaseName()}»</i> if the result is
     * {@code null}.
     * </p>
     * 
     * @param identification
     *            - The element's identification.
     * 
     * @return The element of this database whose identification is {@code identification}; </br>
     *         {@code null} if there's no such element in this database.
     * 
     * @throws InvalidArgumentException
     *             If {@code identification} is null.
     * 
     * @see Optional
     */
    @Override
    public Optional< T > getElementByIdentification( String identification )
        throws InvalidArgumentException {
        
        if( identification == null )
            throw new InvalidArgumentException( "Cannot get element with null identification." );
        
        return new Optional< T >( database.get( identification ),
                                  new NoSuchElementInDatabaseException( identification,
                                                                        databaseName ) );
    }
    
    /**
     * Returns an {@link Iterable} of all elements stored in this database.This iterable is
     * "read-only"; attempts to modify it result in an {@link UnsupportedOperationException}.
     * <p>
     * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()
     * get()} throws {@link InternalErrorException} if the result is {@code null} (since this is
     * never supposed to happen) and whose method {@link Optional#toString() toString()} returns the
     * string <i>« {@code getDatabaseName()} is empty.»</i>.
     * </p>
     * 
     * @return An {@link Optional} {@link Iterable} of all {@link #Element elements} stored in this
     *         database.
     * 
     * @see Optional
     */
    public Optional< Iterable< T >> getAllElements() {
        
        return new Optional< Iterable< T >>(
                                             Collections.unmodifiableCollection( database.values() ),
                                             getDatabaseName() + " is empty." );
    }
    
    /**
     * Returns an {@link Iterable} with the elements stored in this database that satisfy the
     * specified {@code criteria}.
     * <p>
     * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()
     * get()} throws {@link InternalErrorException} if the result is {@code null} (since this is
     * never supposed to happen) and whose method {@link Optional#toString() toString()} returns the
     * string <i>«No such element in {@code getDatabaseName()}»</i>.
     * </p>
     * 
     * @param criteria
     *            - The criteria to be satisfied by all elements returned.
     * @return An {@link Iterable} with the elements stored in this database that satisfy the
     *         specified {@code criteria}.
     * 
     * @throws InvalidArgumentException
     *             If {@code criteria} is {@code null}.
     * 
     * @see Optional
     */
    public Optional< Iterable< T >> getAllElementsThat( Predicate< T > criteria )
        throws InvalidArgumentException {
        
        if( criteria == null )
            throw new InvalidArgumentException( "Cannot use a null criteria to get elements from "
                                                + databaseName );
        
        List< T > selectedElements = new ArrayList<>();
        
        try {
            for( T element : getAllElements().get() )
                if( criteria.test( element ) )
                    selectedElements.add( element );
            
        }
        catch( Exception e ) {
            throw new InternalErrorException( "UNEXPECTED EXCEPTION IN InMemoryDatabase!", e );
            // never happens because getAllElements().get() is never null
        }
        
        return new Optional< Iterable< T >>( selectedElements, "No such element in " + databaseName );
    }
    
    /**
     * Returns this database's name.
     * 
     * @return This database's name.
     */
    public String getDatabaseName() {
        
        return databaseName;
    }
    
    // Sort
    
    /**
     * Returns an {@link Iterable} with the elements stored in this database sorted by a specific
     * {@code criteria}.
     * <p>
     * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()
     * get()} throws {@link InternalErrorException} if the result is {@code null} (since this is
     * never supposed to happen) and whose method {@link Optional#toString() toString()} returns the
     * string <i>«{@code getDatabaseName() is empty!}»</i>.
     * </p>
     * 
     * @param criteria
     *            - The criteria by which all the elements will be sorted.
     * @return An {@link Iterable} with the elements stored in this database ordered
     *         {@code criteria}.
     * 
     * @throws InvalidArgumentException
     *             If the {@code criteria} is {@code null}.
     * 
     * @see Optional
     */
    public Optional< Iterable< T >> sortBy( Comparator< T > criteria )
        throws InvalidArgumentException {
        
        if( criteria == null )
            throw new InvalidArgumentException(
                                                "Cannot use a null criteria to sort the elements from "
                                                        + databaseName );
        
        List< T > elementsList = new ArrayList< T >();
        
        try {
            for( T element : getAllElements().get() )
                elementsList.add( element );
            
        }
        catch( Exception e ) {
            throw new InternalErrorException( "UNEXPECTED ERROR IN InMemoryDatabase!", e );
            // never happens because getAllElements().get() is never returns null!
        }
        
        elementsList.sort( criteria );
        
        return new Optional< Iterable< T >>( elementsList, getDatabaseName() + " is empty." );
    }
}
