package main.java.cli.parsingtools.commandfactories.getfactories.getbyidfactories;


import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.ParsingCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * A {@link ParsingCommand factory} that creates commands to get an airship with a certain
 * flightId from an airship database. Commands are {@link Callable} instances.
 * 
 * Extends {@link ParsingCommand} of {@link Airship Airships}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipByFlightIdCommandsFactory extends
        GetElementByIdentificationCommandsFactory< Airship > {
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetAirshipByFlightIdCommandFactory} that produces commands to get an
     * airship with a certain flightId from the {@code airshipsDatabase}. That flightId is the value
     * of the parameter with key {@link CLIStringsDictionary#FLIGHTID} received in the parameters
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
        
        super( CLIStringsDictionary.FLIGHTID,
               airshipsDatabase );
    }

    @Override
    public String getCommandsDescription() {
        return "Gets an airship with a certain flightId.";
    }
    
    
//    /**
//     * Returns an array of strings with name of the parameter needed to produce the command- in this
//     * case the name of the parameter that contains the desired element's identification.
//     * 
//     * @return An array of strings with the name of the required parameters.
//     */
//    @Override
//    protected String[] getRequiredParametersNames() {
//        
//        return new String[]{CLIStringsDictionary.FLIGHTID};
//    }
}