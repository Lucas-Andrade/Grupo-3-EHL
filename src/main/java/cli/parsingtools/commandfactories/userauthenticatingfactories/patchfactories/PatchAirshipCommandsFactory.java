package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.patchfactories;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.Callable;

import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.parsingtools.commandfactories.userauthenticatingfactories.UserAuthenticatingFactory;
import main.java.domain.commands.patchcommands.PatchCivilAirshipCommand;
import main.java.domain.commands.patchcommands.PatchMilitaryAirshipCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.utils.exceptions.parsingexceptions.InvalidParameterValueException;
import main.java.utils.exceptions.parsingexceptions.factoriesexceptions.MissingRequiredParameterException;

/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce commands that
 * patch airships. Commands are {@link Callable} instances.
 * 
 * Extends {@link UserAuthenticatingFactory} of {@link Airship Airships} and {@link String Strings}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchAirshipCommandsFactory extends UserAuthenticatingFactory<Airship, String> {

	// Instance Fields
	
	private final InMemoryAirshipsDatabase airshipsDatabase;

	/**
	 * {@code identification} - The identification of the airship we will
	 */
	private String identification;

	/**
	 * The properties of the airship to be added.
	 */
	private double latitude;
	private double longitude;
	private double altitude;
	private double minAltitude;
	private double maxAltitude;

	/**
	 * {@code type} - The string representation of the airship's concrete type. This will be only
	 * obtained after we find the airship in the database by its {@link #identification} and assert
	 * what type of airship it is in the auxiliar private method
	 * {@link #setValuesOfTheParametersMap()}
	 */
	private String type;

	/**
	 * {@code originalAirship} - The variable where the airship's to be altered original state will be
	 * saved.
	 */
	private Airship originalAirship;

	/**
	 * {@code requiredParametersNames} - The array of strings with the names of the parameters
	 * needed to produce the command.
	 */
	private final String[] requiredParametersNames;

	// Constructor

	/**
	 * Creates a new {@link PatchAirshipCommandsFactory} that produces commands of type
	 * {@link PatchCivilAirshipCommand} or {@link PatchMilitaryAirshipCommand}.
	 * 
	 * @param usersDatabase
	 *            - The users database that stores the user who is posting.
	 * @param airshipsDatabase
	 *            - The airships database where the airship to be altered is.
	 * 
	 * @throws InvalidArgumentException
	 *             If either {@code usersDatabase} or {@code airshipDatabase} are {@code null}.
	 */
	public PatchAirshipCommandsFactory(Database<User> usersDatabase,
			InMemoryAirshipsDatabase airshipsDatabase) throws InvalidArgumentException {

		super("Change an Airship Coordinates and/or AirCorridor", usersDatabase, airshipsDatabase);

		this.airshipsDatabase = airshipsDatabase;

		this.requiredParametersNames = new String[] {CLIStringsDictionary.FLIGHTID};
	}

	// IMPLEMENTATION OF METHODS INHERITED FROM UserAuthenticatingFactory

	/**
	 * Returns a command of type {@link PatchCivilAirshipCommand} or
	 * {@link PatchMilitaryAirshipCommand} after getting the necessary {@code required parameters}
	 * using the private auxiliar method {@link #setValuesOfTheParametersMap()}.
	 * 
	 * The created command depends on the value of {@link #type} and its created using reflection.
	 * 
	 * @param userWhoIsPatching
	 *            - The user who is patching the airship.
	 * 
	 * @return A command that patches airships.
	 * 
	 * @throws MissingRequiredParameterException
	 *             If any of the required parameters is missing.
	 * @throws InvalidParameterValueException
	 *             If any of the parameters have an invalid value.
	 * @throws InternalErrorException
	 *             Should never happen!
	 */
	@SuppressWarnings ("unchecked")
	@Override
	protected Callable<String> internalInternalNewInstance(User userWhoIsPatching)
			throws NoSuchElementInDatabaseException, InvalidArgumentException,
			InternalErrorException, InvalidParameterValueException {

		setValuesOfTheParametersMap();

		String methodName = "patch" + type + "Airship";

		Class<? extends PatchAirshipCommandsFactory> c = this.getClass();
		Class<? extends User> u = userWhoIsPatching.getClass();

		try {
			Method creatorMethod = c.getDeclaredMethod(methodName, u);
			return (Callable<String>) creatorMethod.invoke(this, userWhoIsPatching);

		} catch (InvocationTargetException e) {

			try {
				throw e.getTargetException();

			} catch (Throwable e1) {// If this happen make a call to the God of Java
				throw new InternalErrorException();
			}

		} catch (NoSuchMethodException | SecurityException | IllegalAccessException e) {
			return null; // Never Supposed To Happen!
		}
	}

	/**
	 * Returns an array of strings with name of the parameters needed to produce the command - in
	 * this case it will return the name of the parameters that contain the airship's identification
	 * and properties.
	 * 
	 * @return An array of strings with the name of the required parameters.
	 */
	@Override
	protected String[] getSpecificRequiredParameters() {

		return requiredParametersNames;
	}

	// PRIVATE AUXILIAR METHOD - used in the method postsInternalNewInstance()

	/**
	 * Sets the value of the airship's type and properties fields with the values received in the
	 * parameters map.
	 * <p>
	 * If this method is called inside {@link #internalNewInstance(Map)} and this one is called
	 * inside {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed that the fields
	 * {@link #identification}, {@link #latitude}, {@link #longitude}, {@link #altitude},
	 * {@link #minAltitude} and {@link #maxAltitude} are non- {@code null} after this method
	 * finishes its job.
	 * </p>
	 * 
	 * @throws NoSuchElementInDatabaseException
	 *             If the given flightId refers to an airship that is not contained in airships
	 *             database.
	 * @throws InvalidArgumentException
	 *             If the value for flight id is invalid.
	 * @throws InvalidParameterValueException
	 *             If the values received for latitude, longitude, altitude and air corridor's
	 *             limits are invalid.
	 */
	private void setValuesOfTheParametersMap() throws InvalidArgumentException,
			NoSuchElementInDatabaseException, InvalidParameterValueException {

		identification = getParameterAsString(CLIStringsDictionary.FLIGHTID);

		try {
			originalAirship = airshipsDatabase.getElementByIdentification(identification).get();

		} catch (InvalidArgumentException e) {
			throw e;
			// happens if identification==null, should not happen if cmdParser is working well

		} catch (Exception e1) {
			throw (NoSuchElementInDatabaseException) e1;
			// este é o tipo concreto da excepçao guardada no Optional
			// instanciado em getElementByIdentification
		}

		if (originalAirship instanceof CivilAirship)
			type = "Civil";
		else
			type = "Military";

		try {
			latitude = getParameterAsDouble(CLIStringsDictionary.LATITUDE);

		} catch (MissingRequiredParameterException e) {
			latitude = originalAirship.getCoordinates().getLatitude().getValue();
		}

		try {
			longitude = getParameterAsDouble(CLIStringsDictionary.LONGITUDE);

		} catch (MissingRequiredParameterException e) {
			longitude = originalAirship.getCoordinates().getLongitude().getValue();
		}

		try {
			altitude = getParameterAsDouble(CLIStringsDictionary.ALTITUDE);

		} catch (MissingRequiredParameterException e) {
			altitude = originalAirship.getCoordinates().getAltitude().getValue();
		}

		try {
			maxAltitude = getParameterAsDouble(CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE);

		} catch (MissingRequiredParameterException e) {
			maxAltitude = originalAirship.getAirCorridor().getMaxAltitude();
		}

		try {
			minAltitude = getParameterAsDouble(CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE);

		} catch (MissingRequiredParameterException e) {
			minAltitude = originalAirship.getAirCorridor().getMinAltitude();
		}
	}

	// Methods Used With Reflection

	/**
	 * 
	 * Private method that is invoked using reflection inside the
	 * {@link #postsInternalNewInstance()}.
	 * <p>
	 * <b> This method's name must be <i>patchCivilAirship</i></b>.
	 * 
	 * @param userWhoIsPatching
	 *            - The user who is patching the airship.
	 * 
	 * @return A {@link PatchCivilAirshipCommand}.
	 */
	@SuppressWarnings ("unused")
	private Callable<String> patchCivilAirship(User userWhoIsPacthing) {

		try {
			return new PatchCivilAirshipCommand(airshipsDatabase, identification,
					userWhoIsPacthing, latitude, longitude, altitude, maxAltitude, minAltitude,
					((CivilAirship) originalAirship).getPassengers());

		} catch (InvalidArgumentException e) {// never happens for databaseWhereToPost is not null
			return null;
		}
	}

	/**
	 * 
	 * Private method that is invoked using reflection inside the
	 * {@link #postsInternalNewInstance()}.
	 * <p>
	 * <b> This method's name must be <i>patchMilitaryAirship</i></b>.
	 * 
	 * @param userWhoIsPatching
	 *            - The user who is patching the airship.
	 * 
	 * @return A {@link PatchMilitaryAirshipCommand}.
	 */
	@SuppressWarnings ("unused")
	private Callable<String> patchMilitaryAirship(User userWhoIsPatching) {

		try {
			return new PatchMilitaryAirshipCommand(airshipsDatabase, identification,
					userWhoIsPatching, latitude, longitude, altitude, maxAltitude, minAltitude,
					((MilitaryAirship) originalAirship).hasWeapons());

		} catch (InvalidArgumentException e) {// never happens for databaseWhereToPost is not null
			return null;
		}
	}
}