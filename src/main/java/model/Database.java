package main.java.model;

import java.util.Map;

import main.java.exceptions.dataBaseExceptions.DatabaseException;
import main.java.model.users.User;

/**
 * Interface that imposes a contract to be implemented databases of elements that have an individual
 * identification.
 * 
 * @param <T>
 *            The type of the elements that can be stored in each the database.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public interface Database<T extends Element> {

	/**
	 * Allows an {@link Element element} to be added to this database by a specific {@link User
	 * user}.
	 * 
	 * @param element
	 *            - the element to be added.
	 * @param user
	 *            - the user that will add the element.
	 * 
	 * @return {@code true} if the element is added and {@code false} otherwise.
	 */
	public boolean add(T element, User user);

	/**
	 * Allows an {@link Element element} to be removed from this database by a specific {@link User
	 * user}.
	 * 
	 * @param element
	 *            - the element to be removed.
	 * @param user
	 *            - the user that will remove the element.
	 * 
	 * @return {@code true} if the element is removed and {@code false} otherwise.
	 */
	public boolean remove(T element, User user)  throws DatabaseException;

	/**
	 * Returns the element of this database whose {@code identification} is the same as the one
	 * given as parameter.
	 * 
	 * @param identification
	 *            - The element's identification.
	 * 
	 * @return The element of this database whith the corresponding {@code identification};
	 *         </br></br> {@code null} if there's no such element in this database.
	 */
	public Element getElementByIdentification(String identification);

	/**
	 * @return A map whose values are the elements stored in this database and whose keys are the
	 *         corresponding identifications.
	 */
	public Map<String, T> getAll();
}