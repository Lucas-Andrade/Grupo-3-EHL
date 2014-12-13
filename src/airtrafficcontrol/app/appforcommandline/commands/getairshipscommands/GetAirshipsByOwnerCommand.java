package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.text.MessageFormat;
import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.*;
import airtrafficcontrol.app.appforcommandline.model.airships.*;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.RequiredParameterNotPresentException;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.WrongLoginPasswordException;
import airtrafficcontrol.app.appforcommandline.exceptions.databaseexceptions.NoSuchElementInDatabaseException;


/**
 * Class whose instances have the point to return an {@link Airship}
 * {@code List} by a given User owner
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsByOwnerCommand extends GetAirshipsCommand
{
	private static final String USERNAME = "username";

	/**
	 * Class that implements the {@link CommandFactory}, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory
	{
		private final InMemoryAirshipDatabase dataBase;

		public Factory( InMemoryAirshipDatabase dataBase )
		{
			this.dataBase = dataBase;
		}

		@Override
		public Command newInstance( Map<String, String> parameters )
		{
			return new GetAirshipsByOwnerCommand( dataBase, parameters );
		}
	}

	/**
	 * Create the {@code GetAirshipsByOwnerCommand}
	 * 
	 * @param airshipsDatabaseWhereToSearch
	 * @param parameters
	 */
	public GetAirshipsByOwnerCommand(
			InMemoryAirshipDatabase airshipsDatabaseWhereToSearch,
			Map<String, String> parameters )
	{
		super( airshipsDatabaseWhereToSearch, parameters );
	}

	/**
	 * Get a List of {@link Airship}s that the User, with the required USERNAME,
	 * have posted. Then the List of {@link Airship} info is passed to the
	 * {@link AbstractCommand} field {@code result}.
	 * 
	 * @throws NoSuchElementInDatabaseException
	 * @throws WrongLoginPasswordException
	 */
	@Override
	protected void internalExecute()
			throws RequiredParameterNotPresentException,
			NoSuchElementInDatabaseException
	{
		String axiliarUsername = parameters.get( USERNAME );

		result = listToString( airshipsDatabase.getAirshipsOfUser( axiliarUsername ), MessageFormat.format(
				"The User {0} still didn't create Airships",
				axiliarUsername ) );
	}

	/**
	 * Returns an array of {@code String}s that has the names of the parameters
	 * (to be validate in {@link AbstractCommand}) without whom the command
	 * cannot execute: USERNAME
	 * 
	 * @return An array of {@link String}s that has the names of the parameters
	 *         without whom the command cannot execute.
	 */
	@Override
	protected String[] getRequiredParameters()
	{
		return new String[]{USERNAME};
	}
}
