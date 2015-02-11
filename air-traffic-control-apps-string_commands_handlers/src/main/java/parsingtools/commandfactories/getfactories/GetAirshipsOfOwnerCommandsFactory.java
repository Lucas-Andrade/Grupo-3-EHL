package parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import parsingtools.commandfactories.CommandFactory;
import parsingtools.commandfactories.ParsingCommand;
import utils.StringCommandsDictionary;
import utils.Optional;
import commands.getcommands.GetAirshipsOfOwnerCommand;
import databases.Database;
import elements.Airship;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.MissingRequiredParameterException;


/**
 * Class whose instances are {@link CommandFactory factories} that produce commands of type
 * {@link GetAirshipsByOwnerCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link CommandFactory} of {@link Optional} {@link Iterable Iterables} of {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes.
 */
public class GetAirshipsOfOwnerCommandsFactory extends
        CommandFactory< Optional< Iterable< Airship >>> {
    
    // INSTANCE FIELDS
    
    /**
     * The database where to search the elements from.
     */
    private final Database< Airship > airshipsDatabase;
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link GetAirshipsByOwnerCommandFactory} that produces commands of type
     * {@link GetAirshipsByOwnerCommand}.
     * 
     * @param airshipsDatabase
     *            - The database where to get the airships from.
     * 
     * @throws InvalidArgumentException
     *             If the {@code airshipsDatabase} is null.
     */
    public GetAirshipsOfOwnerCommandsFactory( Database< Airship > airshipsDatabase )
        throws InvalidArgumentException {
        
        if( airshipsDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );
        
        this.airshipsDatabase = airshipsDatabase;
    }
    
    
    /**
     * Returns a command of type {@link GetAirshipsByOwnerCommand} after getting the necessary
     * {@code required parameters} using the private method
     * {@link #setOwnersUsernameValueOfTheParametersMap()}.
     * 
     * @return A command of type {@link GetAirshipsByOwnerCommand}.
     * @throws MissingRequiredParameterException
     *             If any of the required parameters is missing.
     */
    @Override
    protected Callable< Optional< Iterable< Airship >>>
            internalNewCommand( Map< String, String > parametersMap )
                throws MissingRequiredParameterException {
        
        return new GetAOO_ParsingCommand( parametersMap ).newCommand();
    }
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
        
        return "Gets all airships added by a certain user.";
    }
    
    /**
     * Returns an array of strings with name of the parameter needed to produce the command - in
     * this case the name of the parameter that contains the owner's username.
     * 
     * @return An array of strings with the name of the required parameters.
     */
    @Override
    protected String[] getRequiredParametersNames() {
        
        return new String[]{ StringCommandsDictionary.OWNER };
    }
    
    // INNER CLASS
    /**
     * Class that extends {@link ParsingCommand}, whose instances will parse the
     * {@code required parameters} and will create a {@link GetAirshipsOfOwnerCommand}
     */
    private class GetAOO_ParsingCommand extends ParsingCommand< Optional< Iterable< Airship >> > {
        
        /**
         * The username of the user whose airships are to get from {@link #airshipsDatabase}
         */
        private String ownerUsername;
        
        /**
         * Create the {@code ParsingCommand}
         * 
         * @param parametersMap
         *            The container of the parameters required to create the command.
         * 
         * @throws MissingRequiredParameterException
         *             If any of the required parameters is missing.
         */
        public GetAOO_ParsingCommand( Map< String, String > parametersMap )
            throws MissingRequiredParameterException {
            
            super( parametersMap );
            ownerUsername = getParameterAsString( StringCommandsDictionary.OWNER );
        }
        
        /**
         * @return A command of type {@link GetAirshipsOfOwnerCommand}.
         */
        @Override
        public Callable< Optional< Iterable< Airship >>> newCommand() {
            
            try {
                return new GetAirshipsOfOwnerCommand( airshipsDatabase, ownerUsername );
            }
            catch( InvalidArgumentException e ) {
                throw new InternalErrorException(
                                                  "UNEXPECTED EXCEPTION IN GetAirshipsOfOwnerCommandsFactory!",
                                                  e );
                // never happens because database is not null
            }
        }
    }
}
