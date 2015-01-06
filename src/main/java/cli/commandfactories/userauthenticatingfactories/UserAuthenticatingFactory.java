package main.java.cli.commandfactories.userauthenticatingfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.CommandLineDictionary;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.MissingRequiredParameterException;
import main.java.cli.exceptions.factoryexceptions.WrongLoginPasswordException;
import main.java.cli.model.Database;
import main.java.cli.model.Element;
import main.java.cli.model.users.User;


/**
 * Abstract base class for all factories that produce commands which change
 * databases and, consequently, need to authenticate a user before creating a
 * command. (e.g. post, patch and delete commands).
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <E>
 *            The type of the elements contained in the database which will be
 *            changed.
 * @param <R>
 *            The type parameter of the {@link Callable} instance returned by
 *            the method {@link #internalNewInstance()}.
 */
public abstract class UserAuthenticatingFactory< E extends Element, R > extends
		StringsToCommandsFactory< R >
{
	
	// INSTANCE FIELDS
	
	/**
	 * The users database that stores the user who is making changes to
	 * {@link #theDatabase}.
	 */
	private final Database< User > usersDatabase;
	
	/**
	 * The database to suffer changes.
	 */
	protected final Database< E > theDatabase;
	
	/**
	 * The login name received in the parameters map.
	 */
	private String loginName;
	
	/**
	 * The login password received in the parameters map.
	 */
	private String loginPassword;
	
	
	
	// CONSTRUCTOR
	/**
	 * Stores the {@code commandsDescription} and the databases
	 * {@code postingUsersDatabase} and {@code postedElementsDatabase}.
	 * 
	 * @param commandsDescription
	 *            A short description of the commands produced by this factory.
	 * @param usersDatabase
	 *            The users database that stores the user who is making changes
	 *            to {@code theDatabase}.
	 * @param theDatabase
	 *            The database to suffer changes.
	 * @throws InvalidArgumentException
	 *             If either {@code commandsDescription}, {@code usersDatabase}
	 *             or {@code theDatabase} are {@code null}.
	 */
	public UserAuthenticatingFactory( String commandsDescription,
			Database< User > usersDatabase, Database< E > theDatabase )
			throws InvalidArgumentException {
		
		super( commandsDescription );
		
		if( usersDatabase == null || theDatabase == null )
			throw new InvalidArgumentException(
					"Cannot instantiate post factory with null databases." );
		
		this.usersDatabase = usersDatabase;
		this.theDatabase = theDatabase;
	}
	
	
	
	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
	
	/**
	 * Produces a command that changes databases.
	 * <ul>
	 * <li>
	 * Sets the values of the fields {@link #loginName} and
	 * {@link #loginPassword} with the values received in the parameters map (if
	 * this method is called inside {@link #internalNewInstance(Map)} and this
	 * one is called inside {@link StringsToCommandsFactory#newInstance(Map)},
	 * it is guaranteed that these fields are non-{@code null}). </p></li>
	 * <li>Checks whether the login user and login password received match.</li>
	 * </ul>
	 * It starts by authenticating the password of the user who is posting (both
	 * user and password are expected in the parameters map) and returns a
	 * {@link Callable} instance ready to be called.
	 * 
	 * @throws InternalErrorException
	 * @throws InvalidParameterValueException
	 * @throws MissingRequiredParameterException
	 * @throws NoSuchElementInDatabaseException
	 * @throws WrongLoginPasswordException
	 * @throws InvalidArgumentException
	 * @see {@link #internalInternalNewInstance(User)} for more information on
	 *      the exceptions thrown.
	 */
	protected final Callable< R > internalNewInstance()
			throws MissingRequiredParameterException,
			InvalidParameterValueException, InternalErrorException,
			NoSuchElementInDatabaseException, WrongLoginPasswordException,
			InvalidArgumentException {
		
		loginName = getParameterAsString( CommandLineDictionary.LOGINNAME );
		loginPassword = getParameterAsString( CommandLineDictionary.LOGINPASSWORD );
		
		User user = getUserWhoIsPosting();
		if( !user.authenticatePassword( loginPassword ) )
			throw new WrongLoginPasswordException( loginName, loginPassword );
		
		return internalInternalNewInstance( user );
	};
	
	/**
	 * Returns an array of {@link String strings} that has the names of the
	 * parameters without whom the command cannot execute and also has the
	 * {@link String strings} "{@code loginName}" and "{@code loginPassword}".
	 * <p>
	 * All {@link PostCommand "POST commands"} must receive as parameters a
	 * login name and a login password referent to the {@link User user} who is
	 * trying to post. This {@link User user} will be authenticated before the
	 * actual posting operation.
	 * </p>
	 * 
	 * @return An array of {@link String strings} that has the names of the
	 *         parameters without whom the command cannot execute.
	 */
	protected final String[] getRequiredParameters() {
		
		String[] requiredParams = copyToNewArrayWith2MorePositions( getSpecificRequiredParameters() );
		requiredParams[requiredParams.length - 2] = "loginName";
		requiredParams[requiredParams.length - 1] = "loginPassword";
		return requiredParams;
	}
	
	
	
	// AUXILIARY UNIMPLEMENTED METHODS to be implemented in the factories
	
	/**
	 * Produces a command (returns it to the method {@link #newInstance()}).
	 * 
	 * @param userWhoIsPosting
	 *            The user who's login name was received in the parameters map.
	 * @throws InternalErrorException
	 *             If an internal error that wasn't supposed to happen happened.
	 * @throws MissingRequiredParameterException
	 *             If the received map does not contain one of the required
	 *             parameters for instantiating the command.
	 * @throws InvalidParameterValueException
	 *             If the received value for a required parameter was invalid.
	 * @throws InvalidArgumentException
	 *             If the received value for a required parameter was invalid.
	 * @throws NoSuchElementInDatabaseException
	 *             If an element expected to be in a certain database was not
	 *             found in it.
	 */
	protected abstract Callable< R > internalInternalNewInstance(
			User userWhoIsPosting ) throws InternalErrorException,
			MissingRequiredParameterException, InvalidParameterValueException,
			NoSuchElementInDatabaseException, InvalidArgumentException;
	
	/**
	 * Returns an array of {@link String}s that has the names of the parameters
	 * needed for producing a command (returns it to the
	 * {@link #getRequiredParameters()}).
	 * 
	 * @return An array of {@link String}s that has the names of the parameters
	 *         needed for producing a command (returns it to the
	 *         {@link #getRequiredParameters()}).
	 */
	protected abstract String[] getSpecificRequiredParameters();
	
	
	
	// AUXILIARY PRIVATE METHODS
	
	// used in the method internalNewInstance
	/**
	 * Returns the user who is posting.
	 * 
	 * @return The user who is posting.
	 * @throws NoSuchElementInDatabaseException
	 *             If there is no user in {@link #usersDatabase} whose username
	 *             is the login name receive in the parameters map. The message
	 *             of this exception is <i>«{login name} not found in
	 *             {@code postingUsersDatabase.getDatabaseName()}»</i>.
	 */
	private User getUserWhoIsPosting() throws NoSuchElementInDatabaseException {
		
		try
		{
			return usersDatabase.getElementByIdentification( loginName ).get();
		}
		catch( Exception e )
		{
			throw (NoSuchElementInDatabaseException)e;
		}
	}
	
	// used in the method getRequiredParameters
	/**
	 * Copies (by the same order) the {@link String}s stored in {@code array} to
	 * the first positions of a new array of {@link String}s that has 2 more
	 * entries than {@code array}. If {@code array} is {@code null}, it is
	 * returned a new empty array with 2 entries.
	 * 
	 * @param array
	 *            The array to be copied.
	 * @return A new array of {@link String strings} that has 2 more entries
	 *         than {@code array} and whose first positions store the entries of
	 *         {@code array}.
	 */
	private String[] copyToNewArrayWith2MorePositions( String[] array ) {
		
		if( array == null )
			return new String[2];
		
		String[] result = new String[array.length + 2];
		for( int index = 0; index < array.length; ++index )
			result[index] = array[index];
		return result;
	}
	
	
}
