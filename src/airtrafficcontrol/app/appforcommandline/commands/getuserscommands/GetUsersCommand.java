package airtrafficcontrol.app.appforcommandline.commands.getuserscommands;


import java.util.Map;
import airtrafficcontrol.app.appforcommandline.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;
import airtrafficcontrol.app.appforcommandline.commands.exceptions.CommandException;


public abstract class GetUsersCommand extends AbstractCommand
{
	
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
	public GetUsersCommand( InMemoryUserDatabase usersDatabaseWhereToSearch,
			Map< String, String > parameters ) {
		super( parameters );
		this.usersDatabaseWhereToSearch = usersDatabaseWhereToSearch;
	}

	
	
	// UNIMPLEMENTED METHODS
	
	/**
	 * Performs the specific action associated with this command.
	 */
	@Override
	protected abstract void internalExecute() throws CommandException;
	
	/**
	 * Returns an array of {@link String strings} that has the names of the
	 * parameters without whom the command cannot execute.
	 * 
	 * @return An array of {@link String strings} that has the names of the
	 *         parameters without whom the command cannot execute.
	 */
	@Override
	protected abstract String[] getRequiredParameters();
	
}
