package main.java.cli;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import main.java.cli.exceptions.InvalidArgumentException;

/**
 * Class whose instances represent values returned by methods. This class is inspired by
 * {@link java.util.Optional} class's structure and functionality, with some twists: its purpose is
 * not only to wrap results that might be {@code null} and prevent unexpected
 * {@link NullPointerException}s that might come from trying to use them (in this version of
 * Optional, one may even choose the exception to be thrown by method {@link #get()}), but also to
 * wrap results that might be empty collections for which one would like to specify its string
 * representation.
 * <p>
 * If the value represented by a certain <code>Optional{@literal <}T></code> instance is
 * {@code null}, when called by that instance, the method {@link #get()} throws an exception and the
 * method {@link #toString()} returns that same exception's message. The exception to be thrown is
 * chosen in the instantiation moment.
 * </p>
 * <p>
 * If the value represented by a certain <code>Optional{@literal <}T></code> instance is an empty
 * collection or an empty map (meaning {@code T} implements {@link Collection} or implements
 * {@link Map} and value{@code .isEmpty()} is {@code true}), when called by that instance, the
 * method {@link #toString()} may return a specified {@link String}. This string is chosen in the
 * instantiation moment.
 * </p>
 * <p>
 * There are two constructors. Both constructors receive the value to be represented and the
 * exception to be thrown if that value is {@code null}. The existence of two constructors allows
 * <code>Optional{@literal <}T></code> instances that represent empty collections or maps to have
 * one of two different string representations:
 * <ul>
 * <li><b>one of the constructors can only be used when the type {@code T} implements
 * {@link Collection} or {@link Map}</b> and it also receives as argument a string representation of
 * the value if it is an empty collection or an empty map.</li>
 * <li>the string representation of an instance created using the other constructor, whether it
 * represents an empty collection or other non- {@code null} value is the result of calling
 * {@code value.toString()}.</li>
 * </ul>
 * 
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
public class Optional<T> {

	// INSTANCE FIELDS

	/**
	 * The value represented.
	 */
	private final T value;

	/**
	 * The exception to be thrown by the method {@link #get()} if {@link #value} is {@code null}.
	 * Also, if {@link #value} is {@code null} the method {@link #toString()} returns this
	 * exception's message.
	 */
	private final Exception toBeThrownIfNull;

	/**
	 * The string to be returned by the method {@link #toString()} if this value is an instance of
	 * {@link Collection} or {@link Map} which is empty (known via method
	 * {@link Collection#isEmpty()} or {@link Map#isEmpty()}) and this string is non-{@code null}
	 * (it is non-{@code null} if this instance was created using the second constructor).
	 */
	private final String toStringIfIsEmptyCollectionOrMap;

	// CONSTRUCTORS

	/**
	 * Creates a new instance of {@link Optional} that represents {@code value}.
	 * 
	 * @param value
	 *            The value to be represented by this optional.
	 * @param toBeThrownIfNull
	 *            The exception to be thrown by the method {@link #get()} if {@code value==null}.
	 * @throws InvalidArgumentException
	 *             If {@code toBeThrownIfNull==null}.
	 */
	public Optional(T value, Exception toBeThrownIfNull) throws InvalidArgumentException {

		if (toBeThrownIfNull == null)
			throw new InvalidArgumentException(
					"Cannot instantiate an Optional with null exception.");

		this.value = value;
		this.toBeThrownIfNull = toBeThrownIfNull;
		this.toStringIfIsEmptyCollectionOrMap = null;
	}

	/**
	 * Creates a new instance of {@link Optional} that represents {@code value}, only if {@code T}
	 * implements {@link Collection} or implements {@link Map} .
	 * 
	 * @param collectionValue
	 *            The value (must be an instance of {@link Collection} or {@link Map}) to be
	 *            represented by this optional.
	 * @param toBeThrownIfNull
	 *            The exception to be thrown by the method {@link #get()} if {@code value==null}.
	 * @param toStringIfIsEmptyCollectionOrMap
	 *            The string to be returned by the method {@link #toString()} if {@code value} is
	 *            empty (known via method {@link Collection#isEmpty()} or {@link Map#isEmpty()}).
	 * @throws InvalidArgumentException
	 *             If {@code value} is not an instance of {@link Collection}; </br>if
	 *             {@code toBeThrownIfNull} or {@code toBeThrownIsEmptyCollection} are {@code null}.
	 */
	public Optional(T collectionValue, Exception toBeThrownIfNull,
			String toStringIfIsEmptyCollectionOrMap) throws InvalidArgumentException {

		if (!(collectionValue instanceof Collection || collectionValue instanceof Map))
			throw new InvalidArgumentException("Cannot use this constructor, a "
					+ collectionValue.getClass().getSimpleName() + " is not a Collection.");

		if (toBeThrownIfNull == null)
			throw new InvalidArgumentException(
					"Cannot instantiate an Optional with null exceptions.");

		if (toStringIfIsEmptyCollectionOrMap == null)
			throw new InvalidArgumentException(
					"Invalid null string representation for empty collections.");

		this.value = collectionValue;
		this.toBeThrownIfNull = toBeThrownIfNull;
		this.toStringIfIsEmptyCollectionOrMap = toStringIfIsEmptyCollectionOrMap;
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
	 * Returns {@code true} if the stored value is an instance of {@link Collection} and is empty
	 * (as defined in method {@link Collection#isEmpty()}).
	 *
	 * @return {@code true} if the stored value is an instance of collection and is empty (as
	 *         defined in method {@link Collection#isEmpty()}); </br> {@code false} if the stored
	 *         value is not a collection or it's a non-empty collection.
	 */
	@SuppressWarnings ("rawtypes")
	public boolean isEmptyCollectionOrEmptyMap() {

		boolean isEmptyCollection = (value instanceof Collection && ((Collection) value).isEmpty());
		boolean isEmptyMap = (value instanceof Map && ((Map) value).isEmpty());
		
		return isEmptyCollection || isEmptyMap;
	}

	/**
	 * Returns {@code true} if the stored value is an instance of {@link Collection} and is empty
	 * (as defined in method {@link Collection#isEmpty()}).
	 *
	 * @return {@code true} if the stored value is an instance of collection and is empty (as
	 *         defined in method {@link Collection#isEmpty()}); </br> {@code false} if the stored
	 *         value is not a collection or it's a non-empty collection.
	 */
	public boolean hasSpecifiedStringRepresentationIfEmptyCollectionOrMap() {

		return toStringIfIsEmptyCollectionOrMap != null;
	}

	/**
	 * Returns the value represented by this {@code Optional} instance, if it is non-{@code null}.
	 * If it is {@code null} throws the exception specified in the constructor.
	 *
	 * @return The non-{@code null} value represented by this.
	 * @throws Exception
	 *             If the value represented by this is {@code null}.
	 */
	public T get() throws Exception {

		if (value == null) {
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
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (!(obj instanceof Optional))
			return false;

		Optional<?> other = (Optional<?>) obj;
		return Objects.equals(value, other.value);
	}

	/**
	 * Returns the hash code value of the stored value. If this value is {@code null}, its hash code
	 * is {@code 0}.
	 *
	 * @return The hash code value of the stored value.
	 */
	@Override
	public int hashCode() {

		return Objects.hashCode(value);
	}

	/**
	 * Returns a string representation of the value represented by this instance of {@link Optional}
	 * .
	 * <p>
	 * If the value is {@code null}, this method returns the message of the exception specified in
	 * the instantiation moment (obtained via method {@link Exception#getMessage()}).<br />
	 * If the value is an empty collection and there was received a specified string representation
	 * for it in the constructor, that's what will be returned.<br/>
	 * Otherwise, this method returns the result of {@code value.toString()}.
	 * </p>
	 *
	 * @return A string representation of the value represented by this instance of {@link Optional}
	 *         .
	 */
	@Override
	public String toString() {

		if (value == null) {
			return toBeThrownIfNull.getMessage();
		}

		if (isEmptyCollectionOrEmptyMap() && toStringIfIsEmptyCollectionOrMap != null)
			return toStringIfIsEmptyCollectionOrMap;

		return value.toString();
	}
}