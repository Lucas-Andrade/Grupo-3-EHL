package utils;


import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import exceptions.InvalidArgumentException;
import exceptions.NullValueInOptionalException;


/**
 * Class whose instances represent values returned by methods. This class is inspired by
 * {@link java.util.Optional}'s structure and functionality, with some twists: its purpose is not
 * only to wrap results that might be {@code null} and prevent unexpected
 * {@link NullPointerException}s that might come from trying to use them, but also to wrap results
 * that might be empty collections or empty maps and for which we would like to specify its string
 * representation.
 * <p>
 * If the value represented by a certain <code>Optional{@literal <}T></code> instance is
 * {@code null}, the method {@link #get()} throws an exception and the method {@link #toString()}
 * returns that same exception's message. The exception to be thrown is either the one chosen in the
 * instantiation moment or a default {@link NullValueInOptionalException} with message
 * <i>«null»</i>.
 * </p>
 * <p>
 * If the value represented by a certain <code>Optional{@literal <}T></code> instance is an empty
 * collection or an empty map (meaning {@code T} implements {@link Collection} or implements
 * {@link Map} and {@code isEmpty()} ), the method {@link #toString()} may either return a specific
 * {@link String} chosen in the instantiation moment or the default string representation of the
 * value.
 * </p>
 * <p>
 * There are three constructors. All constructors receive as argument:
 * <ol>
 * <li>the value {@code value} of type {@code T} to be represented and</li>
 * </ol>
 * Two of these constructors allow the representation of {@code null} values; they receive:
 * <ol start="2">
 * <li>the exception {@code toBeThrownIfNull} to be thrown by method {@link #get()} if
 * {@code value==null}. If this argument is {@code null}, the exception thrown is a
 * {@link NullValueInOptionalException} with message <i>«null»</i>.</li>
 * </ol>
 * Two of these constructors allow the representation of values that are empty {@link Collection}s
 * or {@link Map}s; they receive:
 * <ol start="3">
 * <li >the string representation {@code toStringIfEmpty} to be returned by the method
 * {@link #toString()} if {@code value.isEmpty()}. If this argument is {@code null}, the string
 * returned is {@code value.toString()}.</li>
 * </ol>
 * One of these constructors receives either {@code toBeThrownIfNull} and {@code toStringIfEmpty} in
 * order to allow the creation of {@link Optional}s that represent collections or maps that may be
 * {@code null} or empty.
 * </p>
 * <p>
 * {@link Optional} instances are immutable.
 * </p>
 * 
 * <p>
 * This is a value-based class; use of identity-sensitive operations (including reference equality
 * (==), identity hash code, or synchronization) on instances of Optional may have unpredictable
 * results and should be avoided.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <T>
 *            The type of this result, if non-{@code null}.
 */
public class Optional< T > {
    
    // INSTANCE FIELDS
    
    /**
     * The value represented.
     */
    private final T value;
    
    /**
     * The exception given in the constructor to be thrown by the method {@link #get()} if
     * {@link #value} is {@code null}. Also, if {@link #value} is {@code null} the method
     * {@link #toString()} returns this exception's message.
     */
    private final Exception toBeThrownIfNull;
    
    /**
     * The string given in the constructor to be returned by the method {@link #toString()} if this
     * value is an empty instance of {@link Collection} or {@link Map} (known via method
     * {@link Collection#isEmpty()} or {@link Map#isEmpty()}).
     */
    private final String toStringIfEmpty;
    
    
    
    // CONSTRUCTORS
    
    /**
     * Creates a new instance of {@link Optional} that represents {@code value} which may be
     * {@code null}.
     * 
     * @param value
     *            The value to be represented by this optional.
     * @param toBeThrownIfNull
     *            The exception to be thrown by the method {@link #get()} if {@code value==null}. If
     *            this argument is {@code null}, the method {@link #get()} will throw a
     *            {@link NullValueInOptionalException} with message <i>«null»</i> if
     *            {@code value==null}.
     */
    public Optional( T value, Exception toBeThrownIfNull ) {
        
        this.value = value;
        this.toBeThrownIfNull =
                (toBeThrownIfNull == null) ? new NullValueInOptionalException( "null" )
                                          : toBeThrownIfNull;
        this.toStringIfEmpty = null;
    }
    
    /**
     * Creates a new instance of {@link Optional} that represents {@code value}, meant to be a
     * {@link Collection} or a {@link Map}, which may be empty.
     * 
     * @param value
     *            The value to be represented by this optional, meant to be an instance of
     *            {@link Collection} or a {@link Map}.
     * @param toStringIfEmpty
     *            The string to be returned by the method {@link #toString()} if
     *            {@code value.is Empty()}. If this argument is {@code null}, the method
     *            {@link #toString()} will return {@code value.toString()} if
     *            {@code value.isEmpty()}.
     */
    public Optional( T value, String toStringIfEmpty ) {
        
        this.value = value;
        this.toBeThrownIfNull = null;
        this.toStringIfEmpty = toStringIfEmpty;
    }
    
    /**
     * Creates a new instance of {@link Optional} that represents {@code value}, meant to be a
     * {@link Collection} or a {@link Map}, which may be {@code null} or empty.
     * 
     * @param value
     *            The value to be represented by this optional, meant to be an instance of
     *            {@link Collection} or a {@link Map}.
     * @param toBeThrownIfNull
     *            The exception to be thrown by the method {@link #get()} if {@code value==null}. If
     *            this argument is {@code null}, the method {@link #get()} will throw a
     *            {@link NullValueInOptionalException} with message <i>«null»</i> if
     *            {@code value==null}.
     * @param toStringIfEmpty
     *            The string to be returned by the method {@link #toString()} if
     *            {@code value.is Empty()}. If this argument is {@code null}, the method
     *            {@link #toString()} will return {@code value.toString()} if
     *            {@code value.isEmpty()}.
     */
    public Optional( T value, Exception toBeThrownIfNull, String toStringIfEmpty )
        throws InvalidArgumentException {
        
        this.value = value;
        this.toBeThrownIfNull =
                (toBeThrownIfNull == null) ? new NullValueInOptionalException( "null" )
                                          : toBeThrownIfNull;
        this.toStringIfEmpty = toStringIfEmpty;
    }
    
    
    
    // PUBLIC METHODS
    
    /**
     * Returns {@code true} if the stored value is {@code null}.
     *
     * @return {@code true} if the stored value is {@code null}; </br> {@code false} otherwise.
     */
    public boolean isNull() {
        
        return value == null;
    }
    
    /**
     * Returns {@code true} if the stored value is an instance of {@link Collection} or {@link Map}
     * and is empty (as defined in method {@link Collection#isEmpty()} or {@link Map#isEmpty()},
     * respectively).
     *
     * @return {@code true} if the stored value is empty; </br> {@code false} if the stored value is
     *         not a collection nor a map or it's not empty.
     */
    @SuppressWarnings( "rawtypes" )
    public boolean isEmpty() {
        
        boolean isEmptyCollection = (value instanceof Collection && ((Collection)value).isEmpty());
        boolean isEmptyMap = (value instanceof Map && ((Map)value).isEmpty());
        return isEmptyCollection || isEmptyMap;
    }
    
    /**
     * Returns {@code true} if the stored value is an instance of {@link Collection} or {@link Map}
     * and the constructor was given a string representation for empty values.
     *
     * @return {@code true} if the stored value is a {@link Collection} or a {@link Map} and the
     *         constructor was given a string representation for empty values; </br> {@code false}
     *         if the stored value is not a collection nor a map or if the constructor wasn't given
     *         a string representation for empty values.
     */
    public boolean hasSpecificStringRepresentationIfEmpty() {
        
        return isEmpty() && toStringIfEmpty != null;
    }
    
    /**
     * Returns the value represented by this {@code Optional} instance, if it is non-{@code null}.
     * If it is {@code null} throws either the exception specified in the constructor or a
     * {@link NullValueInOptionalException} with message <i>«null»</i>.
     *
     * @return The non-{@code null} value represented by this.
     * @throws Exception
     *             If the value represented by this {@link Optional} is {@code null} .
     */
    public T get() throws Exception {
        
        if( value == null ) {
            throw toBeThrownIfNull;
        }
        return value;
    }
    
    
    
    // OVERRIDES OF Object METHODS
    
    /**
     * Indicates whether {@code obj} is "equal to" {@code this}. The object {@code obj} is
     * considered equal to {@code this} if it is also an instance of {@code Optional} and
     * <ul>
     * <li>both instances store {@code null} or
     * <li>the values stored in both instances are equal via method {@code equals()} of class
     * {@code T}.
     * </ul>
     *
     * @param obj
     *            The object to be compared with {@code this}.
     * @return {@code true} if {@code obj} is "equal to" {@code this}; </br> {@code false}
     *         otherwise.
     */
    @Override
    public boolean equals( Object obj ) {
        
        if( this == obj )
            return true;
        
        if( !(obj instanceof Optional) )
            return false;
        
        Optional< ? > other = (Optional< ? >)obj;
        return Objects.equals( value, other.value );
    }
    
    /**
     * Returns the hash code value of the stored value. If this value is {@code null}, its hash code
     * is {@code 0}.
     *
     * @return The hash code value of the stored value.
     */
    @Override
    public int hashCode() {
        
        return Objects.hashCode( value );
    }
    
    /**
     * Returns a string representation of the value represented by this instance of {@link Optional}
     * .
     * <p>
     * If the value is {@code null}, this method returns the message of the exception specified in
     * the instantiation moment (obtained via method {@link Exception#getMessage()}).<br />
     * If the value is an empty collection or map and the constructor received a specified string
     * representation for it, that string will be returned.<br/>
     * Otherwise, this method returns the result of {@code value.toString()}.
     * </p>
     *
     * @return A string representation of the value represented by this instance of {@link Optional}
     *         .
     */
    @Override
    public String toString() {
        
        if( value == null ) {
            return toBeThrownIfNull.getMessage();
        }
        
        if( isEmpty() && toStringIfEmpty != null )
            return toStringIfEmpty;
        
        return value.toString();
    }
    
}
