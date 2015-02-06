package main.java.domain.commands.postcommands;


import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.CivilAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are commands that post a {@link CivilAirship} in a given database.
 * 
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostCivilAirshipCommand extends PostAirshipCommand {
    
    // INSTANCE FIELDS
    
    private final int numberOfPassengers;
    
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
     * @param numberOfPassengers
     * @param airshipsDatabase
     *            - The database where to add the airship.
     * @param userWhoIsPosting
     *            - The user whose login name was given in the post command.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is null.
     */
    public PostCivilAirshipCommand( double latitude, double longitude, double altitude,
                                    double maxAltitude, double minAltitude, int numberOfPassengers,
                                    Database< Airship > airshipsDatabase, User userWhoIsPosting )
        throws InvalidArgumentException {
    
        super( latitude, longitude, altitude, maxAltitude, minAltitude, airshipsDatabase,
               userWhoIsPosting );
        
        this.numberOfPassengers = numberOfPassengers;
    }
    
    // IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    
    
    /**
     * Create a CivilAirship
     * 
     * @see PostAirshipCommand#createAirship()
     */
    @Override
    protected Airship createAirship() throws InvalidArgumentException {
    
        return new CivilAirship( latitude, longitude, altitude, maxAltitude, minAltitude,
                                 numberOfPassengers );
    }
}
