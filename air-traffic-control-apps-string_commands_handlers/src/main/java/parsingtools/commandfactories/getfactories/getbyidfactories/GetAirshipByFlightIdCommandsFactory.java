package parsingtools.commandfactories.getfactories.getbyidfactories;


import java.util.concurrent.Callable;
import parsingtools.commandfactories.CommandFactory;
import utils.CommandStrings_Dictionary;
import databases.Database;
import elements.Airship;
import exceptions.InvalidArgumentException;


/**
 * A {@link CommandFactory factory} that creates commands to get an airship with a certain flightId
 * from an airship database. Commands are {@link Callable} instances.
 * 
 * Extends {@link GetElementByIdentificationCommandsFactory} of {@link Airship Airships}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipByFlightIdCommandsFactory extends
        GetElementByIdentificationCommandsFactory< Airship > {
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetAirshipByFlightIdCommandFactory} that produces commands to get an
     * airship with a certain flightId from the {@code airshipsDatabase}. That flightId is the value
     * of the parameter with key {@link CommandStrings_Dictionary#FLIGHTID} received in the parameters
     * map.
     * 
     * @param airshipsDatabase
     *            - The database where to get the airship from.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is {@code null}.
     */
    public GetAirshipByFlightIdCommandsFactory( Database< Airship > airshipsDatabase )
        throws InvalidArgumentException {
        
        super( CommandStrings_Dictionary.FLIGHTID, airshipsDatabase );
    }
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
        
        return "Gets an airship with a certain flightId.";
    }
}
