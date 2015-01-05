package main.java.cli.commandfactories.postfactories;


import java.util.Map;
import java.util.concurrent.Callable;
import main.java.cli.StringsDictionary;
import main.java.cli.commandfactories.CallablesFactory;
import main.java.cli.commandfactories.StringsToCommandsFactory;
import main.java.cli.commands.postcommands.PostUserCommand;
import main.java.cli.exceptions.InvalidArgumentException;
import main.java.cli.model.Database;
import main.java.cli.model.users.User;


/**
 * Class whose instances are {@link CallablesFactory factories} that produce
 * commands that post a user. Commands are {@link Callable} instances.
 * 
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostUserCommandsFactory extends PostCommandsFactory< User, String >
{
	
	// INSTANCE FIELDS
	
	/**
	 * The array of strings with the names of the parameters needed to produce
	 * the command.
	 */
	private final String[] requiredParametersNames;
	
	/**
	 * The properties of the user to be added.
	 */
	private String username;
	private String password;
	private String email;
	private String fullName;
	
	
	
	// CONSTRUCTOR
	/**
	 * Creates a new {@link PostUserCommandsFactory} that produces commands that
	 * post a user.
	 * 
	 * @param postingUsersDatabase
	 *            The database with the user who is posting.
	 * @param postedUsersDatabase
	 *            The database where to post the new user.
	 * @throws InvalidArgumentException
	 *             If {@code database==null}.
	 */
	public PostUserCommandsFactory( Database< User > postingUsersDatabase,
			Database< User > postedUsersDatabase )
			throws InvalidArgumentException {
		
		super( "Adds a new user.", postingUsersDatabase, postedUsersDatabase );
		
		this.requiredParametersNames = new String[]{
				StringsDictionary.USERNAME,
				StringsDictionary.PASSWORD,
				StringsDictionary.EMAIL };
	}
	
	
	
	// IMPLEMENTATION OF METHODS INHERITED FROM PostCommandsFactory
	
	/**
	 * Returns a command that posts a user.
	 * 
	 * @return A command that posts a user.
	 */
	@Override
	protected Callable< String > postsInternalNewInstance(User userWhoIsPosting) {
		
		getValuesOfTheParametersMap();
		
		try
		{
			return new PostUserCommand( username, password, email, fullName,
					databaseWhereToPost, userWhoIsPosting );
		}
		catch( InvalidArgumentException e )
		{// never happens cause databaseWhereToPost is not null
			return null;
		}
		
		
	}
	
	/**
	 * Returns an array of strings with name of the parameters needed to produce
	 * the command: the name of the parameters that contain the user's
	 * properties.
	 * 
	 * @return An array of strings with the name of the parameters that contain
	 *         the user's properties.
	 */
	@Override
	protected String[] getSpecificRequiredParameters() {
		
		return requiredParametersNames;
	}
	
	
	
	// AUXILIARY PRIVATE METHODS - used in the method postsInternalNewInstance()
	
	/**
	 * Sets the value of the user's properties fields with the values received
	 * in the parameters map.
	 * <p>
	 * If this method is called inside {@link #internalNewInstance(Map)} and
	 * this one is called inside
	 * {@link StringsToCommandsFactory#newInstance(Map)}, it is guaranteed that
	 * the fields {@link #username}, {@link #password} and {@link #email} are
	 * non-{@code null} after this method finishes its job.
	 * </p>
	 */
	private void getValuesOfTheParametersMap() {
		
		username = getParameterAsString( StringsDictionary.USERNAME );
		password = getParameterAsString( StringsDictionary.PASSWORD );
		email = getParameterAsString( StringsDictionary.EMAIL );
		fullName = getParameterAsString( StringsDictionary.FULLNAME );
	}
	
	
}
