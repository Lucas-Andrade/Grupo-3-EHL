package main.java.cli.parsingtools.commandfactories.userauthenticatingfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CLIStringsDictionary;
import main.java.cli.parsingtools.commandfactories.CommandFactory;
import main.java.cli.parsingtools.commandfactories.ParsingCommand;
import main.java.domain.commands.AuthenticateUserCommand;
import main.java.domain.model.Database;
import main.java.domain.model.Element;
import main.java.domain.model.users.User;
import main.java.utils.Optional;
import main.java.utils.exceptions.InternalErrorException;
import main.java.utils.exceptions.InvalidArgumentException;
import main.java.utils.exceptions.InvalidParameterValueException;
import main.java.utils.exceptions.MissingRequiredParameterException;
import main.java.utils.exceptions.WrongLoginPasswordException;
import main.java.utils.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * Abstract base class for all factories that produce commands which change databases and,
 * consequently, need to authenticate a user before creating a command. (e.g. post, patch and delete
 * commands).
 *
 * @param <E>
 *            - The type of the elements contained in the database which will be changed.
 * @param <R>
 *            - The {@link Callable} instance type of the command returned by the
 *            {@link #internalNewCommand()} method.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public abstract class UserAuthenticatingFactory< E extends Element, R > extends
        CommandFactory< R > {
    
    
    
    // INSTANCE FIELDS
    
    /**
     * {@code usersDatabase} - The users database that contains the user who is making changes to
     * the {@link #databaseToChange}.
     */
    private final Database< User > usersDatabase;
    
    /**
     * {@code database} - The database that will change.
     */
    protected final Database< E > databaseToChange;
    
    /**
     * {@code loginName} - The use's login name received in the parameters map.
     */
    private String loginName;
    
    /**
     * {@code loginPassword} - The use's login password received in the parameters map.
     */
    private String loginPassword;
    
    
    
    // CONSTRUCTOR
    
    /**
     * Stores the {@code commandsDescription} and the databases {@code postingUsersDatabase} and
     * {@code postedElementsDatabase}.
     * 
     * @param commandsDescription
     *            - A short description of the commands produced by this factory.
     * @param usersDatabase
     *            - The users database that stores the user who is making changes to
     *            {@code theDatabase}.
     * @param databaseToChange
     *            - The database that can suffer changes.
     * 
     * @throws InvalidArgumentException
     *             If either {@code commandsDescription}, {@code usersDatabase} or
     *             {@code databaseToChange} are {@code null}.
     */
    public UserAuthenticatingFactory( String commandsDescription, Database< User > usersDatabase,
                                      Database< E > databaseToChange )
        throws InvalidArgumentException {
        
        super( commandsDescription );
        
        if( usersDatabase == null || databaseToChange == null )
            throw new InvalidArgumentException( "Cannot instantiate factory with null databases." );
        
        this.usersDatabase = usersDatabase;
        this.databaseToChange = databaseToChange;
    }
    
    
    
    // IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
    
    /**
     * Produces a command that changes databases.
     * <ul>
     * <li>
     * Sets the values of the fields {@link #loginName} and {@link #loginPassword} with the values
     * received in the parameters map (if this method is called inside
     * {@link #internalNewInstance(Map)} and this one is called inside
     * {@link ParsingCommand#newCommand(Map)}, it is guaranteed that these fields are
     * non-{@code null}). </p></li>
     * <li>Checks whether the login user and login password received match.</li>
     * </ul>
     * It starts by authenticating the password of the user who is posting (both user and password
     * are expected in the parameters map) and returns a {@link Callable} instance ready to be
     * called.
     * 
     * @throws InternalErrorException
     * @throws InvalidParameterValueException
     * @throws MissingRequiredParameterException
     * @throws NoSuchElementInDatabaseException
     * @throws WrongLoginPasswordException
     * @throws InvalidArgumentException
     * 
     * @see {@link #internalInternalNewInstance(User)} for more information on the exceptions
     *      thrown.
     */
    protected final Callable< R > internalNewCommand()
        throws MissingRequiredParameterException, InvalidParameterValueException,
        InternalErrorException, NoSuchElementInDatabaseException, WrongLoginPasswordException,
        InvalidArgumentException {
        
        
        loginName = getParameterAsString( CLIStringsDictionary.LOGINNAME );
        loginPassword = getParameterAsString( CLIStringsDictionary.LOGINPASSWORD );
        Callable< Optional< User >> command =
                new AuthenticateUserCommand( loginName, loginPassword, usersDatabase );
        
        try {
            return internalInternalNewInstance( command.call().get() );
        }
        catch( Exception e ) {
            throw (WrongLoginPasswordException)e;
        }
    }
    
    /**
     * Returns an array of {@link String strings} that has the names of the parameters without which
     * the command cannot execute as well as the {@link String strings} "{@code loginName}" and "
     * {@code loginPassword}".
     * <p>
     * All {@code "POST commands"}, {@code "PATCH commands"} and {@code "DELETE" commands} must
     * receive as parameters a login name and a login password referent to the {@link User user} who
     * is trying to chage a specific Database. This {@link User user} will be authenticated before
     * the actual changing operation.
     * </p>
     * 
     * @return An array of {@link String strings} that has the names of the parameters without whom
     *         the command cannot execute.
     */
    protected final String[] getRequiredParametersNames() {
        
        String[] requiredParams =
                copyToNewArrayWith2MorePositions( getSpecificRequiredParametersNames() );
        requiredParams[requiredParams.length - 2] = "loginName";
        requiredParams[requiredParams.length - 1] = "loginPassword";
        return requiredParams;
    }
    
    
    
    // UNIMPLEMENTED AUXILIARY METHODS
    
    /**
     * Produces a command (returns it to the method {@link ParsingCommand#newInstance()
     * newInstance()}).
     * 
     * @param userWhoIsPosting
     *            - The user who's login name was received in the parameters map.
     * 
     * @throws InternalErrorException
     *             If an internal error that wasn't supposed to happen happened.
     * @throws MissingRequiredParameterException
     *             If the received map does not contain one of the required parameters for
     *             instantiating the command.
     * @throws InvalidParameterValueException
     *             If the received value for a required parameter was invalid.
     * @throws InvalidArgumentException
     *             If the received value for a required parameter was invalid.
     * @throws NoSuchElementInDatabaseException
     *             If an element expected to be in a certain database was not found in it.
     */
    protected abstract Callable< R > internalInternalNewInstance( User userWhoIsPosting )
        throws InternalErrorException, MissingRequiredParameterException,
        InvalidParameterValueException, NoSuchElementInDatabaseException, InvalidArgumentException;
    
    /**
     * Returns an array of {@link String Strings} that has the names of the parameters needed for
     * producing a command (returns it to the {@link #getRequiredParametersNames()}).
     * 
     * @return An array of {@link String}s that has the names of the parameters needed for producing
     *         a command (returns it to the {@link #getRequiredParametersNames()}).
     */
    protected abstract String[] getSpecificRequiredParametersNames();
    
    
    
    // AUXILIARY PRIVATE METHODS
    
    // used in the method getRequiredParameters
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