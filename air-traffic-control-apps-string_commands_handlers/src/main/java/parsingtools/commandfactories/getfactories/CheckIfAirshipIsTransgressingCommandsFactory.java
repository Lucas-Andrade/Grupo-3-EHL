package parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.ParsingCommand;
import parsingtools.commandfactories.getfactories.getallfactories.GetAllElementsInADatabaseCommandsFactory;
import utils.CLIStringsDictionary;
import commands.getcommands.CheckIfAirshipIsTransgressingCommand;
import databases.Database;
import elements.Airship;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.MissingRequiredParameterException;


/**
 * Class whose instances are {@link CommandFactory factories} that produce commands of type
 * {@link CheckIfAirshipIsTransgressingCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link GetAllElementsInADatabaseCommandsFactory} of {@link Airship Airships}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class CheckIfAirshipIsTransgressingCommandsFactory extends CommandFactory< String > {
    
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
        
        if( airshipsDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );
        
        this.requiredParametersNames = new String[]{ CLIStringsDictionary.FLIGHTID };
        this.airshipsDatabase = airshipsDatabase;
    }
    
    /**
     * Returns a command of type {@link CheckIfAirshipIsTransgressingCommand} after getting the
     * necessary {@code required parameters} using the private auxiliar method
     * {@link #setFlightIdValueOfTheParametersMap()}.
     * 
     * @return A command of type {@link CheckIfAirshipIsTransgressingCommand}.
     * @throws MissingRequiredParameterException
     *             If any of the required parameters is missing.
     */
    @Override
    protected Callable< String > internalNewCommand( Map< String, String > parametersMap )
        throws MissingRequiredParameterException {
        
        return new CheckIAIT_ParsingCommand( parametersMap ).newCommand();
    }
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
        
        return "Checks whether an airship is transgressing its air corridor.";
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
    
    
    
    // INNER CLASS
    /**
     * Class that extends {@link ParsingCommand}, whose instances will parse the
     * {@code required parameters} and will create a {@link CheckIfAirshipIsTransgressingCommand}
     */
    private class CheckIAIT_ParsingCommand extends ParsingCommand< String > {
        
        /**
         * {@code flightId} - The flightId of the airships to be evaluated.
         */
        private String flightId;
        
        /**
         * Create the {@code ParsingCommand}
         * 
         * @param parametersMap
         * @throws MissingRequiredParameterException
         *             If any of the required parameters is missing.
         */
        public CheckIAIT_ParsingCommand( Map< String, String > parametersMap )
            throws MissingRequiredParameterException {
            
            super( parametersMap );
            flightId = getParameterAsString( requiredParametersNames[0] );
        }
        
        /**
         * @return A command of type {@link CheckIfAirshipIsTransgressingCommand}.
         */
        @Override
        public Callable< String > newCommand() {
            
            try {
                return new CheckIfAirshipIsTransgressingCommand( airshipsDatabase, flightId );
            }
            catch( InvalidArgumentException e ) {
                throw new InternalErrorException(
                                                  "UNEXPECTED EXCEPTION IN CheckIfAirshipIsTransgressingCommandsFactory!" );
                // never happens cause database is not null
            }
        }
    }
}
