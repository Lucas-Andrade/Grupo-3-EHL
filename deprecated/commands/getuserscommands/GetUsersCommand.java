package airtrafficcontrol.deprecated.appforcommandline.commands.getuserscommands;

import java.util.Map;
import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;


import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;

public abstract class GetUsersCommand extends AbstractCommand {

	// INSTANCE FIELDS
	/**
	 * The users database where to perform the 'get' operation.
	 */
	protected InMemoryUserDatabase usersDatabaseWhereToSearch;

	// CONSTRUCTOR
	/**
	 * Stores the container {@code parameters}.
	 * 
	 * @param usersDatabaseWhereToSearch
	 *            The users database where to perform the 'get' operation.
	 * @param parameters
	 *            The container of the parameters name-value pairs.
	 */
	public GetUsersCommand(InMemoryUserDatabase usersDatabaseWhereToSearch,
			Map<String, String> parameters) {

		super(parameters);
		this.usersDatabaseWhereToSearch = usersDatabaseWhereToSearch;
	}
}
