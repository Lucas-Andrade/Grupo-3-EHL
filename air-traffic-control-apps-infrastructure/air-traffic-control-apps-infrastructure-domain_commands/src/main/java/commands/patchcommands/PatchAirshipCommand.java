package commands.patchcommands;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import utils.CompletionStatus;
import databases.Database;
import databases.InMemoryDatabase;
import elements.Airship;
import elements.User;
import elements.airships.CivilAirship;
import elements.airships.MilitaryAirship;
import exceptions.DatabaseException;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;


/**
 * Class whose instances are commands that allow an airship's fields to be changed. The airship to
 * be changed must be in a given airships database
 * 
 * Implements the Interface {@link Callable} of {@link String}.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PatchAirshipCommand implements Callable< CompletionStatus > {
    
    // INSTANCE FIELDS
    
    /**
     * {@code airshipDatabase} - The airships database that contains the airship we want to patch.
     */
    private final Database< Airship > airshipDatabase;
    
    /**
     * {@code identification} - The airship's {@code flightId};
     */
    private final String identification;
    
    /**
     * {@code user} - The user that will patch the airship.
     */
    private final User user;
    
    /**
     * Airship's properties that can be changed.
     */
    private Double latitude;
    private Double longitude;
    private Double altitude;
    private Double maxAltitude;
    private Double minAltitude;
    
    /**
     * {@code airship} - The new airship that will replace the airship to be patched.
     */
    private Airship airship;
    
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
    public PatchAirshipCommand( Database< Airship > airshipDatabase, String identification,
                                User user, Double latitude, Double longitude, Double altitude,
                                Double maxAltitude, Double minAltitude )
        throws InvalidArgumentException {
        
        if( airshipDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        if( identification == null )
            throw new InvalidArgumentException(
                                                "Cannot instantiate command with null identification." );
        
        if( user == null )
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
     * patched and the method will return an error message.
     * 
     * @return A string informing if the patching was successful or not.
     * 
     * @throws InvalidArgumentException
     *             If any of the given values for the airship's properties is invalid.
     * @throws DatabaseException
     *             If the {@code #identification} given does not match any of the airships existing
     *             in the {@code #airshipDatabase}.
     */
    @Override
    public CompletionStatus call() throws InvalidArgumentException, DatabaseException {
        
        if( latitude == null && longitude == null && altitude == null && maxAltitude == null
            && minAltitude == null )
            return new CompletionStatus( false,
                                         "Airship not patched beacause no new parameter was given" );
        
        try {
            
            Airship originalAirship = setAirshipParameters();
            
            airshipDatabase.removeByIdentification( identification );
            
            chooseCorrectMethod( originalAirship );
            
            airshipDatabase.add( airship, user );
            
            return new CompletionStatus( true, "Airship successfully patched" );
            
        }
        catch( DatabaseException e ) {
            return new CompletionStatus( false, "Airship does not exist in the database" );
        }
    }
    
    // Private Methods
    
    /**
     * Method that will verify if the given parameters to patch the airship's original fields are
     * null and, if so, will replace them with the original values.
     * 
     * @return originalAirship - The airship we want to patch
     * 
     * @throws DatabaseException
     *             If the {@code #identification} given does not match any of the airships existing
     *             in the {@code #airshipDatabase}.
     */
    private Airship setAirshipParameters() throws DatabaseException {
        
        Airship originalAirship = null;
        
        try {
            originalAirship = airshipDatabase.getElementByIdentification( identification ).get();
            
        }
        catch( Exception e ) {
            throw (DatabaseException)e;
        }
        
        if( latitude == null )
            latitude = originalAirship.getCoordinates().getLatitude().getValue();
        
        if( longitude == null )
            longitude = originalAirship.getCoordinates().getLongitude().getValue();
        
        if( altitude == null )
            altitude = originalAirship.getCoordinates().getAltitude().getValue();
        
        if( maxAltitude == null )
            maxAltitude = originalAirship.getAirCorridor().getMaxAltitude();
        
        if( minAltitude == null )
            minAltitude = originalAirship.getAirCorridor().getMinAltitude();
        
        return originalAirship;
    }
    
    /**
     * Method that will verify the original airship type and call the correct method using
     * reflection depending on it.
     * 
     * @param originalAirship
     *            - The airship we want to patch.
     * 
     * @throws InvalidArgumentException
     *             If any of the given values for the airship's properties is invalid.
     */
    private void chooseCorrectMethod( Airship originalAirship ) throws InvalidArgumentException {
        
        String type = originalAirship.getClass().getSimpleName();
        
        String methodName = "create" + type;
        
        Class< ? extends PatchAirshipCommand > c = this.getClass();
        Class< ? extends Airship > u = originalAirship.getClass();
        
        try {
            Method creatorMethod = c.getDeclaredMethod( methodName, u );
            creatorMethod.invoke( this, originalAirship );
            
        }
        catch( InvocationTargetException e ) {
            throw new InvalidArgumentException( e.getMessage(), e );
            
        }
        catch( NoSuchMethodException | SecurityException | IllegalAccessException e ) {
            throw new InternalErrorException( "UNEXPECTED ERROR IN PatchAirshipCommand!", e );
            // Never Supposed To Happen!
        }
    }
    
    // Private Methods To Be Used With Reflection
    
    /**
     * Method that will create a new {@code CivilAirship} to replace the airship to be patched.
     * 
     * @param originalAirship
     *            - The airship we want to patch
     * 
     * @throws InvalidArgumentException
     *             If any of the given values for the airship's properties is invalid.
     */
    @SuppressWarnings( "unused" )
    private void createCivilAirship( CivilAirship originalAirship ) throws InvalidArgumentException {
        
        airship =
                new CivilAirship( latitude, longitude, altitude, maxAltitude, minAltitude,
                                  originalAirship.getPassengers(), identification );
    }
    
    /**
     * Method that will create a new {@code MilitaryAirship} to replace the airship to be patched.
     * 
     * @param originalAirship
     *            - The airship we want to patch
     * 
     * @throws InvalidArgumentException
     *             If any of the given values for the airship's properties is invalid.
     */
    @SuppressWarnings( "unused" )
    private void createMilitaryAirship( MilitaryAirship originalAirship )
        throws InvalidArgumentException {
        
        airship =
                new MilitaryAirship( latitude, longitude, altitude, maxAltitude, minAltitude,
                                     originalAirship.hasWeapons(), identification );
    }
}
