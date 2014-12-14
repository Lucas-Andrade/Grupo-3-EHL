package appForConsole.commands.postCommands;

import java.text.MessageFormat;
import java.util.Map;

import appForConsole.commands.AbstractCommand;
import appForConsole.exceptions.commandExecptions.CommandException;
import appForConsole.exceptions.commandExecptions.InvalidParameterValueException;
import appForConsole.exceptions.commandExecptions.WrongLoginPasswordException;
import appForConsole.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import appForConsole.model.Database;
import appForConsole.model.Element;
import appForConsole.model.users.InMemoryUserDatabase;
import appForConsole.model.users.User;

public abstract class PostCommand<T extends Element> extends AbstractCommand {

	// INSTANCE FIELDS

	private static final String LOGIN_NAME = "loginName";
	private static final String LOGIN_PASSWORD = "loginPassword";

	/**
	 * The users' database that stores the user who's posting.
	 */
	protected InMemoryUserDatabase usersDatabase;

	/**
	 * The database where to store the posted element.
	 */
	protected Database<T> database;

	// CONSTRUCTOR

	/**
	 * Stores the containers {@code parameters}, {@code usersDatabase} and {@code database}.
	 * 
	 * @param usersDatabase
	 *            The users' database that stores the user who is posting.
	 * @param database
	 *            The database where to store the posted element.
	 * @param parameters
	 *            The container of the parameters name-value pairs.
	 */
	public PostCommand(InMemoryUserDatabase usersDatabase, Database<T> database,
			Map<String, String> parameters) {

		super(parameters);
		this.usersDatabase = usersDatabase;
		this.database = database;
	}

	// IMPLEMENTATION OF INHERITED METHODS

	/**
	 * Performs the specific action associated with this command, inclusively authenticates the user
	 * who is posting.
	 * 
	 * @throws CommandException
	 * @throws NoSuchElementInDatabaseException
	 * @throws WrongLoginPasswordException
	 */
	@Override
	protected final void internalExecute() throws CommandException,
			NoSuchElementInDatabaseException, WrongLoginPasswordException {

		authenticateUser();
		internalPostExecute();
	};

	/**
	 * Returns an array of {@link String strings} that has the names of the parameters without whom
	 * the command cannot execute and also has the {@link String strings} "{@code loginName}" and "
	 * {@code loginPassword}".
	 * <p>
	 * All {@link PostCommand "POST commands"} must receive as parameters a login name and a login
	 * password referent to the {@link User user} who is trying to post. This {@link User user} will
	 * be authenticated before the actual posting operation.
	 * </p>
	 * 
	 * @return An array of {@link String strings} that has the names of the parameters without whom
	 *         the command cannot execute.
	 */
	@Override
	protected final String[] getRequiredParameters() {

		String[] requiredParams = copyToNewArrayWith2MorePositions(getSpecificRequiredParameters());
		
		requiredParams[requiredParams.length - 2] = LOGIN_NAME;
		requiredParams[requiredParams.length - 1] = LOGIN_PASSWORD;
		
		return requiredParams;
	}

	// UNIMPLEMENTED METHODS

	/**
	 * Performs the specific action associated with this command.
	 */
	protected abstract void internalPostExecute() throws CommandException;

	/**
	 * Returns an array of {@link String strings} that has the names of the parameters without whom
	 * the command cannot execute.
	 * 
	 * @return An array of {@link String strings} that has the names of the parameters without whom
	 *         the command cannot execute.
	 */
	protected abstract String[] getSpecificRequiredParameters();

	// AUXILIAR PRIVATE METHODS

	/**
	 * Checks whether the required parameters (obtained using the method
	 * {@link #getRequiredParameters()}) were received.
	 * 
	 * @throws InvalidParameterValueException
	 *             If the value in {@link AbstractCommand#parameters parameters} corresponding to "
	 *             {@code loginName}" or to " {@code loginPassword}" is {@code null}.
	 * @throws NoSuchElementInDatabaseException
	 *             If the value corresponding to "{@code loginName}" in
	 *             {@link AbstractCommand#parameters parameters} is not in the
	 *             {@link #usersDatabase users database}.
	 * @throws WrongLoginPasswordException
	 *             If the value corresponding to "{@code loginPassword}" is not the password for the
	 *             value corresponding to "{@code loginName} ".
	 */
	private void authenticateUser() throws InvalidParameterValueException,
			NoSuchElementInDatabaseException, WrongLoginPasswordException {

		String theLoginName = parameters.get("loginName");
		if (theLoginName == null)
			throw new InvalidParameterValueException("loginName", null);
		
		String theLoginPassword = parameters.get("loginPassword");
		if (theLoginPassword == null)
			throw new InvalidParameterValueException("loginPassword", null);

		User theUser = usersDatabase.getElementByIdentification(parameters.get("loginName"));
		if (theUser == null)
			throw new NoSuchElementInDatabaseException(MessageFormat.format(
					"{0} not in the users database.", theLoginName));

		if (!theUser.authenticatePassword(theLoginPassword))
			throw new WrongLoginPasswordException(theLoginName, theLoginPassword);
	}

	/**
	 * Copies (in the original order) the {@link String strings} stored in {@code array} to the
	 * first positions of a new array of {@link String strings} whose length is 2 units larger than
	 * {@code array}.
	 * 
	 * @param array
	 *            The array to be copied.
	 * @return A new array of {@link String strings} whose length is 2 units larger than
	 *         {@code array} and whose first positions store the entries of {@code array}.
	 */
	private String[] copyToNewArrayWith2MorePositions(String[] array) {

		String[] result = new String[array.length + 2];
		
		for (int index = 0; index < array.length; ++index)
			result[index] = array[index];
		
		return result;
	}
}