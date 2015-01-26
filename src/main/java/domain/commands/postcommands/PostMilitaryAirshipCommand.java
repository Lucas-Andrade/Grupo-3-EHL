package main.java.domain.commands.postcommands;


import java.util.concurrent.Callable;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are commands that post military airships in a given database.
 * 
 * Implements the Interface {@link Callable} of {@link String}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostMilitaryAirshipCommand implements Callable< String > {
    
    // INSTANCE FIELDS
    
    /**
     * The users' database that stores the user who's posting the airshi.
     */
    private User userWhoIsPosting;
    
    /**
     * The database where to post the new military airship.
     */
    private Database< Airship > databaseWhereToPost;
    
    /**
     * The properties of the airship to be created and added to the airships database.
     */
    private double latitude;
    private double longitude;
    private double altitude;
    private double minAltitude;
    private double maxAltitude;
    private boolean hasArmours;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new instance of this command that adds a military airship with these properties to
     * {@link #databaseWhereToPost}.
     * 
     * @param latitude
     * @param longitude
     * @param altitude
     * @param minAltitude
     * @param maxAltitude
     * @param hasArmours
     * @param airshipsDatabase
     *            - The database where to add the airship.
     * @param userWhoIsPosting
     *            - The user whose login name was given in the post command.
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is null.
     */
    public PostMilitaryAirshipCommand( double latitude, double longitude, double altitude,
                                       double maxAltitude, double minAltitude, boolean hasArmours,
                                       Database< Airship > airshipsDatabase, User userWhoIsPosting )
        throws InvalidArgumentException {
        
        if( airshipsDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate command with null database." );
        
        this.userWhoIsPosting = userWhoIsPosting;
        this.databaseWhereToPost = airshipsDatabase;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.minAltitude = minAltitude;
        this.maxAltitude = maxAltitude;
        this.hasArmours = hasArmours;
    }
    
    // IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    /**
     * Adds a new military airship with the properties given in the constructor to the given
     * {@link #databaseWhereToPost}.
     * 
     * This method will always be successfull (if the command is call the airship is always
     * successfully added to the database) unless some of the parameters are missing, in which case
     * the command contructor will throw an exception!
     * 
     * @return The airship's {@code flight id} if the airship was successfully posted;
     * 
     * @throws Exception
     *             If the value given for some property is invalid.
     */
    @Override
    public String call() throws Exception {
        
        Airship theMilitaryAirship =
                new MilitaryAirship( latitude, longitude, altitude, maxAltitude, minAltitude,
                                     hasArmours );
        
        String flightId = theMilitaryAirship.getIdentification();
        
        databaseWhereToPost.add( theMilitaryAirship, userWhoIsPosting );
        
        return "Flight Id: " + flightId;
    }
}