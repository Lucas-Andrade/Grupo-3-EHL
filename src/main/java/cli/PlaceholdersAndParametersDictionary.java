package main.java.cli;

/**
 * A list of names of placeholders and parameters that are recognized in the commands. The
 * placeholders or parameters names in the commands to be registered or in the concrete commands to
 * be executed are to be accessed through the static fields here present.
 * <p>
 * <b>Implementation note:</b>
 * </p>
 * <p>
 * This class will break the Open-Closed Principle (see SOLID principles); every time a new command
 * is created, the new placeholders or parameters names are supposed to be added in this list.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public final class PlaceholdersAndParametersDictionary {

	// AIRSHIP RELATED. PLACEHOLDERS NAMES

	public static final String AIRSHIP_TYPE = "type";
	public static final String FLIGHTID = "flightId";
	public static final String NUMBEROFPASSENGERS_UPPERLIMIT = "nbP";
	public static final String OWNER = "owner";

	// AIRSHIP RELATED. PARAMETERS NAMES

	public static final String ALTITUDE = "altitude";
	public static final String AIRCORRIDOR_MINALTITUDE = "minAltitude";
	public static final String AIRCORRIDOR_MAXALTITUDE = "maxAltitude";
	public static final String HASARMOUR = "hasArmour";
	public static final String LATITUDE = "latitude";
	public static final String LONGITUDE = "longitude";
	public static final String NUMBEROFPASSENGERS = "nbPassengers";

	// AIRSHIP RELATED. PARAMETERS NAMES
	// beware!!! changes in these fields values affect invocations by reflection
	// in class PostAirshipCommandsFactory

	public static final String CIVIL = "Civil";
	public static final String MILITARY = "Military";

	// USER RELATED. PARAMETERS NAMES

	public static final String EMAIL = "email";
	public static final String FULLNAME = "fullname";
	public static final String LOGINNAME = "loginName";
	public static final String LOGINPASSWORD = "loginPassword";
	public static final String PASSWORD = "password";
	public static final String USERNAME = "username"; // also a placeholder
}