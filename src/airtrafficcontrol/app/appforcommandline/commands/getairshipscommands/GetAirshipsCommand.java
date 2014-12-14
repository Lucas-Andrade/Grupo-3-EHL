package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;

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