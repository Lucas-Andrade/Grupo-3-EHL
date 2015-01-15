package main.java.domain.model.airships;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import main.java.domain.model.Database;
import main.java.domain.model.InMemoryDatabase;
import main.java.domain.model.users.User;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;

/**
 * Class whose instances represent in-memory databases of {@link Airship}s. An in-memory database
 * exists only during the runtime. </br></br> Implements {@link Database}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class InMemoryAirshipsDatabase extends InMemoryDatabase<Airship> {

	// Instance Field

	/**
	 * The container {@link Map} where all the {@link Airship}s will be stored by {@link User}:
	 * <ul>
	 * <li>The keys are Strings with the users identifications;</li>
	 * <li>The values will be a {@link List} of all the {@link Airship}s added by the user whose
	 * identification is stored in the corresponding key.</li>
	 * </ul>
	 */
	private Map<String, List<Airship>> flightsByUserRegister;

	// Constructor

	/**
	 * Creates an empty {@link InMemoryAirshipDatabase} with the name {@code databaseName}.
	 * 
	 * @param databaseName
	 *            - This database's name.
	 * 
	 * @throws InvalidArgumentException
	 *             If {@code databaseName} is null.
	 */
	public InMemoryAirshipsDatabase(String databaseName) throws InvalidArgumentException {

		super(databaseName);

		flightsByUserRegister = new HashMap<String, List<Airship>>();
	}

	// OVERRIDE OF METHODS InMemoryDatabase

	/**
	 * Stores an {@link #Airship} in this database, added by a specific {@link User}
	 * 
	 * If there is another airship with the same {@code flightId} in {@code this} database the
	 * airship will not be added.
	 * 
	 * @param airship
	 *            - The airship to be added to this database.
	 * @param userWhoIsAddingThisAirship
	 *            - The user who is adding the airship to the database.
	 * 
	 * @return {@code true} if the airship was successfully added and {@code false} otherwhise.
	 * 
	 * @throws InvalidArgumentException
	 *             If either {@code airship} or {@code user} are {@code null}.
	 */
	@Override
	public boolean add(Airship airship, User user) throws InvalidArgumentException {

		if (super.add(airship, user)) {
			addAirshipToItsUsersListOfAirships(airship, user);
			return true;
		}

		return false;
	}

	/**
	 * Removes the {@code airship} with the given {@code flightId} from this database.
	 * 
	 * If an {@code airship} with the given {@code flightId} doesn't exist in {@code this} database
	 * no {@code airship} will be removed.
	 * 
	 * @param flightId
	 *            - The flightId of the airship to be removed.
	 * 
	 * @return {@code true} if the airship was successfully removed and {@code false} otherwise.
	 * 
	 * @throws DatabaseException
	 *             When trying to perform an forbidden operation in a database.
	 * @throws InvalidArgumentException
	 *             If {@code flightId} is null.
	 */
	@Override
	public boolean removeByIdentification(String flightId) throws InvalidArgumentException,
			DatabaseException {

		super.removeByIdentification(flightId);

		removeAirshipFromItsUsersListOfAirships(flightId);
		
		return true;
	}

	// OTHER PUBLIC METHODS

	/**
	 * Returns a list with the {@link #Airship airships} stored in this database that were added by
	 * the {@link User} with the given {@code username}.
	 * 
	 * It there isn't such a list the method will return an empty list.
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()
	 * get()} throws {@link InternalErrorException} if the result is {@code null} (since this is
	 * never supposed to happen) and whose method {@link Optional#toString() toString()} returns the
	 * string <i>«No airship added by {@code username}.»</i>.
	 * </p>
	 * 
	 * @param username
	 *            - The username of the user whose airships are to get.
	 * 
	 * @return A list of {@link #Airship airships} stored in this database that were added by the
	 *         {@link User} with the given {@code username}.
	 * 
	 * @see Optional
	 */
	public Optional<Iterable<Airship>> getAirshipsOfUser(String username) {

		List<Airship> list = flightsByUserRegister.get(username);

		if (list == null)
			list = new ArrayList<>();

		return new Optional<Iterable<Airship>>(list, "No airship added by " + username);
	}

	/**
	 * Returns an {@link Iterable} specific number of {@link #Airship airships} (determined by the
	 * given parameter {@code nrOfAirshipsToGet}) stored in this database that are closer to the
	 * given {@code reference} {@link GeographicPosition}. The altitudes are not taken in
	 * consideration.
	 * <p>
	 * This result is wrapped in an {@link Optional} instance whose method {@link Optional#get()
	 * get()} throws {@link InternalErrorException} if the result is {@code null} (since this is
	 * never supposed to happen) and whose method {@link Optional#toString() toString()} returns the
	 * string <i>« {@code databaseName} is empty.»</i>.
	 * </p>
	 * 
	 * 
	 * @param reference
	 *            - The reference latitude and longitude.
	 * @param nrOfAirshipsToGet
	 *            - The number of closer airships to get.
	 * 
	 * @return a {@code List} of {@link #Airship airships} with the number of airships determined by
	 *         {@code nrOfAirshipsToGet} and sorted by the distance from each {@code Airship} to the
	 *         {@code reference} position.
	 * 
	 * @throws InvalidArgumentException
	 *             If {@code nrOfAirshipsToGet} is less than 0.
	 * 
	 * @see Optional
	 */
	public Optional<Iterable<Airship>> getAirshipsCloserTo(GeographicPosition reference,
			int nrOfAirshipsToGet) throws InvalidArgumentException {

		if (nrOfAirshipsToGet < 0)
			throw new InvalidArgumentException("Number of airships cannot be negative.");

		List<Airship> airshipsList = new ArrayList<Airship>();

		try {
			airshipsList.addAll(getAll().get().values());

		} catch (Exception e) { // This never happens because getAll() never has null values!
			System.out.println("ERROR in getAirshipsCloserTo()");
		}

		airshipsList.sort(new AirshipComparators.ComparatorByDistance(reference));

		if (nrOfAirshipsToGet <= airshipsList.size())
			airshipsList = airshipsList.subList(0, nrOfAirshipsToGet);

		return new Optional<Iterable<Airship>>(airshipsList, getDatabaseName() + " is empty.");

	}

	// PRIVATE AUXILIAR METHODS

	// Used in method add
	/**
	 * Updates the list of airships of the given {@code user} by adding an {@code airship} to it.
	 * 
	 * This lists are contained whiting the {@code flightsByUserRegister} {@link Map}.
	 * 
	 * @param airship
	 *            - The airship to be added to {@code user}'s list of airships.
	 * @param user
	 *            - The user whose list is to be updated.
	 */
	private void addAirshipToItsUsersListOfAirships(Airship airship, User user) {

		if (flightsByUserRegister.containsKey(user.getIdentification()))
			flightsByUserRegister.get(user.getIdentification()).add(airship);

		else {
			List<Airship> newListForNewUser = new ArrayList<>();
			newListForNewUser.add(airship);
			flightsByUserRegister.put(user.getIdentification(), newListForNewUser);
		}
	}

	// Used in method removeByIdentification
	/**
	 * Updates the list of airships posted the {@code users} by removing an {@code airship} with the
	 * given {@code flightId} to it.
	 * 
	 * This lists are contained whiting the {@code flightsByUserRegister} {@link Map}.
	 * 
	 * @param flightId
	 *            - The flightId of the airship to be removed from its user's list of airships.
	 */
	private void removeAirshipFromItsUsersListOfAirships(String flightId) {

		for (Entry<String, List<Airship>> entry : flightsByUserRegister.entrySet()) {

			List<Airship> list = entry.getValue();

			for (Airship airship : list)
				if (airship.getIdentification().equals(flightId)) {

					list.remove(airship);

					if (list.isEmpty())
						flightsByUserRegister.remove(entry.getKey());

					return;
				}
		}
	}
}