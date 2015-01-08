package main.java.cli.model;

import main.java.cli.Optional;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.DatabaseException;
import main.java.cli.model.users.User;

/**
 * Interface whose instances represent databases of elements that have an individual identification.
 *
 * @param <T>
 *            The type of the elements stored in the database.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface Database<T extends Element> {

	/**
	 * Returns this database's name.
	 * 
	 * @return This database's name.
	 */
	public String getDatabaseName();

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
	public boolean add(T element, User user) throws InvalidArgumentException;

	/**
	 * Removes the {@code Element} with the given{@code identification} from this database.
	 * 
	 * @param identification
	 *            - The identification of the element to be removed.
	 * 
	 * @return {@code true} if the element was successfully removed and {@code false} otherwise.
	 * 
	 * @throws DatabaseException
	 *             If performing a forbidden operation in a database.
	 * @throws InvalidArgumentException
	 *             If {@code identification} is null.
	 */
	public boolean removeByIdentification(String identification) throws DatabaseException,
			InvalidArgumentException;

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
	public Optional<T> getElementByIdentification(String identification)
			throws InvalidArgumentException;

	/**
	 * Returns an {@link Iterable} of all elements stored in this database. This result is wrapped
	 * in an {@link Optional} instance.
	 * 
	 * @return An {@link Optional} {@link Iterable} of all elements stored in this database.
	 * 
	 * @see Optional
	 */
	public Optional<Iterable<T>> getAllElements();
}