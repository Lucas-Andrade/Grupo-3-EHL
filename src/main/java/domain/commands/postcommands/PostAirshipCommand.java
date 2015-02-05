package main.java.domain.commands.postcommands;

import java.util.concurrent.Callable;
import main.java.domain.commands.CompletionStatus;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;


public abstract class PostAirshipCommand implements Callable< CompletionStatus > {

    
// INSTANCE FIELDS
    
    /**
     * The users' database that stores the user who's posting the airship.
     */
    protected User userWhoIsPosting;
    
    /**
     * The database where to post the new civil airship.
     */
    protected Database< Airship > databaseWhereToPost;
    
    /**
     * The properties of the airship to be created and added to the airships database.
     */
    protected double latitude;
    protected double longitude;
    protected double altitude;
    protected double minAltitude;
    protected double maxAltitude;
    
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
            throw new InvalidArgumentException( "Cannot instantiate command with null userWhoIsPosting." );

        this.databaseWhereToPost = airshipsDatabase;

        this.userWhoIsPosting = userWhoIsPosting;
        
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.minAltitude = minAltitude;
        this.maxAltitude = maxAltitude;
    }
}
