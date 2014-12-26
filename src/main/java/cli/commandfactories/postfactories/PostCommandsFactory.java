package main.java.cli.commandfactories.postfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.PlaceholdersAndParametersDictionary;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.exceptions.InternalErrorException;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
import main.java.cli.exceptions.factoryexceptions.CommandFactoryException;
import main.java.cli.exceptions.factoryexceptions.InvalidParameterValueException;
import main.java.cli.exceptions.factoryexceptions.WrongLoginPasswordException;
import main.java.cli.model.Database;
import main.java.cli.model.Element;
import main.java.cli.model.users.User;


/**
 * Abstract base class for all "post commands" factories.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 * @param <E>
 *            The type of the elements to be posted.
 * @param <R>
 *            The type parameter of the {@link Callable} instance returned by
 *            the method {@link #internalNewInstance()}.
 */
public abstract class PostCommandsFactory< E extends Element, R > extends
		StringsToCommandsFactory< R >
{
	
	// INSTANCE FIELDS
	
	/**
	 * The users' database that stores the user who's posting.
	 */
	private final Database< User > postingUsersDatabase;
	
	/**
	 * The database where to post the new element.
	 */
	protected final Database< E > databaseWhereToPost;
	
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
	 * @param postingUsersDatabase
	 *            The users database that stores the user who is posting.
	 * @param postedElementsDatabase
	 *            The database where to post the new element.
	 * @throws InvalidArgumentException
	 *             If either {@code commandsDescription},
	 *             {@code postingUsersDatabase} or
	 *             {@code postedElementsDatabase} are {@code null}.
	 */
	public PostCommandsFactory( String commandsDescription,
			Database< User > postingUsersDatabase,
			Database< E > postedElementsDatabase )
			throws InvalidArgumentException {
		
		super( commandsDescription );
		
		if( postingUsersDatabase == null || postedElementsDatabase == null )
			throw new InvalidArgumentException(
					"Cannot instantiate post factory with null databases." );
		
		this.postingUsersDatabase = postingUsersDatabase;
		this.databaseWhereToPost = postedElementsDatabase;
	}
	
	
	
	// IMPLEMENTATION OF METHODS INHERITED FROM StringsToCommandsFactory
	
	/**
	 * Produces a post command.
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
	 * @throws NoSuchElementInDatabaseException
	 *             If there is no user in {@link #postingUsersDatabase} whose
	 *             username is the login name receive in the parameters map. The
	 *             message of this exception is <i>«{login name} not found in
	 *             {@code postingUsersDatabase.getDatabaseName()}»</i>.
	 * @throws InternalErrorException
	 *             If an internal error that wasn't supposed to happen happened.
	 * @throws CommandFactoryException 
	 */
	protected final Callable< R > internalNewInstance()
			throws NoSuchElementInDatabaseException, InternalErrorException,
			CommandFactoryException {
		
		loginName = getParameterAsString( PlaceholdersAndParametersDictionary.LOGINNAME );
		loginPassword = getParameterAsString( PlaceholdersAndParametersDictionary.LOGINPASSWORD );
		
		User user = getUserWhoIsPosting();
		if( !user.authenticatePassword( loginPassword ) )
			throw new WrongLoginPasswordException( loginName, loginPassword );
		
		return postsInternalNewInstance( user );
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
	 * @throws CommandFactoryException
	 * @throws InternalErrorException
	 * @throws InvalidParameterValueException
	 *             If the received value for a required parameter was invalid.
	 */
	protected abstract Callable< R > postsInternalNewInstance(
			User userWhoIsPosting ) throws CommandFactoryException,
			InternalErrorException;
	
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
	 *             If there is no user in {@link #postingUsersDatabase} whose
	 *             username is the login name receive in the parameters map. The
	 *             message of this exception is <i>«{login name} not found in
	 *             {@code postingUsersDatabase.getDatabaseName()}»</i>.
	 */
	private User getUserWhoIsPosting() throws NoSuchElementInDatabaseException {
		
		try
		{
			return postingUsersDatabase.getElementByIdentification( loginName )
					.get();
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
