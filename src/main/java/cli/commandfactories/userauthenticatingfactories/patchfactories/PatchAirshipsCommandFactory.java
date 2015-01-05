package main.java.cli.commandfactories.userauthenticatingfactories.patchfactories;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

import main.java.cli.StringsDictionary;
import main.java.cli.commandfactories.userauthenticatingfactories.UserAuthenticatingFactory;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.model.Database;
import main.java.cli.model.airships.Airship;
import main.java.cli.model.airships.CivilAirship;
import main.java.cli.model.airships.MilitaryAirship;
import main.java.cli.model.users.User;

public class PatchAirshipsCommandFactory extends UserAuthenticatingFactory<Airship, String> {

	private final Database<Airship> airshipsDatabase;

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
			Database<Airship> airshipsDatabase) throws InvalidArgumentException {

		super("Change an Airship Coordinates and/or AirCorridor", usersDatabase, airshipsDatabase);

		this.airshipsDatabase = airshipsDatabase;

		this.requiredParameters = new String[] {StringsDictionary.FLIGHTID};
	}

	@SuppressWarnings ("unchecked")
	@Override
	protected Callable<String> internalInternalNewInstance(User userWhoIsPosting) throws Exception {

		getValuesOfTheParametersMap();

		String methodName = "patch" + type + "Airship";
		Class<? extends PatchAirshipsCommandFactory> c = this.getClass();
		Class<? extends User> u = userWhoIsPosting.getClass();

		try {
			return (Callable<String>) c.getDeclaredMethod(methodName, u).invoke(this,
					userWhoIsPosting);

		} catch (InvocationTargetException e) {

			try {
				throw e.getTargetException();

			} catch (MissingRequiredParameterException e1) {
				throw (MissingRequiredParameterException) e1;

			} catch (InvalidParameterValueException e1) {
				throw (InvalidParameterValueException) e1;

			} catch (Throwable e1) {// If this happen make a call to the God of Java
				throw new InternalErrorException();
			}

		} catch (NoSuchMethodException | SecurityException | IllegalAccessException
				| IllegalArgumentException e) {
			throw new InvalidParameterValueException(StringsDictionary.AIRSHIP_TYPE, type);
		}
	}

	@Override
	protected String[] getSpecificRequiredParameters() {

		return requiredParameters;
	}

	private void getValuesOfTheParametersMap() throws Exception {

		if (!parametersMap.containsKey(StringsDictionary.FLIGHTID))
			throw new MissingRequiredParameterException(StringsDictionary.FLIGHTID);

		identification = getParameterAsString(StringsDictionary.FLIGHTID);

		airship = airshipsDatabase.getElementByIdentification(identification).get();

		type = airship.getClass().toString();

		try {
			latitude = getParameterAsDouble(StringsDictionary.LATITUDE);

		} catch (MissingRequiredParameterException e) {
			latitude = airship.getCoordinates().getLatitude().getValue();
		}

		try {
			longitude = getParameterAsDouble(StringsDictionary.LONGITUDE);

		} catch (MissingRequiredParameterException e) {
			longitude = airship.getCoordinates().getLongitude().getValue();
		}

		try {
			altitude = getParameterAsDouble(StringsDictionary.ALTITUDE);

		} catch (MissingRequiredParameterException e) {
			altitude = airship.getCoordinates().getAltitude().getValue();
		}

		try {
			maxAltitude = getParameterAsDouble(StringsDictionary.AIRCORRIDOR_MAXALTITUDE);

		} catch (MissingRequiredParameterException e) {
			maxAltitude = airship.getAirCorridor().getMaxAltitude();
		}

		try {
			minAltitude = getParameterAsDouble(StringsDictionary.AIRCORRIDOR_MINALTITUDE);

		} catch (MissingRequiredParameterException e) {
			minAltitude = airship.getAirCorridor().getMinAltitude();
		}
	}

	// used with reflection
	@SuppressWarnings ("unused")
	private Callable<String> patchCivilAirship(User userWhoIsPosting) {

		try {
			return new PatchCivilAirshipCommand(databaseWhereToPost, identification,
					userWhoIsPosting, latitude, longitude, altitude, maxAltitude, minAltitude,
					((CivilAirship) airship).getPassengers());

		} catch (InvalidArgumentException e) {// never happens for databaseWhereToPost is not null
			return null;
		}
	}

	// used with reflection
	@SuppressWarnings ("unused")
	private Callable<String> pacthMilitaryAirship(User userWhoIsPosting) {

		try {
			return new PatchMilitaryAirshipCommand(databaseWhereToPost, identification,
					userWhoIsPosting, latitude, longitude, altitude, maxAltitude, minAltitude,
					((MilitaryAirship) airship).hasWeapons());

		} catch (InvalidArgumentException e) {// never happens for databaseWhereToPost is not null
			return null;
		}
	}
}