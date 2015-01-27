package main.java.cli.parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllElementsInADatabaseCommandsFactory;
import main.java.domain.commands.getcommands.CheckIfAirshipIsTransgressingCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce commands of
 * type {@link CheckIfAirshipIsTransgressingCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link GetAllElementsInADatabaseCommandsFactory} of {@link Airship Airships}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CheckIfAirshipIsTransgressingCommandsFactory extends StringsToCommandsFactory< String > {
    
    // INSTANCE FIELDS
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    /**
     * {@code airshipsDatabase} - The database where to search the elements from.
     */
    private final Database< Airship > airshipsDatabase;
    
    /**
     * {@code flightId} - The flightId of the airships to be evaluated.
     */
    private String flightId;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link CheckIfAirshipIsTransgressingCommandFactory} that produces commands of
     * type {@link CheckIfAirshipIsTransgressingCommand}.
     * 
     * @param airshipsDatabase
     *            - The database where to search.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is null.
     */
    public CheckIfAirshipIsTransgressingCommandsFactory( Database< Airship > airshipsDatabase )
        throws InvalidArgumentException {
        
        super( "Checks whether an airship is transgressing its air corridor." );
        
        if( airshipsDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );
        
        this.requiredParametersNames = new String[]{ CLIStringsDictionary.FLIGHTID };
        this.airshipsDatabase = airshipsDatabase;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
    
    /**
     * Returns a command of type {@link CheckIfAirshipIsTransgressingCommand} after getting the
     * necessary {@code required parameters} using the private auxiliar method
     * {@link #setFlightIdValueOfTheParametersMap()}.
     * 
     * @return A command of type {@link CheckIfAirshipIsTransgressingCommand}.
     */
    @Override
    protected Callable< String > internalNewInstance() {
        
        setFlightIdValueOfTheParametersMap();
        
        try {
            return new CheckIfAirshipIsTransgressingCommand( airshipsDatabase, flightId );
            
        }
        catch( InvalidArgumentException e ) {
            throw new InternalErrorException(
                                              "UNEXPECTED EXCEPTION IN CheckIfAirshipIsTransgressingCommandsFactory!" );
            // never happens cause database is not null
        }
    }
    
    /**
     * Returns an array of strings with name of the parameters needed to produce the command - in
     * this case the name of the parameter that contains the airships's flightId.
     * 
     * @return An array of strings with the name of the required parameters.
     */
    @Override
    protected String[] getRequiredParametersNames() {
        
        return requiredParametersNames;
    }
    
    // PRIVATE AUXILIAR METHOD
    
    /**
     * Sets the value of the field {@link #flightId} with the value received in the parameters map.
     * <p>
     * Since this method is called inside {@link #internalNewInstance(Map)} and, in its turn, this
     * last one is called inside {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed
     * that the field {@link #flightId} is non-{@code null} after this method finishes its job.
     * </p>
     */
    private void setFlightIdValueOfTheParametersMap() {
        
        flightId = getParameterAsString( requiredParametersNames[0] );
    }
}