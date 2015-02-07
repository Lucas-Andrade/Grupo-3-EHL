package databases;



import java.util.Comparator;
import java.util.function.Predicate;

import utils.Optional;
import elements.Element;
import elements.User;
import exceptions.DatabaseException;
import exceptions.InvalidArgumentException;


/**
 * Interface whose instances represent databases of elements that have an individual identification.
 *
 * @param <T>
 *            The type of the elements stored in the database.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface Database< T extends Element > {
    
    // Add and Remove
    
    /**
     * Stores an {@link Element} in this database, added by a specific {@link User}.
     * 
     * @param element
     *            - The element to be added.
     * @param user
     *            - The user that is adding the element.
     * 
     * @return {@code true} if the element was successfully added and {@code false} otherwise.
     * 
     * @throws InvalidArgumentException
     *             If invalid values are given either to {@code element} or {@code user}.
     */
    public boolean add( T element, User user ) throws InvalidArgumentException;
    
    /**
     * Removes the {@code element} with the given {@code identification} from this database.
     * 
     * @param identification
     *            - The identification of the {@code element} to be removed.
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
    public boolean removeByIdentification( String identification )
        throws DatabaseException, InvalidArgumentException;
    
    // Gets
    
    /**
     * Returns the element of this database whose identification is the given {@code identification}
     * or returns {@code null} if there's no such element in this database. This result is wrapped
     * in an {@link Optional} instance.
     * 
     * @param identification
     *            - The element's identification.
     * 
     * @return The element of this database whose identification is {@code identification} if it
     *         exists or {@code null} if there's no such element in this database.
     * 
     * @throws InvalidArgumentException
     *             If an invalid value is given to {@code identification}.
     * 
     * @see Optional
     */
    public Optional< T > getElementByIdentification( String identification )
        throws InvalidArgumentException;
    
    /**
     * Returns an {@link Iterable} of all elements stored in this database. This result is wrapped
     * in an {@link Optional} instance.
     * 
     * @return An {@link Optional} {@link Iterable} of all elements stored in this database.
     * 
     * @see Optional
     */
    public Optional< Iterable< T >> getAllElements();
    
    /**
     * Returns an {@link Iterable} of all elements stored in this database that verify a certain
     * condition. This result is wrapped in an {@link Optional} instance.
     * 
     * @param criteria
     *            - The condition the elements need to verify to be chosen.
     * 
     * @return An {@link Optional} {@link Iterable} of all elements stored in this database.
     * 
     * @see Optional
     */
    public Optional< Iterable< T >> getAllElementsThat( Predicate< T > criteria )
        throws InvalidArgumentException;
    
    /**
     * Returns an {@link Iterable} of all elements stored in this database that were added by a
     * specific {@code user}. This result is wrapped in an {@link Optional} instance.
     * 
     * @param username
     *            - The username of the {@code user} whose added elements we want to obtain.
     * 
     * @return An {@link Optional} {@link Iterable} of all the elements added by the {@code user}
     *         with the given {@code username}.
     */
    public Optional< Iterable< T >> getElementsByUser( String username );
    
    /**
     * Returns this database's name.
     * 
     * @return This database's name.
     */
    public String getDatabaseName();
    
    // Sort
    
    /**
     * Returns an {@link Iterable} of all elements stored in this database sorted by a specific
     * criteria. This result is wrapped in an {@link Optional} instance.
     * 
     * @param criteria
     *            - The criteria by which the elements will be sorted.
     * 
     * @return An {@link Optional} {@link Iterable} of all elements stored in this database properly
     *         ordered.
     * 
     * @see Optional
     */
    public Optional< Iterable< T >> sortBy( Comparator< T > criteria )
        throws InvalidArgumentException;
}
