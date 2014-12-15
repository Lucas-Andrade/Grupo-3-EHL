package appForConsole.commands.getUsersCommands;

import java.util.Map;

import appForConsole.commands.AbstractCommand;
import appForConsole.model.users.InMemoryUserDatabase;

public abstract class GetUsersCommand extends AbstractCommand {

	// INSTANCE FIELDS

	/**
	 * The users database where to perform the 'get' operation.
	 */
	protected InMemoryUserDatabase usersDatabase;

	// CONSTRUCTOR

	/**
	 * Stores the container {@code parameters}.
	 * 
	 * @param usersDatabase
	 *            The users database where to perform the 'get' operation.
	 * @param parameters
	 *            The container of the parameters name-value pairs.
	 */
	public GetUsersCommand(InMemoryUserDatabase usersDatabase, Map<String, String> parameters) {

		super(parameters);
		this.usersDatabase = usersDatabase;
	}
}