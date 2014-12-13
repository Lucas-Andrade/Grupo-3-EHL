package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.AbstractCommand;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;
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

	// UNIMPLEMENTED METHODS

	/**
	 * Performs the specific action associated with this command.
	 * @throws NoSuchElementInDatabaseException 
	 */
	@Override
	protected abstract void internalExecute() throws CommandException, NoSuchElementInDatabaseException;

	/**
	 * Returns an array of {@link String strings} that has the names of the parameters without whom
	 * the command cannot execute.
	 * 
	 * @return An array of {@link String strings} that has the names of the parameters without whom
	 *         the command cannot execute.
	 */
	@Override
	protected abstract String[] getRequiredParameters();

}
