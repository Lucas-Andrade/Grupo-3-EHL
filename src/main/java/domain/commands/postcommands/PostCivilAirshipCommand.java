package main.java.domain.commands.postcommands;

import java.util.concurrent.Callable;

import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;

/**
 * Class whose instances are commands that post civil airships in a given database.
 * 
 * Implements the Interface {@link Callable} of {@link String}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostCivilAirshipCommand implements Callable<String> {

	// INSTANCE FIELDS

	/**
	 * The users' database that stores the user who's posting the airship.
	 */
	private User userWhoIsPosting;

	/**
	 * The database where to post the new civil airship.
	 */
	private Database<Airship> databaseWhereToPost;

	/**
	 * The properties of the airship to be created and added to the airships database.
	 */
	private double latitude;
	private double longitude;
	private double altitude;
	private double minAltitude;
	private double maxAltitude;
	private int numberOfPassengers;

	// CONSTRUCTOR

	/**
	 * Creates a new instance of this command that adds a civil airship with these properties to
	 * {@link databaseWhereToPost}.
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
	public PostCivilAirshipCommand(double latitude, double longitude, double altitude,
			double maxAltitude, double minAltitude, int numberOfPassengers,
			Database<Airship> airshipsDatabase, User userWhoIsPosting)
			throws InvalidArgumentException {

		if (airshipsDatabase == null)
			throw new InvalidArgumentException("Cannot instantiate command with null database.");

		this.userWhoIsPosting = userWhoIsPosting;
		this.databaseWhereToPost = airshipsDatabase;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitude = altitude;
		this.minAltitude = minAltitude;
		this.maxAltitude = maxAltitude;
		this.numberOfPassengers = numberOfPassengers;
	}

	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE

	/**
	 * Adds a new civil airship with the properties given in the constructor to the given
	 * {@link #databaseWhereToPost}.
	 * 
	 * This method will always be successfull (if the command is call the airship is always
	 * successfully added to the database) unless some of the parameters are missing, in which case
	 * the command contructor will throw an exception!
	 * 
	 * @return The airship's flight id if the airship was successfully posted;
	 * 
	 * @throws Exception
	 *             If the value given for some property is invalid.
	 */
	@Override
	public String call() throws Exception {

		Airship theCivilAirship = new CivilAirship(latitude, longitude, altitude, maxAltitude,
				minAltitude, numberOfPassengers);

		String flightId = theCivilAirship.getIdentification();

		databaseWhereToPost.add(theCivilAirship, userWhoIsPosting);

		return "Flight Id: " + flightId;
	}
}