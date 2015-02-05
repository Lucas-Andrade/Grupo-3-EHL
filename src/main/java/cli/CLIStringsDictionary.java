package main.java.cli;


import main.java.cli.parsingtools.CommandParser;
import main.java.cli.parsingtools.Parser;
import main.java.cli.parsingtools.commandfactories.ParsingCommand;



/**
 * A list of correspondences between {@link String}s contained in string-commands and their internal
 * meaning in the app.
 * <p>
 * This app takes strings as input; these strings hold information on which operation to perform and
 * contain data needed to perform them. Therefore they must be recognizable and translatable into
 * instances by the app. <br />
 * This dictionary holds several static fields accessed throughout the app's parsing mechanism (the
 * app's {@link Parser}s, {@link CommandParser} and {@link ParsingCommand}s); each
 * variable of this dictionary is associated with a literal string that is either the name or the
 * value of a placeholder or parameter. Only names and values of placeholders and parameters
 * contained in this dictionary are recognized internally.
 * </p>
 * <p>
 * <b>Implementation note:</b><br />
 * This class will break the Open-Closed Principle (see SOLID principles); every time a new command
 * is created or a new functionality is added to the already existent commands, the new names and
 * values of its placeholders and/or parameters names are to be added in this list.
 * </p>
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public final class CLIStringsDictionary {
    
    /**
     * Unused private constructor
     */
    private CLIStringsDictionary(){}
    
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
    public static final String NUMBEROFAIRSHIPSTOFIND = "nbAirships";
    public static final String NUMBEROFPASSENGERS = "nbPassengers";
    
    // AIRSHIP RELATED. AIRSHIP_TYPE PARAMETER VALUES
    // beware!!! changes in these fields values affect invocations by reflection
    // in class PostAirshipCommandsFactory
    
    public static final String CIVIL = "Civil";
    public static final String MILITARY = "Military";
    
    
    
    // USER RELATED. PARAMETERS NAMES
    
    public static final String EMAIL = "email";
    public static final String FULLNAME = "fullname";
    public static final String LOGINNAME = "loginName";
    public static final String LOGINPASSWORD = "loginPassword";
    public static final String NEWPASSWORD = "newPassword";
    public static final String OLDPASSWORD = "oldPassword";
    public static final String PASSWORD = "password";
    public static final String USERNAME = "username"; // also a placeholder
    
    
    
    // OUTPUT RELATED. PARAMETERS NAMES
    
    public static final String ACCEPT = "accept";
    public static final String STREAM = "output-file";
    
    // TRANSLATOR RELATED. ACCEPT PARAMETER VALUES
    
    public static final String HTML = "text/html";
    public static final String JSON = "application/json";
    public static final String TEXT = "text/plain";
    
    
    
}
