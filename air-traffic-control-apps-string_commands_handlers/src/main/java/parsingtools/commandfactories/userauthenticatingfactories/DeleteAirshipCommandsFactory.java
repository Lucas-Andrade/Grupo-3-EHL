package parsingtools.commandfactories.userauthenticatingfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import parsingtools.commandfactories.ParsingCommand;
import utils.CommandStrings_Dictionary;
import utils.CompletionStatus;
import commands.DeleteAirshipCommand;
import databases.Database;
import elements.Airship;
import elements.User;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;


/**
 * Class whose instances are {@link ParsingCommand factories} that produce commands of type
 * {@link DeleteAirshipCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link UserAuthenticatingFactory} of {@link Airship Airships} and {@link String Strings}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class DeleteAirshipCommandsFactory extends
        UserAuthenticatingFactory< Airship, CompletionStatus > {
    
    
    
    // CONSTRUCTOR
    /**
     * Creates a new {@link DeleteAirshipCommandsFactory} that produces commands of type
     * {@link DeleteAirshipCommand}.
     * 
     * @param usersDatabase
     *            - The database that contains the user that will delete the airship from
     *            {@link #airshipDatabase}.
     * 
     * @param airshipDatabase
     *            - The airship database whose elements can be deleted.
     * 
     * @throws InvalidArgumentException
     *             If either {@code usersDatabase} or {@code airshipDatabase} are {@code null}.
     */
    public DeleteAirshipCommandsFactory( Database< User > usersDatabase,
                                         Database< Airship > airshipDatabase )
        throws InvalidArgumentException {
        
        super( usersDatabase, airshipDatabase );
    }
    
    
    
    // IMPLEMENTATION OF METHODS INHERITED FROM CommandFactory AND UserAuthenticatingFactory
    
    /**
     * @see parsingtools.commandfactories.CommandFactory#getCommandsDescription()
     */
    @Override
    public String getCommandsDescription() {
        
        return "Deletes an airship.";
    }
    
    /**
     * @see UserAuthenticatingFactory#internalInternalNewCommand(java.util.Map,
     *      main.java.domain.model.users.User)
     */
    @Override
    protected
            Callable< CompletionStatus >
            internalInternalNewCommand( Map< String, String > parametersMap, User userWhoIsDeleting )
                throws MissingRequiredParameterException, InvalidParameterValueException {
        
        return new DeleteAirship_ParsingCommand( parametersMap ).newCommand();
        // to create these factory's commands, the userWhoIsDeleting is not needed
    }
    
    /**
     * @see UserAuthenticatingFactory#getSpecificRequiredParametersNames()
     */
    @Override
    protected String[] getSpecificRequiredParametersNames() {
        
        return new String[]{ CommandStrings_Dictionary.FLIGHTID };
    }
    
    
    
    // INNER CLASS
    /**
     * Class whose instances are responsible for creating commands of type
     * {@link DeleteAirshipCommand} after interpreting the string parameters received in the
     * parameters map.
     *
     * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
     */
    private class DeleteAirship_ParsingCommand extends ParsingCommand< CompletionStatus > {
        
        /**
         * Creates a new {@link DeleteAirship_ParsingCommand} that creates a command of type
         * {@link DeleteAirshipCommand}.
         * 
         * @param parametersMap
         *            The container of the parameters required to create the command.
         */
        public DeleteAirship_ParsingCommand( Map< String, String > parametersMap ) {
            super( parametersMap );
        }
        
        /**
         * Returns a command of type {@link DeleteAirshipCommand} after extracting the value of the
         * parameter with name {@link CommandStrings_Dictionary#FLIGHTID} from the {@code parametersMap}.
         * 
         * @return A command of type {@link DeleteAirshipCommand}.
         * @throws MissingRequiredParameterException
         *             If the value of the parameter with name {@link CommandStrings_Dictionary#FLIGHTID}
         *             is {@code null} or an empty-string.
         */
        @Override
        protected Callable< CompletionStatus > newCommand()
            throws MissingRequiredParameterException {
            
            String flightId = getParameterAsString( CommandStrings_Dictionary.FLIGHTID );
            try {
                return new DeleteAirshipCommand( databaseToChange, flightId );
            }
            catch( InvalidArgumentException e ) {
                throw new InternalErrorException(
                                                  "UNEXPECTED EXCEPTION IN DeleteAirship_ParsingCommand!" );
                // never happens cause airshipDatabase is not null
            }
        }
        
    }
    
    
    
}
