package main.java.cli.parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.cli.parsingtools.commandfactories.ParsingCommand;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllElementsInADatabaseCommandsFactory;
import main.java.domain.commands.getcommands.GetAirshipsOfOwnerCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * Class whose instances are {@link ParsingCommand factories} that produce commands of
 * type {@link GetAirshipsByOwnerCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link GetAllElementsInADatabaseCommandsFactory} of {@link Optional} {@link Iterable
 * Iterables} of {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes.
 */
public class GetAirshipsOfOwnerCommandsFactory extends
        CommandFactory< Optional< Iterable< Airship >>> {
    
    // INSTANCE FIELDS
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    /**
     * {@code airshipsDatabase} - The database where to search the elements from.
     */
    private final Database<Airship> airshipsDatabase;
    

    
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
    public GetAirshipsOfOwnerCommandsFactory( Database<Airship> airshipsDatabase )
        throws InvalidArgumentException {
        
        super( );
        
        if( airshipsDatabase == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null database!" );
        
        this.requiredParametersNames = new String[]{ CLIStringsDictionary.OWNER };
        this.airshipsDatabase = airshipsDatabase;
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
    
    /**
     * Returns a command of type {@link GetAirshipsByOwnerCommand} after getting the necessary
     * {@code required parameters} using the private auxiliar method
     * {@link #setOwnersUsernameValueOfTheParametersMap()}.
     * 
     * @return A command of type {@link GetAirshipsByOwnerCommand}.
     */
    @Override
    protected Callable< Optional< Iterable< Airship >>>
            internalNewCommand( Map< String, String > parametersMap )
                throws InvalidParameterValueException, WrongLoginPasswordException,
                NoSuchElementInDatabaseException, InternalErrorException,
                MissingRequiredParameterException, InvalidArgumentException {
        
        return new Get( parametersMap ).newCommand();
    }
    
    /**
     * Returns an array of strings with name of the parameter needed to produce the command - in
     * this case the name of the parameter that contains the owner's username.
     * 
     * @return An array of strings with the name of the required parameters.
     */
    @Override
    protected String[] getRequiredParametersNames() {
        
        return requiredParametersNames;
    }
    
    // PRIVATE AUXILIAR METHOD
    
//    /**
//     * Sets the value of the field {@link #ownerUsername} with the value received in the parameters
//     * map needed to {@link GetAirshipsOfOwnerCommand}.
//     * <p>
//     * Since this method is called inside {@link #internalNewInstance(Map)} and, in its turn, this
//     * last one is called inside {@link ParsingCommand#newCommand(Map)}, it is guaranteed
//     * that the field {@link #ownerUsername} is non-{@code null} after this method finishes its job.
//     * </p>
//     */
//    private void setOwnersUsernameValueOfTheParametersMap() {
//        
//        
//    }

    @Override
    public String getCommandsDescription() {
        return "Gets all airships added by a certain user." ;
    }


    
    private class Get extends ParsingCommand< Optional< Iterable< Airship >> > {
        
        /**
         * {@code ownerUsername} - The username of the user whose airships are to get from
         * {@link #airshipsDatabase}
         */
        private String ownerUsername;

        public Get( Map< String, String > parametersMap ) {
            super( parametersMap );
            ownerUsername = getParameterAsString( requiredParametersNames[0] );
        }

        @Override
        public Callable< Optional< Iterable< Airship >>> newCommand() {
            try {
                return new GetAirshipsOfOwnerCommand( airshipsDatabase, ownerUsername );
            }
            catch( InvalidArgumentException e ) {
                throw new InternalErrorException( "UNEXPECTED EXCEPTION IN GetAirshipsOfOwnerCommandsFactory!", e );
                // never happens because database is not null
            }
        }
    }
}
