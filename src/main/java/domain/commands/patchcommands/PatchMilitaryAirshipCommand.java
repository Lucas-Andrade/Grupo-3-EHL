package main.java.domain.commands.patchcommands;

import java.util.concurrent.Callable;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * Class whose instances are commands that change an airship's properties belonging to an airships
 * database
 * 
 * Implements the Interface {@link Callable} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchMilitaryAirshipCommand implements Callable<String> {

	// INSTANCE FIELDS

	/**
	 * The users database that contains the airship we want to patch.
	 */
	private final Database<Airship> airshipDatabase;

	/**
	 * The airship's identification giving by the username.
	 */
	private final String identification;

	/**
	 * The user that will patch the airship
	 */
	private final User user;

	/**
	 * The properties of the airship that can be changed.
	 */
	private final double latitude;
	private final double longitude;
	private final double altitude;
	private final double maxAltitude;
	private final double minAltitude;
	private final boolean hasWeapons;

	// CONSTRUCTOR

	/**
	 * Creates a new instance of this command that patches a military airship with these properties
	 * in {@link databaseWhereToPost}.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param minAltitude
	 * @param maxAltitude
	 * @param numberOfPassengers
	 * @param airshipsDatabase
	 *            - The database where to add the airship.
	 * @param userWhoIsPosting
	 *            - The user whose login name was given in the post command.
	 * 
	 * @throws InvalidArgumentException
	 *             If the {@code airshipsDatabase} is null.
	 */
	public PatchMilitaryAirshipCommand(Database<Airship> airshipDatabase, String identification,
			User user, double latitude, double longitude, double altitude, double maxAltitude,
			double minAltitude, boolean hasWeapons) throws InvalidArgumentException {

		if (airshipDatabase == null)
			throw new InvalidArgumentException("Cannot instantiate command with null database.");

		if (identification == null)
			throw new InvalidArgumentException(
					"Cannot instantiate command with null identification.");

		if (user == null)
			throw new InvalidArgumentException();

		this.airshipDatabase = airshipDatabase;
		this.identification = identification;
		this.user = user;

		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.maxAltitude = maxAltitude;
		this.minAltitude = minAltitude;
		this.hasWeapons = hasWeapons;
	}

	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE

	/**
	 * Replaces a military airship belonging to an airships database that is specified by the given
	 * {@link #identification} with another airship with the new parameters given in the constructor
	 * to the given {@link #databaseWhereToPost}.
	 * 
	 * @return A string saying if the patching was successful or not.
	 * 
	 * @throws Exception
	 *             If the value given for some property is invalid.
	 */
	@Override
	public String call() throws Exception {

		if (airshipDatabase.removeByIdentification(identification)) {

			Airship airship = MilitaryAirship.createANewAirshipWithAPreDefinedIdentification(
					latitude, longitude, altitude, maxAltitude, minAltitude, hasWeapons,
					identification);

			airshipDatabase.add(airship, user);

			return "Airship successfully altered";
		}

		return "Airship does not exist in the database";
	}
}