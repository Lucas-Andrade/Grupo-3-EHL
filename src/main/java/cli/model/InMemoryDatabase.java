package main.java.cli.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import main.java.cli.model.users.User;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;

/**
 * Abstract class whose instances represent in-memory databases of {@link Element} that have an
 * identification. An in-memory database exists only during the runtime. </br></br> Implements
 * {@link Database}.
 * 
 * @param <T>
 *            - The type of the elements stored in the database.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class InMemoryDatabase<T extends Element> implements Database<T> {

	// INSTANCE FIELDS

	/**
	 * This database's name
	 */
	private String databaseName;

	/**
	 * The {@link Map} container of that will represent a database of {@link #Element elements}.
	 * </br></br> The Map keys are the elements identifications and the values are the elements
	 * themselves.
	 */
	private Map<String, T> database;

	// CONSTRUCTOR

	/**
	 * Creates an empty database with name {@code databaseName}. Constructor used by the subclasses
	 * of this class to create an empty database.
	 * 
	 * @throws InvalidArgumentException
	 *             If the {@code databaseName} is null.
	 */
	public InMemoryDatabase(String databaseName) throws InvalidArgumentException {

		if (databaseName == null)
			throw new InvalidArgumentException("Cannot instantiate database with null name.");

		this.databaseName = databaseName;
		this.database = new HashMap<String, T>();
	}

	// IMPLEMENTATION OF METHODS INHERITED FROM THE Database INTERFACE

	/**
	 * Returns this database's name.
	 * 
	 * @return This database's name.
	 */
	public String getDatabaseName() {

		return databaseName;
	}

	/**
	 * Stores an {@link Element} in this database, added by a specific {@link User}. Implementation
	 * of the method {@link Database#add() add()} from the {@link Database} Interface.
	 * 
	 * If an {@code Element} with the same {@code identification} is already in {@code this}
	 * database the {@code Element} will not be added.
	 * 
	 * @param element
	 *            - The element to be added to this database.
	 * @param user
	 *            - The user who added this element to the database.
	 * 
	 * @return {@code true} if the element was successfully added and {@code false} otherwise.
	 * 
	 * @throws InvalidArgumentException
	 *             If either {@code element} or {@code user} are {@code null}.
	 */
	@Override
	public boolean add(T element, User user) throws InvalidArgumentException {

		if (element == null)
			throw new InvalidArgumentException("Cannot add null elements to database.");

		if (user == null)
			throw new InvalidArgumentException("Elements cannot be added by a null user.");

		if (database.containsKey(element.getIdentification()))
			return false;

		database.put(element.getIdentification(), element);
		return true;
	}

	/**
	 * Removes an element from this database. Implementation of the method {@link Database#remove()
	 * remove()} from the {@link Database} Interface.
	 * 
	 * If an {@code Element} with the given {@code identification} doesn't exist in {@code this}
	 * database no {@code Element} will be removed.
	 * 
	 * @param identification
	 *            - The identification of the element to be removed.
	 * 
	 * @return {@code true} if the element was successfully removed and {@code false} otherwise.
	 * 
	 * @throws DatabaseException
	 *             When trying to perform an forbidden operation in a database (e.g removing the
	 *             user "MASTER" from an {@link InMemoryUserDatabase}).
	 * @throws InvalidArgumentException
	 *             If {@code identification} is null.
	 */
	@Override
	public boolean removeByIdentification(String identification) throws DatabaseException,
			InvalidArgumentException {

		if (identification == null)
			throw new InvalidArgumentException("Cannot remove element with null identification.");

		if (!database.containsKey(identification))
			return false;

		database.remove(identification);
		return true;
	}

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
	public Optional<T> getElementByIdentification(String identification)
			throws InvalidArgumentException {

		if (identification == null)
			throw new InvalidArgumentException("Cannot get element with null identification.");

		return new Optional<T>(database.get(identification), new NoSuchElementInDatabaseException(
				identification, databaseName));
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
	public Optional<Iterable<T>> getAllElements() {

		return new Optional<Iterable<T>>(Collections.unmodifiableCollection(database.values()),
				getDatabaseName() + " is empty.");
	}

	// OTHER PUBLIC METHODS

	/**
	 * Returns a map whose values are the elements stored in this database and whose keys are the
	 * corresponding identifications.
	 * <p>
	 * This map is "read-only"! Attempts to modify the returned map, whether direct or via its
	 * collection views, result in an {@link UnsupportedOperationException}.
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()
	 * get()} throws {@link InternalErrorException} if the result is {@code null} (since this is
	 * never supposed to happen) and whose method {@link Optional#toString() toString()} returns the
	 * string <i>« {@code getDatabaseName()} is empty.»</i>.
	 * </p>
	 * 
	 * @return A "read-only" view of this database.
	 * 
	 * @see Optional
	 */
	public Optional<Map<String, T>> getAll() {

		return new Optional<Map<String, T>>(Collections.unmodifiableMap(database), databaseName
				+ " is empty.");
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
	public Optional<Iterable<T>> getAllElementsThat(Predicate<T> criteria)
			throws InvalidArgumentException {

		if (criteria == null)
			throw new InvalidArgumentException("Cannot use a null criteria to get elements from "
					+ databaseName);

		List<T> selectedElements = new ArrayList<>();

		try {
			for (T element : getAllElements().get())
				if (criteria.test(element))
					selectedElements.add(element);

		} catch (Exception e) { // never happens because getAll() is never null
		}

		return new Optional<Iterable<T>>(selectedElements, "No such element in " + databaseName);
	}
}