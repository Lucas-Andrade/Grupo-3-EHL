package airtrafficcontrol.app.appforcommandline.model.airships;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import airtrafficcontrol.app.appforcommandline.model.Database;
import airtrafficcontrol.app.appforcommandline.model.Element;
import airtrafficcontrol.app.appforcommandline.model.InMemoryDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

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
	 * indentification is stored in the correspondeing key.
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
	 * database by a specific {@link User user} records that addiction in the field
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

	// Get Methods Used By The Commands

	/**
	 * Auxiliar method to be used by the application commands that will allow a {@link List}
	 * containing all the airships added by a specified user to be obtained.
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
	 * Auxiliar method to be used by the application commands that will allow a {@link List}
	 * containing all the airships that verify a specific criteria to be obtained.
	 * 
	 * @param criteria
	 *            - The specific validation criteria that have to be verified by the airships
	 *            contained in the database in order to be selected.
	 * 
	 * @return A {@link List} containing all the airships that verify a specific criteria.
	 */
	public List<Airship> getAirshipsThat(Predicate<Airship> criteria) {

		List<Airship> selectedAirships = new ArrayList<>();

		for (Airship airship : getAll().values())
			if (criteria.test(airship))
				selectedAirships.add(airship);

		return selectedAirships;
	}

	/**
	 * Auxiliar method to be used by the application commands that will allow a {@link List}
	 * containing all the airships that are outside their pre-established {@link AirCorridor
	 * altitude corridor} to be obtained.
	 * 
	 * @return A {@link List} containing all the airships that are outside their pre-established
	 *         {@link AirCorridor altitude corridor}.
	 */
	public List<Airship> reportTransgressions() {

		List<Airship> transgressingAirships = new ArrayList<>();

		for (Airship airship : getAll().values()) {
			if (airship.isTransgressing())
				transgressingAirships.add(airship);
		}

		return transgressingAirships;
	}

	/**
	 * Auxiliar method to be used by the application commands to verify in an airship is outside its
	 * pre-established {@link AirCorridor altitude corridor}.
	 * 
	 * @param flightId
	 *            - The {@link Airship#flightId flightId} of the {@link Airship airship} to be
	 *            evaluated.
	 * 
	 * @return {@code true} if the {@code Airship} with the given {@code flightId} is in the
	 *         correct {@code altitude corridor} or false otherwhise.
	 * 
	 * @throws NoSuchElementInDatabaseException
	 *             If no {@code Airship} exist in the database with the given {@code flightId.}
	 */
	public boolean checkIfThisAirshipIsTransgressing(String flightId)
			throws NoSuchElementInDatabaseException {

		Airship theAirship = getElementByIdentification(flightId);

		if (theAirship == null)
			throw new NoSuchElementInDatabaseException(
					"The Airship with the given ID doesn't exist in the database");

		if (theAirship.isTransgressing())
			return true;

		return false;
	}
}