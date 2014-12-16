package main.java.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import main.java.model.users.User;

/**
 * Abstract class whose instances represent in-memory databases of {@link Element} that have an
 * identification. An in-memory database exists only during the runtime. </br></br> Implements
 * {@link Database}.
 *
 * @param <T>
 *            The type of the elements stored in the database.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class InMemoryDatabase<T extends Element> implements Database<T> {

	// Instance Fields

	/**
	 * The container {@link Map} where the {@link Element elements} will be stored: <li>The keys are
	 * the elements' identifications - see {@link Element#getIdentification() getIdentification()}
	 * method; <li>The values are the elements.
	 */
	private Map<String, T> database;

	// Constructor

	/**
	 * Constructor used by the subclasses of this class to creates an empty database.
	 */
	public InMemoryDatabase() {

		this.database = new HashMap<String, T>();
	}

	// Implemented Methods

	/**
	 * Implementation of the method {@link Database#add() add()} from the {@link Database}
	 * Interface. </br></br>Allows an {@link Element element} to be added to this database by a
	 * specific {@link User user}.
	 */
	@Override
	public boolean add(T element, User user) {

		if (element == null || user == null)
			throw new IllegalArgumentException();

		if (database.containsKey(element.getIdentification()))
			return false;

		database.put(element.getIdentification(), element);
		return true;
	}

	/**
	 * Implementation of the method {@link Database#getElementByIdentification()
	 * getElementByIdentification()} from the {@link Database} Interface. </br></br>Returns the
	 * element of this database whose {@code identification} is the same as the one given as
	 * parameter.
	 */
	@Override
	public T getElementByIdentification(String identification) {

		return database.get(identification);
	}

	/**
	 * Returns an unmodifiable view of the map that represents this database.This map is "read-only"
	 * and any attempts to modify it, whether directly or via its collection views, will result in
	 * an {@link UnsupportedOperationException}.
	 * 
	 * @return A "read-only" view of this database.
	 */
	@Override
	public Map<String, T> getAll() {

		return Collections.unmodifiableMap(database);
	}
}