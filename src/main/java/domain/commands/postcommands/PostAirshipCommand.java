package main.java.domain.commands.postcommands;


import java.util.concurrent.Callable;
import main.java.domain.commands.CompletionStatus;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Abstract class whose subclasses instances are {@link Callable} of {@link CompletionStatus} to
 * post an airship in a given database.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class PostAirshipCommand implements Callable< CompletionStatus > {
       
    // INSTANCE FIELDS
    
    /**
     * The users' database that stores the user who's posting the airship.
     */
    protected final User userWhoIsPosting;
    
    /**
     * The database where to post the new civil airship.
     */
    protected final Database< Airship > databaseWhereToPost;
    
    /**
     * The properties of the airship to be created and added to the airships database.
     */
    protected final double latitude;
    protected final double longitude;
    protected final double altitude;
    protected final double minAltitude;
    protected final double maxAltitude;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new instance of this command that adds a civil airship with these properties to
     * {@link databaseWhereToPost}.
     * 
     * @param latitude
     * @param longitude
     * @param altitude
     * @param minAltitude
     * @param maxAltitude
     * @param airshipsDatabase
     *            - The database where to add the airship.
     * @param userWhoIsPosting
     *            - The user whose login name was given in the post command.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is null.
     */
    public PostAirshipCommand( double latitude, double longitude, double altitude,
                               double maxAltitude, double minAltitude,
                               Database< Airship > airshipsDatabase, User userWhoIsPosting )
        throws InvalidArgumentException {
    
        if( airshipsDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        if( userWhoIsPosting == null )
            throw new InvalidArgumentException(
                                                "Cannot instantiate command with null userWhoIsPosting." );
        
        this.databaseWhereToPost = airshipsDatabase;
        
        this.userWhoIsPosting = userWhoIsPosting;
        
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.minAltitude = minAltitude;
        this.maxAltitude = maxAltitude;
    }
    
// IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    /**
     * Adds a new {@link Airship} with the properties given in the constructor to the given
     * {@link #databaseWhereToPost}.
     * 
     * This method will always be successful (if the command is call the airship is always
     * successfully added to the database) unless some of the parameters are missing, in which case
     * the command constructor will throw an exception!
     * 
     * @return The airship's {@code flight id} if the airship was successfully posted;
     * 
     * @throws InvalidArgumentException
     *             If some parameter have a invalid value.
     */
    @Override
    public CompletionStatus call() throws InvalidArgumentException {
    
        Airship theAirship = createAirship();
        
        databaseWhereToPost.add( theAirship, userWhoIsPosting );
        
        return new CompletionStatus( true, "Airship successfully posted with flightId: "
                                           + theAirship.getIdentification() );
    }
    
    /**
     * Return a new instance of {@link Airship}
     * 
     * @return An {@code airship}
     * 
     * @throws InvalidArgumentException
     *             If some parameter have a invalid value.
     */
    protected abstract Airship createAirship() throws InvalidArgumentException;
}
