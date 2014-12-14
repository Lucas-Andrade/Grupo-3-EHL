package appForConsole.commands.postCommands;

import java.util.Map;

import appForConsole.commands.AbstractCommand;
import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.exceptions.commandExecptions.InvalidParameterValueException;
import appForConsole.model.users.InMemoryUserDatabase;
import appForConsole.model.users.User;

/**
 * Class that extends {@link PostCommand} to add a new user into an User Database.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class PostUserCommand extends PostCommand<User> {

	private static final String USERNAME = "username";

	private static final String PASSWORD = "password";

	private static final String EMAIL = "email";

	private static final String FULLNAME = "fullName";
	
	private static final String LOGIN_NAME = "loginName";

	private static final String[] REQUIREDPARAMETERS = {USERNAME, PASSWORD, EMAIL};

	/**
	 * 
	 * Class that implements the {@link CommandFactory} factory
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	public static class Factory implements CommandFactory {

		InMemoryUserDatabase postingUsersDatabase;
		InMemoryUserDatabase postedUsersDatabase;

		public Factory(InMemoryUserDatabase postingUsersDatabase,
				InMemoryUserDatabase postedUsersDatabase) {

			this.postingUsersDatabase = postingUsersDatabase;
			this.postedUsersDatabase = postedUsersDatabase;
		}

		public Command newInstance(Map<String, String> parameters) {

			return new PostUserCommand(postingUsersDatabase, postedUsersDatabase, parameters);
		}
	}

	/**
	 * Constructor
	 * 
	 * store the container parameters
	 * 
	 * @param postingUsersDatabase
	 *            - Users Data who contains the user responsible to add the new user.
	 * @param postedUsersDatabase
	 *            - User Data who will contain the new user.
	 * @param parameters
	 *            - Container with parameters needed to proceed the search.
	 */
	public PostUserCommand(InMemoryUserDatabase postingUsersDatabase,
			InMemoryUserDatabase postedUsersDatabase, Map<String, String> parameters) {

		super(postingUsersDatabase, postedUsersDatabase, parameters);
	}

	/**
	 * @throws InvalidParameterValueException
	 * @throws InvalidArgumentException
	 *             Override of {@link AbstractCommand}
	 * 
	 *             execute the main action associated to this command
	 * @throws InvalidParameterValueException
	 */
	@Override
	protected void internalPostExecute() throws InvalidParameterValueException {

		String username = getParameterAsString(USERNAME);
		String password = getParameterAsString(PASSWORD);
		String email = getParameterAsString(EMAIL);
		String fullName = getParameterAsString(FULLNAME);

		User user = (fullName != null) ? new User(username, password, email, fullName) : new User(
				username, password, email);

		User postingUser = usersDatabase.getElementByIdentification(parameters.get(LOGIN_NAME));

		result = database.add(user, postingUser) ? "User was successfull added"
				: "User was not successfull added";
	}

	/**
	 * Override of {@link AbstractCommand}
	 * 
	 * Method responsible to get the Obligation parameters associated to this Command
	 * 
	 */
	@Override
	protected String[] getSpecificRequiredParameters() {

		return REQUIREDPARAMETERS;
	}
}