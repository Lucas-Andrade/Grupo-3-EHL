package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.StringsToCommandsFactory;
import main.java.domain.commands.DeleteAirshipCommand;
import main.java.domain.model.Database;
import main.java.domain.model.airships.Airship;
import main.java.domain.model.users.User;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;


/**
 * Class whose instances are {@link StringsToCommandsFactory factories} that produce commands of
 * type {@link DeleteAirshipCommand}. Commands are {@link Callable} instances.
 * 
 * Extends {@link UserAuthenticatingFactory} of {@link Airship Airships} and {@link String Strings}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class DeleteAirshipCommandsFactory extends UserAuthenticatingFactory< Airship, String > {
    
    // Instance Fields
    
    /**
     * {@code airshipDatabase} - The airship database whose elements can be deleted.
     */
    private final Database< Airship > airshipDatabase;
    
    /**
     * {@code requiredParametersNames} - The array of strings with the names of the parameters
     * needed to produce the command.
     */
    private final String[] requiredParametersNames;
    
    /**
     * {@code flightId} - The flightId of the airship to delete from de {@link #airshipDatabase}.
     */
    private String flightId;
    
    // Constructor
    
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
        
        super( "Delete An Airship", usersDatabase, airshipDatabase );
        
        this.airshipDatabase = airshipDatabase;
        
        this.requiredParametersNames = new String[]{ CLIStringsDictionary.FLIGHTID };
    }
    
    // IMPLEMENTATION OF METHODS INHERITED FROM UserAuthenticatingFactory
    
    /**
     * Returns a command of type {@link DeleteAirshipCommand} after getting the necessary
     * {@code required parameters} using the private auxiliar method {@link #setFlightId()}.
     * 
     * This command will be in its turn returned to {@link StringsToCommandsFactory#newInstance()
     * newInstance()} via the method {@link UserAuthenticatingFactory#internalNewInstance()
     * internalNewInstance()}
     * 
     * @param userWhoIsDeleting
     *            - The user who is deleting the airship.
     * 
     * @return A command of type {@link DeleteAirshipCommand}.
     */
    @Override
    protected Callable< String > internalInternalNewInstance( User userWhoIsDeleting ) {
        
        setFlightId();
        
        try {
            return new DeleteAirshipCommand( airshipDatabase, flightId );
        }
        catch( InvalidArgumentException e ) {
            throw new InternalErrorException(
                                              "UNEXPECTED EXCEPTION IN DeleteAirshipCommandsFactory!" );
            // never happens cause airshipDatabase is not null
        }
    }
    
    /**
     * Returns an array of strings with name of the parameter needed to produce the command - in
     * this case the name of the parameter that contains the airship's {@code flightId}.
     * 
     * @return An array of strings with the name of the required parameters.
     */
    @Override
    protected String[] getSpecificRequiredParametersNames() {
        
        return requiredParametersNames;
    }
    
    // PRIVATE AUXILIAR METHOD - used in the method postsInternalNewInstance()
    
    /**
     * Sets the value of the field {@link #flightId} with the value received in the parameters map
     * needed to {@link DeleteAirshipCommand}.
     * <p>
     * Since this method is called inside {@link #internalNewInstance(Map)} and, in its turn, this
     * last one is called inside {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed
     * that the field {@link #flightId} is non-{@code null} after this method finishes its job.
     * </p>
     */
    private void setFlightId() {
        
        flightId = getParameterAsString( requiredParametersNames[0] );
    }
    
}