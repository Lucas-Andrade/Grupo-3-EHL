package main.java.model.airships;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import main.java.exceptions.dataBaseExceptions.DatabaseException;
import main.java.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import main.java.model.Database;
import main.java.model.Element;
import main.java.model.InMemoryDatabase;
import main.java.model.users.User;

/**
 * Class whose instances represent an in-memory database of {@link Airship airships}. </br></br>
 * Implements {@link Database}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryAirshipDatabase extends InMemoryDatabase<Airship> {

	// Instance Fields

	/**
	 * The container {@link Map} where all the {@link Airhip airships} will be stored by
	 * {@link User}: <li>The keys are Strings with the users identifications;</br></br><li>The
	 * values will be a {@link List} of all the {@link Airhip airships} added by the user whose
	 * indentification is stored in the corresponding key.
	 */
	private Map<String, List<Airship>> flightsByUserRegister;

	// Constructor

	/**
	 * Creates an empty {@code InMemoryAirshipDatabase}.
	 */
	public InMemoryAirshipDatabase() {

		flightsByUserRegister = new HashMap<String, List<Airship>>();
	}

	// Overrides

	/**
	 * Override of the implementation of the method {@link InMemoryDatabase#add() add()} from the
	 * {@link Database} Interface. </br></br>Allows an {@link Element element} to be added to this
	 * database by a specific {@link User user} and records that addiction to the {@link map}
	 * {@code flightsByUserRegister}.
	 */
	@Override
	public boolean add(Airship airship, User user) {

		if (super.add(airship, user)) {

			if (flightsByUserRegister.containsKey(user.getIdentification()))
				flightsByUserRegister.get(user.getIdentification()).add(airship);

			else {

				List<Airship> newListForNewUser = new ArrayList<>();
				newListForNewUser.add(airship);
				flightsByUserRegister.put(user.getIdentification(), newListForNewUser);
			}

			return true;
		}

		return false;
	}

	/**
	 * Override of the implementation of the method {@link InMemoryDatabase#remove() remove()} from
	 * the {@link Database} Interface. </br></br>Allows an {@link Airship airship} to be removed
	 * from this database by a specific {@link User user} and deletes that record from the
	 * {@code flightsByUserRegister} field.
	 * @throws DatabaseException 
	 */
	@Override
	public boolean remove(Airship airship, User user) throws DatabaseException {

		if (super.remove(airship, user)) {

			flightsByUserRegister.values().remove(airship);
			return true;
		}

		return false;
	}

	// Get Methods Used By The Commands

	/**
	 * Auxiliar method that will allow a {@link List} containing all the airships added by a
	 * specified user to be obtained.
	 * 
	 * @param username
	 *            - The user's identification.
	 * 
	 * @return A {@link List} containing all the airships added by a specified user.
	 * 
	 * @throws NoSuchElementInDatabaseException
	 *             if no {@code Airship} was added by the given {@code User}.
	 */
	public List<Airship> getAirshipsOfUser(String username) throws NoSuchElementInDatabaseException {

		if (!flightsByUserRegister.containsKey(username))
			throw new NoSuchElementInDatabaseException("No Airship was added by the given User");

		return flightsByUserRegister.get(username);
	}

	/**
	 * Auxiliar method that will return a {@link List} containing all the airships that verify a
	 * certain given criteria.
	 * 
	 * @param criteria
	 *            - The specific validation criteria that has to be verified by the airships
	 *            contained in the database in order to be selected.
	 * 
	 * @return A {@link List} containing all the airships that verify the given criteria.
	 */
	public List<Airship> getAirshipsThat(Predicate<Airship> criteria) {

		List<Airship> selectedAirships = new ArrayList<>();

		for (Airship airship : getAll().values())
			if (criteria.test(airship))
				selectedAirships.add(airship);

		return selectedAirships;
	}
}