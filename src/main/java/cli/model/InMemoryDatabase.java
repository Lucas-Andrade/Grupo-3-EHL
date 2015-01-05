package main.java.cli.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import main.java.cli.Optional;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.DatabaseException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.model.users.User;

/**
 * Abstract class whose instances represent in-memory databases of {@link Element} that have an
 * identification. An in-memory database exists only during the runtime. </br>Implements
 * {@link Database}.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <T>
 *            The type of the elements stored in the database.
 */
public abstract class InMemoryDatabase<T extends Element> implements Database<T> {

	// INSTANCE FIELDS

	/**
	 * This database's name
	 */
	private String databaseName;

	/**
	 * The container of {@link Element}s, where the: <li>keys are the elements' identifications -
	 * see {@link Element#getIdentification()}; <li>values are the elements.
	 */
	private Map<String, T> database;

	// CONSTRUCTOR
	
	/**
	 * Creates an empty database with name {@code databaseName}. Constructor used by the subclasses
	 * of this class to create an empty database.
	 * 
	 * @throws InvalidArgumentException
	 *             If {@code databaseName==null}.
	 */
	public InMemoryDatabase(String databaseName) throws InvalidArgumentException {

		if (databaseName == null)
			throw new InvalidArgumentException("Cannot instantiate database with null name.");

		this.databaseName = databaseName;
		this.database = new HashMap<String, T>();
	}

	// IMPLEMENTATION OF METHODS INHERITED FROM Database INTERFACE

	/**
	 * Returns this database's name.
	 * 
	 * @return This database's name.
	 */
	public String getDatabaseName() {

		return databaseName;
	}

	/**
	 * Stores the {@link Element} {@code element} in this database, added by a specific {@link User}
	 * .
	 * 
	 * @param element
	 *            The element to be added to this database.
	 * @param user
	 *            The user who added this element to the database.
	 * @return {@code true} if the element was successfully added;</br> {@code false} otherwise.
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
	 * @param identification
	 *            The identification of the element to be removed.
	 * @return {@code true} if the element was successfully removed; </br> {@code false} otherwise.
	 * @throws DatabaseException
	 *             When trying to perform an forbidden operation in a database (e.g removing the
	 *             user "MASTER" from an {@link InMemoryUserDatabase}).
	 * @throws InvalidArgumentException
	 *             If {@code identification==null}.
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
	 * Returns the element of this database whose identification is {@code identification} (uses
	 * method {@link Element#getIdentification()}).
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()}
	 * throws a {@link NoSuchElementInDatabaseException} with the message <i>«{@code identification}
	 * not found in {@link #getDatabaseName()}»</i> if the result is {@code null}.
	 * </p>
	 * 
	 * @param identification
	 *            The element's identification.
	 * @return The element of this database whose identification is {@code identification}; </br>
	 *         {@code null} if there's no such element in this database.
	 * @throws InvalidArgumentException
	 *             If {@code identification==null}.
	 * @see Optional
	 */
	@Override
	public Optional<T> getElementByIdentification(String identification)
			throws InvalidArgumentException {

		if (identification == null)
			throw new InvalidArgumentException("Cannot get element with null identification.");
		
		try {
			return new Optional<T>(database.get(identification),
					new NoSuchElementInDatabaseException(identification, databaseName));
		
		} catch (InvalidArgumentException e) { // never happens because the Optional was
												// instantiated with a non null
												// NoSuchElementInDatabaseException
			return null;
		}
	}

	/**
	 * Returns an {@link Iterable} of all elements stored in this database.This iterable is
	 * "read-only"; attempts to modify it result in an {@link UnsupportedOperationException}.
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()}
	 * throws {@link InternalErrorException} if the result is {@code null} (since this is never
	 * supposed to happen) and whose method {@link Optional#toString()} returns the string <i>«
	 * {@code getDatabaseName()} is empty.»</i>.
	 * </p>
	 * 
	 * @return An {@link Iterable} of all elements stored in this database.
	 * @see Optional
	 */
	public Optional<Iterable<T>> getAllElements() {

		try {
			return new Optional<Iterable<T>>(Collections.unmodifiableCollection(database.values()),
					new InternalErrorException(), getDatabaseName() + " is empty.");
			
		} catch (InvalidArgumentException e) { // never happens because the InternalErrorException
												// is not null
			return null;
		}
	}

	// OTHER PUBLIC METHODS

	/**
	 * Returns a map whose values are the elements stored in this database and whose keys are the
	 * corresponding identifications (obtained by the method {@link Element#getIdentification()}
	 * ).This map is "read-only"; attempts to modify the returned map, whether direct or via its
	 * collection views, result in an {@link UnsupportedOperationException}.
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()}
	 * throws {@link InternalErrorException} if the result is {@code null} (since this is never
	 * supposed to happen) and whose method {@link Optional#toString()} returns the string <i>«
	 * {@code getDatabaseName()} is empty.»</i>.
	 * </p>
	 * 
	 * @return A "read-only" view of this database.
	 */
	public Optional<Map<String, T>> getAll() {

		try {
			return new Optional<Map<String, T>>(Collections.unmodifiableMap(database),
					new InternalErrorException(), databaseName + " is empty.");
			
		} catch (InvalidArgumentException e) { // never happens because the exception is not null
			
			return null;
		}
	}

	/**
	 * Returns an {@link Iterable} with the elements stored in this database that satisfy the
	 * specified {@code criteria}.
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()}
	 * throws {@link InternalErrorException} if the result is {@code null} (since this is never
	 * supposed to happen) and whose method {@link Optional#toString()} returns the string <i>«No
	 * such element in {@code getDatabaseName()}»</i>.
	 * </p>
	 * 
	 * @param criteria
	 *            The criteria to be satisfied by all elements returned.
	 * @return An {@link Iterable} with the elements stored in this database that satisfy the
	 *         specified {@code criteria}.
	 * @throws InvalidArgumentException
	 *             If {@code criteria} is {@code null}.
	 */
	public Optional<Iterable<T>> getAllElementsThat(Predicate<T> criteria)
			throws InvalidArgumentException {

		if (criteria == null)
			throw new InvalidArgumentException("Cannot use a null criteria to get elements from "
					+ databaseName);

		try {
			List<T> selectedElements = new ArrayList<>();

			for (T element : getAll().get().values())
				if (criteria.test(element))
					selectedElements.add(element);

			return new Optional<Iterable<T>>(selectedElements, new InternalErrorException(),
					"No such element in " + databaseName);
			
		} catch (Exception e) { // never happens because
								// 1. getAll() is never null and
								// 2. new InternalErrorException() is also not null
			return null;
		}
	}
}