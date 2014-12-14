package appForConsole.commands.getAirshipsCommands;

import java.util.Map;

import appForConsole.commands.AbstractCommand;
import appForConsole.model.airships.InMemoryAirshipDatabase;

public abstract class GetAirshipsCommand extends AbstractCommand {

	// INSTANCE FIELDS

	/**
	 * The airships database where to perform the 'get' operation.
	 */
	protected InMemoryAirshipDatabase airshipsDatabase;

	// CONSTRUCTOR

	/**
	 * Stores the container {@code parameters}.
	 * 
	 * @param airshipsDatabaseWhereToSearch
	 *            The users database where to perform the 'get' operation.
	 * @param parameters
	 *            The container of the parameters name-value pairs.
	 */
	public GetAirshipsCommand(InMemoryAirshipDatabase airshipsDatabase,
			Map<String, String> parameters) {

		super(parameters);
		this.airshipsDatabase = airshipsDatabase;
	}
}