package parsingtools.commandfactories.userauthenticatingfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import parsingtools.commandfactories.ParsingCommand;
import databases.Database;
import elements.User;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;
import exceptions.NoSuchElementInDatabaseException;


/**
 * Class whose instances are {@link ParsingCommand factories} that produce a command that allow the
 * authentication o an user. Commands are {@link Callable} instances.
 * 
 * Extends {@link UserAuthenticatingFactory} of {@link User Users} and {@link Void}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class AuthenticateCommandFactory extends UserAuthenticatingFactory< User, Void > {
    
    // Constructor
    
    /**
     * Creates a new {@link AuthenticateCommandFactory}.
     * 
     * @param usersDatabase
     *            The database that contains the user who is making changes to
     *            {@link #databaseToChange}.
     * @param databaseToChange
     *            The database that will be changed.
     * 
     * @throws InvalidArgumentException
     *             If either {@code usersDatabase} or {@code databaseToChange} are {@code null}.
     */
    public AuthenticateCommandFactory( Database< User > usersDatabase,
                                       Database< User > databaseToChange )
        throws InvalidArgumentException {
    
        super( usersDatabase, databaseToChange );
    }
    
    /**
     * The required command is already created by {@link UserAuthenticatingFactory}.
     */
    @Override
    protected Callable< Void > internalInternalNewCommand( Map< String, String > parametersMap,
                                                           User authenticatedUser )
        throws MissingRequiredParameterException, InvalidParameterValueException,
        NoSuchElementInDatabaseException, InvalidArgumentException {
    
        return null;
    }
    
    /**
     * The required parameters are already requested by {@link UserAuthenticatingFactory}.
     */
    @Override
    protected String[] getSpecificRequiredParametersNames() {
    
        return null;
    }
    
    /**
     * Returns a short description of the command produced by this factory.
     * 
     * @return a short description of the command produced by this factory.
     */
    @Override
    public String getCommandsDescription() {
    
        return "Authenticates the user that is trying to log in";
    }
}
