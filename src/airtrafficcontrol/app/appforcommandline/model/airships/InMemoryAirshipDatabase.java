package airtrafficcontrol.app.appforcommandline.model.airships;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import airtrafficcontrol.app.appforcommandline.model.InMemoryDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class InMemoryAirshipDatabase extends InMemoryDatabase<Airship> {

	/**
	 * The register of the {@link Airship airships} added by each {@link User user}. Each
	 * {@link User#username username} is mapped to a {@link List} of the {@link Airship#flightId
	 * flightId}s of the {@link Airship airships} he added to this database..
	 */
	private Map<String, List<Airship>> flightsByUserRegister;

	/**
	 * Creates an empty {@link InMemoryAirshipDatabase in-memory airships database} with no
	 * {@link Airship airships} .
	 */
	public InMemoryAirshipDatabase() {

		flightsByUserRegister = new HashMap<String, List<Airship>>();
	}

	/**
	 * Returns a list with the {@link Airship#flightId flightId}s of {@link Airship airships} stored
	 * in this database that were added by the {@link User} with the {@link User#username username}
	 * {@code username}.
	 * 
	 * @param username
	 * @return A list with the {@link Airship#flightId flightId}'s of {@link Airship airships}
	 *         stored in this database that were added by the {@link User} with
	 *         {@link User#username} {@code username}.
	 */
	public List<Airship> getAirshipsOfUser(String username) {

		return flightsByUserRegister.get(username);
	}

	/**
	 * Returns a list with the {@link Airship#flightId flightId}s of {@link Airship airships} stored
	 * in this database that satisfy the specified {@code criteria}.
	 * 
	 * @param criteria
	 * @return
	 */
	public List<Airship> getAirshipsThat(Predicate<Airship> criteria) {

		List<Airship> selectedAirships = new ArrayList<>();

		for (Airship airship : getAll().values())
			if (criteria.test(airship))
				selectedAirships.add(airship);

		return selectedAirships;
	}

	/**
	 * Returns a list with the {@link Airship#flightId flightId}s of {@link Airship airships} stored
	 * in this database that are out of their pre-established {@link AirCorridor altitude corridor}.
	 * 
	 * @return A list with the {@link Airship#flightId flightId}s of {@link Airship airships} stored
	 *         in this database that are out of the pre-established {@link AirCorridor altitude
	 *         corridor}s.
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
	 * Checks whether the {@link Airship airship} with {@link Airship#flightId flightId}
	 * {@code flightId} is out of its pre-established {@link AirCorridor altitude corridor}.
	 * 
	 * @param flightId
	 *            The {@link Airship#flightId flightId} of the {@link Airship airship} to be
	 *            evaluated.
	 * @return {@code true} if the {@link Airship airship} with {@link Airship#flightId flightId}
	 *         {@code flightId} is in the correct {@link AirCorridor altitude corridor}; </br>
	 *         {@code false} if the {@link Airship airship} with {@link Airship#flightId flightId}
	 *         {@code flightId} is out of its pre-established {@link AirCorridor altitude corridor}
	 *         or is not in this database.
	 * @throws NoSuchElementInDatabaseException 
	 */
	public boolean checkIfThisAirshipIsInCorridor(String flightId) throws NoSuchElementInDatabaseException {

		Airship theAirship = getElementByIdentification(flightId);
		
		if (theAirship == null)
			throw new NoSuchElementInDatabaseException("The Airship with the given ID doesn't exist in the database");
		
		if (theAirship.isTransgressing())
			return true;
		
		return false;
	}

	/**
	 * Stores the {@link Airship airship} {@code airship} in this database, added by the
	 * {@link User user} {@code userWhoIsAddingThisAirship}.
	 * 
	 * @param element
	 *            The element to be added to this database.
	 * @param userWhoIsAddingThisAirship
	 *            The user who added this element to the database.
	 * @return {@code true} if the element was successfully added;</br> {@code false} otherwise.
	 */
	@Override
	public boolean add(Airship airship, User user) {

		if (super.add(airship, user)) {

			if (flightsByUserRegister.containsKey(user.getUsername()))
				flightsByUserRegister.get(user.getUsername()).add(airship);

			else {

				List<Airship> newListForNewUser = new ArrayList<>();
				newListForNewUser.add(airship);
				flightsByUserRegister.put(user.getUsername(), newListForNewUser);
			}

			return true;
		}

		return false;
	}
}
