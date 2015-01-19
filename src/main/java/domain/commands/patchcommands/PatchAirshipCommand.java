package main.java.domain.commands.patchcommands;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import main.java.domain.model.Database;
import main.java.domain.model.InMemoryDatabase;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.DatabaseException;

/**
 * Class whose instances are commands that change an airship's properties belonging to an airships
 * database
 * 
 * Implements the Interface {@link Callable} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchAirshipCommand implements Callable<String> {
	
	// INSTANCE FIELDS
	
	/**
	 * The users database that contains the airship we want to patch.
	 */
	private final Database<Airship>	airshipDatabase;
	
	/**
	 * The airship's identification giving by the username.
	 */
	private final String			identification;
	
	/**
	 * The user that will patch the airship
	 */
	private final User				user;
	
	/**
	 * The properties of the airship that can be changed.
	 */
	private Double					latitude;
	private Double					longitude;
	private Double					altitude;
	private Double					maxAltitude;
	private Double					minAltitude;
	
	private Airship					airship;
	private String					type;
	
	// CONSTRUCTOR
	
	/**
	 * Creates a new instance of this command that patches a civil airship with these properties in
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
	public PatchAirshipCommand(Database<Airship> airshipDatabase, String identification, User user,
		Double latitude, Double longitude, Double altitude, Double maxAltitude, Double minAltitude)
		throws InvalidArgumentException {
	
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
	}
	
	// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
	
	/**
	 * Replaces a civil airship belonging to an airships database that is specified by the given
	 * {@link #identification} with another airship with the new parameters given in the constructor
	 * to the given {@link #databaseWhereToPost}.
	 * 
	 * If the method {@link InMemoryDatabase#removeByIdentification(String)
	 * removeByIdentification(String)} throws {@link DatabaseException} the airship will not be
	 * patched and the method will return an error message
	 * 
	 * @return A string informing if the patching was successful or not.
	 * 
	 * @throws InvalidArgumentException
	 *             If any of the given values for the airship's proprerties is invalid.
	 */
	@Override
	public String call() throws InvalidArgumentException, Exception {
	
		try {
			
			if (latitude == null && longitude == null && altitude == null && maxAltitude == null
				&& minAltitude == null)
				return "Airship not patched beacause no new parameter was given";
			
			Airship originalAirship = airshipDatabase.getElementByIdentification(identification)
				.get();
			
			if (latitude == null)
				latitude = originalAirship.getCoordinates().getLatitude().getValue();
			
			if (longitude == null)
				longitude = originalAirship.getCoordinates().getLongitude().getValue();
			
			if (altitude == null)
				altitude = originalAirship.getCoordinates().getAltitude().getValue();
			
			if (maxAltitude == null)
				maxAltitude = originalAirship.getAirCorridor().getMaxAltitude();
			
			if (minAltitude == null)
				minAltitude = originalAirship.getAirCorridor().getMinAltitude();
			
			airshipDatabase.removeByIdentification(identification);
			
			type = originalAirship.getClass().getSimpleName();
			
			String methodName = "create" + type;
			
			Class<? extends PatchAirshipCommand> c = this.getClass();
			Class<? extends Airship> u = originalAirship.getClass();
			
			try {
				Method creatorMethod = c.getDeclaredMethod(methodName, u);
				creatorMethod.invoke(this, originalAirship);
				
			} catch (InvocationTargetException e) {
				throw new InvalidArgumentException(e.getMessage());
				
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
				return "UPS"; // Never Supposed To Happen!
			}
			
			airshipDatabase.add(airship, user);
			
			return "Airship successfully patched";
			
		} catch (DatabaseException e) {
			return "Airship does not exist in the database";
		}
	}
	
	@SuppressWarnings ("unused")
	private void createCivilAirship(CivilAirship originalAirship) throws InvalidArgumentException {
	
		airship = new CivilAirship(latitude, longitude, altitude, maxAltitude, minAltitude,
			originalAirship.getPassengers(), identification);
	}
	
	@SuppressWarnings ("unused")
	private void createMilitaryAirship(MilitaryAirship originalAirship)
		throws InvalidArgumentException {
	
		airship = new MilitaryAirship(latitude, longitude, altitude, maxAltitude, minAltitude,
			originalAirship.hasWeapons(), identification);
	}
}