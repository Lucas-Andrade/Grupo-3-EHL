package main.java.domain.commands.postcommands;


import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.MilitaryAirship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are commands that post a {@link MilitaryAirship} in a given database.
 * 
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostMilitaryAirshipCommand extends PostAirshipCommand {
    
    
    /**
     * The properties of the airship to be created and added to the airships database.
     */
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
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is null.
     */
    public PostMilitaryAirshipCommand( double latitude, double longitude, double altitude,
                                       double maxAltitude, double minAltitude, boolean hasArmours,
                                       Database< Airship > airshipsDatabase, User userWhoIsPosting )
        throws InvalidArgumentException {
    
        super( latitude, longitude, altitude, maxAltitude, minAltitude, airshipsDatabase,
               userWhoIsPosting );
        
        this.hasArmours = hasArmours;
    }
    
    // IMPLEMENTATION OF METHOD call INHERITED FROM Callable INTERFACE
    
    
    /**
     * Create a MilitaryAirship
     * 
     * @see PostAirshipCommand#createAirship()
     */
    @Override
    protected Airship createAirship() throws InvalidArgumentException {
    
        return new MilitaryAirship( latitude, longitude, altitude, maxAltitude, minAltitude,
                                    hasArmours );
    }
}
