package commands.getcommands;


import java.util.concurrent.Callable;
import databases.Database;
import elements.Airship;
import exceptions.InvalidArgumentException;
import exceptions.NoSuchElementInDatabaseException;


/**
 * Class whose instances are commands that check if a specific airship belonging to a database is
 * transgressing its air corridor.
 * 
 * Implements the {@link Callable} Interface.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CheckIfAirshipIsTransgressingCommand implements Callable< String > {
    
    // INSTANCE FIELDS
    
    /**
     * The database where to search.
     */
    private final Database< Airship > airshipDatabase;
    
    /**
     * The flightId of the airships to be evaluated.
     */
    private final String flightId;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new instance of this command that checks if the airship with flight Id
     * {@code flightId} in {@code database} is transgressing its air corridor.
     * 
     * @param database
     *            - The database where the airship is supposed to be found.
     * @param flightId
     *            - The flightId of the airships to be evaluated.
     * 
     * @throws InvalidArgumentException
     *             If either {@code database} or {@code flightId} are {@code null}.
     */
    public CheckIfAirshipIsTransgressingCommand( Database< Airship > database, String flightId )
        throws InvalidArgumentException {
    
        if( database == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        if( flightId == null )
            throw new InvalidArgumentException( "Cannot instantiate command with a null flightId." );
        
        this.airshipDatabase = database;
        this.flightId = flightId;
    }
    
    // IMPLEMENTATION OF THE METHOD call() INHERITED FROM Callable INTERFACE
    
    /**
     * Returns a String with the airship's {@code flightId} (given in the constructor) if it is in
     * {@code database} (given in the constructor) and a message saying if the airship is
     * transgressing or not its pre-established air corridor.
     * <p>
     * This method throws {@link NoSuchElementInDatabaseException} with the message <i>«
     * {@code flightId} not found in {@code database.getDatabaseName()}»</i> if the airship is not
     * in {@code database} .
     * </p>
     * 
     * @return Returns a String with the airship's {@code flightId} and the apropriate message its
     *         status.
     * 
     * @throws Exception
     *             If the the {@code flightId} is invalid.
     */
    @Override
    public String call() throws Exception {
    
        Airship theAirship = airshipDatabase.getElementByIdentification( flightId ).get();
        
        if( theAirship.isTransgressing() )
            return "The Airship with the Flight ID " + flightId
                   + " is transgressing its air corridor.";
        
        return "The Airship with the Flight ID " + flightId
               + " is not transgressing its air corridor.";
    }
}
