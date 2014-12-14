package appForConsole.model;

/**
 * Dictionary
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class Param {

	/**
	 * Bellow all static Strings used in this {@link Command} that will be needed to create an
	 * {@link Airship}.
	 * 
	 * Type -> {@Civil} or {@code Military} Latitude, longitude, altitude, minAltitude and
	 * maxAltitude -> {@link Airship} constructor. NbPassengers -> {@link CivilAirship} constructor.
	 * HasArmour -> {@link MilitaryAirship} constructor. LoginName -> validate {@link User}.
	 */

	public static final String TYPE = "type";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String ALTITUDE = "altitude";
	public static final String MINALTITUDE = "minAltitude";
	public static final String MAXALTITUDE = "maxAltitude";

	public static final String[] REQUIRED_POST_AIRSHIP_PARAMETERS = {LATITUDE, LONGITUDE, ALTITUDE,
			MINALTITUDE, MAXALTITUDE};

	public static final String HASARMOUR = "hasArmour";
	public static final String PASSENGERSNUMBER = "nbPassengers";

	public static final String BELLOW_PASSENGERS_NUMBER = "nbP";

	public static final String OWNER = "owner";

	public static final String LOGINNAME = "loginName";
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String FULLNAME = "fullName";
}