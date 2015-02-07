package parsingtools.commandfactories.userauthenticatingfactories;


import java.util.Map;
import java.util.concurrent.Callable;

import parsingtools.commandfactories.CommandFactory;
import utils.CLIStringsDictionary;
import utils.Optional;
import utils.StringUtils;

import commands.AuthenticateUserCommand;

import databases.Database;
import elements.Element;
import elements.User;
import exceptions.InternalErrorException;
import exceptions.InvalidArgumentException;
import exceptions.InvalidParameterValueException;
import exceptions.MissingRequiredParameterException;
import exceptions.NoSuchElementInDatabaseException;
import exceptions.WrongLoginPasswordException;


/**
 * Abstract base class whose subclasses' instances are factories that produce commands which change
 * databases and, consequently, need to authenticate a user before creating a command (e.g. post,
 * patch and delete commands).
 *
 * @param <E>
 *            The type of the elements contained in the database which will be changed.
 * @param <R>
 *            The type of the results returned by the method {@link Callable#call()} of the commands
 *            produced by this {@link CommandFactory}.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */

public abstract class UserAuthenticatingFactory< E extends Element, R > extends CommandFactory< R > {
    
    
    
    // INSTANCE FIELDS
    
    /**
     * The database that contains the user who is making changes to {@link #databaseToChange}.
     */
    private final Database< User > usersDatabase;
    
    /**
     * The database that will be changed.
     */
    protected final Database< E > databaseToChange;
    
    
    
    // CONSTRUCTOR
    
    /**
     * Creates a new {@link UserAuthenticatingFactory}.
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
    public UserAuthenticatingFactory( Database< User > usersDatabase, Database< E > databaseToChange )
        throws InvalidArgumentException {
    
        if( usersDatabase == null || databaseToChange == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null databases." );
        
        this.usersDatabase = usersDatabase;
        this.databaseToChange = databaseToChange;
    }
    
    
    
    // Creating the command METHODS
    
    // inherited from CommandFactory abstract class
    /**
     * Produces a command that changes databases.
     * <p>
     * It starts by authenticating the password of the user who is posting (both user and password
     * are expected in the parameters map) and returns a {@link Callable} instance ready to be
     * called.
     * 
     * @param parametersMap
     *            The container of the parameters required to create the command. *
     * @return A command.
     * 
     * @throws InvalidArgumentException
     *             If {@code parametersMap} is null.
     * @throws InvalidParameterValueException
     *             If the value received in the parameters map for a required parameter is invalid
     *             (e.g. is not convertible to the expected type).
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
     * @throws NoSuchElementInDatabaseException
     *             For the commands that need a user to be executed if there is no user in
     *             {@link #usersDatabase} whose username is the login name receive in the parameters
     *             map. The message of this exception is <i>«{login name} not found in
     *             {@code usersDatabase.getDatabaseName()}»</i>.
     * @throws WrongLoginPasswordException
     *             If the login password received does not match the login username's password.
     */
    protected final Callable< R > internalNewCommand( Map< String, String > parametersMap )
        throws MissingRequiredParameterException, InvalidParameterValueException,
        NoSuchElementInDatabaseException, WrongLoginPasswordException, InvalidArgumentException {
    
        /* Uses the TEMPLATE METHOD design pattern */
        
        User authenticatedUser = authenticateAndGetAuthenticatedUser( parametersMap );
        return internalInternalNewCommand( parametersMap, authenticatedUser );
    }
    
    // to be implemented by each concrete UserAutheticatingFactory
    /**
     * Produces a command.
     * 
     * @param authenticatedUser
     *            The user who is making changes to {@link #databaseToChange}.
     * @param parametersMap
     *            The container of the parameters required to create the command.
     * @return A command.
     * 
     * @throws InvalidArgumentException
     *             If {@code parametersMap} is null.
     * @throws InvalidParameterValueException
     *             If the value received in the parameters map for a required parameter is invalid
     *             (e.g. is not convertible to the expected type).
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
     * @throws NoSuchElementInDatabaseException
     *             For the commands that need a user to be executed if there is no user in
     *             {@link #usersDatabase} whose username is the login name receive in the parameters
     *             map. The message of this exception is <i>«{login name} not found in
     *             {@code usersDatabase.getDatabaseName()}»</i>.
     */
    protected abstract
            Callable< R >
            internalInternalNewCommand( Map< String, String > parametersMap, User authenticatedUser )
                throws MissingRequiredParameterException, InvalidParameterValueException,
                NoSuchElementInDatabaseException, InvalidArgumentException;
    
    // used in the method internalNewCommand(Map)
    /**
     * Checks whether the value of the parameter with name
     * {@link CLIStringsDictionary#LOGINPASSWORD} contained in {@code parametersMap} is the correct
     * password of the {@link User} stored in {@link #usersDatabase} whose username is the value of
     * the parameter {@link CLIStringsDictionary#LOGINNAME} contained in the {@code parametersMap}.
     * Returns the {@link User} stored in {@link #usersDatabase} if the password is correct.
     * 
     * @param parametersMap
     *            The container of parameters that shall contain the keys
     *            {@link CLIStringsDictionary#LOGINNAME} and
     *            {@link CLIStringsDictionary#LOGINPASSWORD}.
     * @return The authenticated {@link User}.
     * @throws MissingRequiredParameterException
     *             If either of the values of the parameters with names
     *             {@link CLIStringsDictionary#LOGINNAME} and
     *             {@link CLIStringsDictionary#LOGINPASSWORD} are {@code null} or the empty-string.
     * 
     * @throws NoSuchElementInDatabaseException
     *             If {@link #usersDatabase} contains no {@link User} with a username that matches
     *             the value of the parameter with name {@link CLIStringsDictionary#LOGINNAME}
     *             received in {@code parametersMap}.
     * @throws WrongLoginPasswordException
     *             If the given password does not match with user password. This exception's message
     *             is <i>«Wrong password: {@link CLIStringsDictionary#LOGINNAME}'s password is not
     *             {@link CLIStringsDictionary#LOGINPASSWORD}.»</i>
     */
    private User authenticateAndGetAuthenticatedUser( Map< String, String > parametersMap )
        throws NoSuchElementInDatabaseException, WrongLoginPasswordException,
        MissingRequiredParameterException {
    
        Callable< Optional< User > > authenticateUserCommand;
        try {
            
            String loginName =
                    StringUtils.parameterToString( CLIStringsDictionary.LOGINNAME,
                                                   parametersMap.get( CLIStringsDictionary.LOGINNAME ) );
            String loginPassword =
                    StringUtils.parameterToString( CLIStringsDictionary.LOGINPASSWORD,
                                                   parametersMap.get( CLIStringsDictionary.LOGINPASSWORD ) );
            // MissingRequiredParameterException
            
            
            authenticateUserCommand =
                    new AuthenticateUserCommand( loginName, loginPassword, usersDatabase );
            // InvalidArgumentException, NoSuchElementInDatabaseException
            
        }
        catch( InvalidArgumentException e ) {
            throw new InternalErrorException( "UNEXPECTED ERROR IN UserAuthenticatingFactory", e );
            // InvalidArgumentException never happens because usersDatabase is not null
        }
        
        try {
            return authenticateUserCommand.call().get();
        }
        catch( Exception e ) {
            throw (WrongLoginPasswordException)e; // downcast
        }
    }
    
    
    
    // Getting the required parameters METHODS
    
    // inherited from CommandFactory abstract class
    /**
     * Returns an array of {@link String strings} that has the names of the parameters without which
     * the command cannot execute, also containing the {@link String}s "{@code loginName}" and "
     * {@code loginPassword}".
     * <p>
     * All {@code "POST commands"}, {@code "PATCH commands"} and {@code "DELETE" commands} must
     * receive as parameters a login name and a login password referent to the {@link User user} who
     * is trying to change a specific Database. This {@link User user} will be authenticated before
     * the actual changing operation.
     * </p>
     * 
     * @return An array of {@link String strings} that has the names of the parameters without whom
     *         the command cannot execute.
     */
    protected final String[] getRequiredParametersNames() {
    
        String[] requiredParams =
                copyToNewArrayWith2MorePositions( getSpecificRequiredParametersNames() );
        requiredParams[requiredParams.length - 2] = CLIStringsDictionary.LOGINNAME;
        requiredParams[requiredParams.length - 1] = CLIStringsDictionary.LOGINPASSWORD;
        return requiredParams;
    }
    
    // to be implemented by each concrete UserAutheticatingFactory
    /**
     * Returns an array of {@link String Strings} that has the names of the parameters needed for
     * producing a command (returns it to the {@link #getRequiredParametersNames()}).
     * 
     * @return An array of {@link String}s that has the names of the parameters needed for producing
     *         a command (returns it to the {@link #getRequiredParametersNames()}).
     */
    protected abstract String[] getSpecificRequiredParametersNames();
    
    // used in the method getRequiredParametersNames()
    /**
     * Copies (by the same order) the {@link String Strings} stored in the {@code array} to the
     * first positions of a new array of {@link String Strings} that has 2 more entries than the
     * inicial {@code array}. If {@code array} is {@code null}, it is returned a new empty array
     * with 2 entries.
     * 
     * @param arrayToBeCopied
     *            The array to be copied.
     * 
     * @return A new array of {@link String strings} that has 2 more entries than the
     *         {@code arrayToBeCopied} and whose first positions contain the entries of the
     *         {@code arrayToBeCopied}.
     */
    private String[] copyToNewArrayWith2MorePositions( String[] arrayToBeCopied ) {
    
        if( arrayToBeCopied == null )
            return new String[2];
        
        String[] result = new String[arrayToBeCopied.length + 2];
        
        for( int index = 0; index < arrayToBeCopied.length; ++index )
            result[index] = arrayToBeCopied[index];
        
        return result;
    }
    
}
