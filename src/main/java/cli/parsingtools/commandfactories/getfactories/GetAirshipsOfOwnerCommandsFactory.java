package main.java.cli.parsingtools.commandfactories.getfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.cli.parsingtools.commandfactories.getfactories.getallfactories.GetAllElementsInADatabaseCommandsFactory;
import main.java.domain.commands.getcommands.GetAirshipsOfOwnerCommand;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.airships.InMemoryAirshipsDatabase;
import main.java.utils.Optional;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce commands of
 * type {@link GetAirshipsByOwnerCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link GetAllElementsInADatabaseCommandsFactory} of {@link Optional} {@link Iterable
 * Iterables} of {@link Airship}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes.
 */
public class GetAirshipsOfOwnerCommandsFactory extends
        StringsToCommandsFactory< Optional< Iterable< Airship >>> {
    
    // INSTANCE FIELDS
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    /**
     * {@code airshipsDatabase} - The database where to search the elements from.
     */
    private final InMemoryAirshipsDatabase airshipsDatabase;
    
    /**
     * {@code ownerUsername} - The username of the user whose airships are to get from
     * {@link #airshipsDatabase}
     */
    private String ownerUsername;
    
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
    public GetAirshipsOfOwnerCommandsFactory( InMemoryAirshipsDatabase airshipsDatabase )
        throws InvalidArgumentException {
        
        super( "Gets all airships added by a certain user." );
        
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
    protected Callable< Optional< Iterable< Airship >>> internalNewInstance() {
        
        setOwnersUsernameValueOfTheParametersMap();
        
        try {
            return new GetAirshipsOfOwnerCommand( airshipsDatabase, ownerUsername );
        }
        catch( InvalidArgumentException e ) { // never happens because database is not null
            return null;
        }
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
    
    /**
     * Sets the value of the field {@link #ownerUsername} with the value received in the parameters
     * map needed to {@link GetAirshipsOfOwnerCommand}.
     * <p>
     * Since this method is called inside {@link #internalNewInstance(Map)} and, in its turn, this
     * last one is called inside {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed
     * that the field {@link #ownerUsername} is non-{@code null} after this method finishes its job.
     * </p>
     */
    private void setOwnersUsernameValueOfTheParametersMap() {
        
        ownerUsername = getParameterAsString( requiredParametersNames[0] );
    }
}