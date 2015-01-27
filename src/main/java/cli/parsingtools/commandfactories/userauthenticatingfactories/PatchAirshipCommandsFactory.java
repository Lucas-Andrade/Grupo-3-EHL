package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.domain.commands.patchcommands.PatchAirshipCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce commands that
 * patch airships. Commands are {@link Callable} instances.
 * 
 * Extends {@link UserAuthenticatingFactory} of {@link Airship Airships} and {@link String Strings}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchAirshipCommandsFactory extends UserAuthenticatingFactory< Airship, String > {
    
    // Instance Fields
    
    private final InMemoryAirshipsDatabase airshipsDatabase;
    
    /**
     * {@code identification} - The identification of the airship we will
     */
    private String identification;
    
    /**
     * The properties of the airship to be added.
     */
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Double minAltitude;
    private Double maxAltitude;
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    // Constructor
    
    /**
     * Creates a new {@link PatchAirshipCommandsFactory} that produces commands of type
     * {@link PatchAirshipCommand} or {@link PatchMilitaryAirshipCommand}.
     * 
     * @param usersDatabase
     *            - The users database that stores the user who is posting.
     * @param airshipsDatabase
     *            - The airships database where the airship to be altered is.
     * 
     * @throws InvalidArgumentException
     *             If either {@code usersDatabase} or {@code airshipDatabase} are {@code null}.
     */
    public PatchAirshipCommandsFactory( Database< User > usersDatabase,
                                        InMemoryAirshipsDatabase airshipsDatabase )
        throws InvalidArgumentException {
        
        super( "Change an Airship Coordinates and/or AirCorridor", usersDatabase, airshipsDatabase );
        
        this.airshipsDatabase = airshipsDatabase;
        
        this.requiredParametersNames = new String[]{ CLIStringsDictionary.FLIGHTID };
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM UserAuthenticatingFactory
    
    /**
     * Returns a command of type {@link PatchAirshipCommand} or {@link PatchMilitaryAirshipCommand}
     * after getting the necessary {@code required parameters} using the private auxiliar method
     * {@link #setValuesOfTheParametersMap()}.
     * 
     * The created command depends on the value of {@link #type} and its created using reflection.
     * 
     * @param userWhoIsPatching
     *            - The user who is patching the airship.
     * 
     * @return A command that patches airships.
     * @throws InvalidArgumentException
     * @throws NoSuchElementInDatabaseException
     * 
     * @throws MissingRequiredParameterException
     *             If any of the required parameters is missing.
     * @throws InvalidParameterValueException
     *             If any of the parameters have an invalid value.
     * @throws InternalErrorException
     *             Should never happen!
     */
    @Override
    protected Callable< String > internalInternalNewInstance( User userWhoIsPatching )
        throws NoSuchElementInDatabaseException, InvalidParameterValueException,
        InvalidArgumentException {
        
        setValuesOfTheParametersMap();
        
        try {
            return new PatchAirshipCommand( airshipsDatabase, identification, userWhoIsPatching,
                                            latitude, longitude, altitude, maxAltitude, minAltitude );
            
        }
        catch( InvalidArgumentException e ) {
            throw new InternalErrorException( "UNEXPECTED ERROR IN PatchAirshipCommandsFactory!", e );
            // never happens for databaseWhereToPost is not null
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
    protected String[] getSpecificRequiredParametersNames() {
        
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
    private void setValuesOfTheParametersMap()
        throws InvalidArgumentException, NoSuchElementInDatabaseException,
        InvalidParameterValueException {
        
        identification = getParameterAsString( CLIStringsDictionary.FLIGHTID );
        
        try {
            latitude = getParameterAsDouble( CLIStringsDictionary.LATITUDE );            
        }
        catch( MissingRequiredParameterException e ) {
            latitude = null;
        }
        
        try {
            longitude = getParameterAsDouble( CLIStringsDictionary.LONGITUDE );            
        }
        catch( MissingRequiredParameterException e ) {
            longitude = null;
        }
        
        try {
            altitude = getParameterAsDouble( CLIStringsDictionary.ALTITUDE );            
        }
        catch( MissingRequiredParameterException e ) {
            altitude = null;
        }
        
        try {
            maxAltitude = getParameterAsDouble( CLIStringsDictionary.AIRCORRIDOR_MAXALTITUDE );            
        }
        catch( MissingRequiredParameterException e ) {
            maxAltitude = null;
        }
        
        try {
            minAltitude = getParameterAsDouble( CLIStringsDictionary.AIRCORRIDOR_MINALTITUDE );            
        }
        catch( MissingRequiredParameterException e ) {
            minAltitude = null;
        }
    }

}