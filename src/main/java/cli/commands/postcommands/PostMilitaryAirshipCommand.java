package main.java.cli.commands.postcommands;

import java.util.concurrent.Callable;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.User;

/**
 * Class whose instances represent commands to post a military airship. Implements
 * {@code Callable<String>}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostMilitaryAirshipCommand implements Callable<String> {

	// INSTANCE FIELDS

	/**
	 * The users' database that stores the user who's posting the airshi.
	 */
	private User userWhoIsPosting;

	/**
	 * The database where to post the new military airship.
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
	private boolean hasArmours;

	// CONSTRUCTOR
	/**
	 * Creates a new instance of this command that adds a military airship with these properties to
	 * {@code airshipsDatabase}.
	 * 
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param minAltitude
	 * @param maxAltitude
	 * @param hasArmours
	 * @param airshipsDatabase
	 *            The database where to add the airship.
	 * @param userWhoIsPosting
	 *            The user whose login name was given in the post command.
	 * @throws InvalidArgumentException
	 *             If {@code airshipsDatabase==null}.
	 */
	public PostMilitaryAirshipCommand(double latitude, double longitude, double altitude,
			double maxAltitude, double minAltitude, boolean hasArmours,
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
		this.hasArmours = hasArmours;
	}

	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	/**
	 * Adds a new military airship with the properties given in the constructor to the database
	 * given in the constructor.
	 * 
	 * @return The airship's flight id if the if was successfully posted; </br>a message of failure
	 *         if it wasn't.
	 * @throws Exception
	 *             If the value given for some property is invalid.
	 */
	@Override
	public String call() throws Exception {

		Airship theMilitaryAirship = new MilitaryAirship(latitude, longitude, altitude,
				maxAltitude, minAltitude, hasArmours);
		
		String flightId = theMilitaryAirship.getIdentification();

		databaseWhereToPost.add(theMilitaryAirship, userWhoIsPosting);

		return "Flight Id: " + flightId;
	}

}
