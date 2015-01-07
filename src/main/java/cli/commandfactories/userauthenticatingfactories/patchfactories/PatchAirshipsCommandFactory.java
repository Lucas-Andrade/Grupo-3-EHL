package main.java.cli.commandfactories.userauthenticatingfactories.patchfactories;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

import main.java.cli.CommandLineStringsDictionary;
import main.java.cli.commandfactories.userauthenticatingfactories.UserAuthenticatingFactory;
import main.java.cli.commands.patchcommands.PatchCivilAirshipCommand;
import main.java.cli.commands.patchcommands.PatchMilitaryAirshipCommand;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.InMemoryAirshipsDatabase;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.User;

public class PatchAirshipsCommandFactory extends UserAuthenticatingFactory<Airship, String> {

	private final InMemoryAirshipsDatabase airshipsDatabase;

	/**
	 * The string representation of the airship's concrete type.
	 */
	private String type;

	private String identification;

	private Airship airship;

	private double latitude;
	private double longitude;
	private double altitude;
	private double minAltitude;
	private double maxAltitude;

	private final String[] requiredParameters;

	public PatchAirshipsCommandFactory(Database<User> usersDatabase,
			InMemoryAirshipsDatabase airshipsDatabase) throws InvalidArgumentException {

		super("Change an Airship Coordinates and/or AirCorridor", usersDatabase, airshipsDatabase);

		this.airshipsDatabase = airshipsDatabase;

		this.requiredParameters = new String[] {CommandLineStringsDictionary.FLIGHTID};
	}

	@SuppressWarnings ("unchecked")
	@Override
	protected Callable<String> internalInternalNewInstance(User userWhoIsPatching)
			throws NoSuchElementInDatabaseException, InvalidArgumentException,
			InternalErrorException, InvalidParameterValueException {

		getValuesOfTheParametersMap();

		String methodName = "patch" + type + "Airship";

		Class<? extends PatchAirshipsCommandFactory> c = this.getClass();
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

	@Override
	protected String[] getSpecificRequiredParameters() {

		return requiredParameters;
	}

	/**
	 * @throws NoSuchElementInDatabaseException
	 *             If the given flightId refers to an airship that is not contained in airships
	 *             database.
	 * @throws InvalidArgumentException
	 *             If the value for flight id is invalid.
	 * @throws InvalidParameterValueException
	 *             If the values received for latitude, longitude, altitude and air corridor's
	 *             limits are invalid.
	 * 
	 */
	private void getValuesOfTheParametersMap() throws InvalidArgumentException,
			NoSuchElementInDatabaseException, InvalidParameterValueException {

		identification = getParameterAsString(CommandLineStringsDictionary.FLIGHTID);

		try {
			airship = airshipsDatabase.getElementByIdentification(identification).get();

		} catch (InvalidArgumentException e) {
			throw e;
			// happens if identification==null, should not happen if cmdParser is working well

		} catch (Exception e1) {
			throw (NoSuchElementInDatabaseException) e1;
			// este é o tipo concreto da excepçao guardada no Optional
			// instanciado em getElementByIdentification
		}

		if (airship instanceof CivilAirship)
			type = "Civil";
		else
			type = "Military";

		try {
			latitude = getParameterAsDouble(CommandLineStringsDictionary.LATITUDE);

		} catch (MissingRequiredParameterException e) {
			latitude = airship.getCoordinates().getLatitude().getValue();
		}

		try {
			longitude = getParameterAsDouble(CommandLineStringsDictionary.LONGITUDE);

		} catch (MissingRequiredParameterException e) {
			longitude = airship.getCoordinates().getLongitude().getValue();
		}

		try {
			altitude = getParameterAsDouble(CommandLineStringsDictionary.ALTITUDE);

		} catch (MissingRequiredParameterException e) {
			altitude = airship.getCoordinates().getAltitude().getValue();
		}

		try {
			maxAltitude = getParameterAsDouble(CommandLineStringsDictionary.AIRCORRIDOR_MAXALTITUDE);

		} catch (MissingRequiredParameterException e) {
			maxAltitude = airship.getAirCorridor().getMaxAltitude();
		}

		try {
			minAltitude = getParameterAsDouble(CommandLineStringsDictionary.AIRCORRIDOR_MINALTITUDE);

		} catch (MissingRequiredParameterException e) {
			minAltitude = airship.getAirCorridor().getMinAltitude();
		}
	}

	// used with reflection
	@SuppressWarnings ("unused")
	private Callable<String> patchCivilAirship(User userWhoIsPacthing) {

		try {
			return new PatchCivilAirshipCommand(airshipsDatabase, identification,
					userWhoIsPacthing, latitude, longitude, altitude, maxAltitude, minAltitude,
					((CivilAirship) airship).getPassengers());

		} catch (InvalidArgumentException e) {// never happens for databaseWhereToPost is not null
			return null;
		}
	}

	// used with reflection
	@SuppressWarnings ("unused")
	private Callable<String> patchMilitaryAirship(User userWhoIsPatching) {

		try {
			return new PatchMilitaryAirshipCommand(airshipsDatabase, identification,
					userWhoIsPatching, latitude, longitude, altitude, maxAltitude, minAltitude,
					((MilitaryAirship) airship).hasWeapons());

		} catch (InvalidArgumentException e) {// never happens for databaseWhereToPost is not null
			return null;
		}
	}
}