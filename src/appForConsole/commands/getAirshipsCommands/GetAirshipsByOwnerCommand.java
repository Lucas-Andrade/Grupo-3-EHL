package appForConsole.commands.getAirshipsCommands;

import java.util.Map;

import appForConsole.commands.AbstractCommand;
import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.exceptions.commandExecptions.MissingRequiredParameterException;
import appForConsole.exceptions.commandExecptions.WrongLoginPasswordException;
import appForConsole.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import appForConsole.model.Param;
import appForConsole.model.airships.Airship;
import appForConsole.model.airships.InMemoryAirshipDatabase;
import appForConsole.model.users.InMemoryUserDatabase;
import appForConsole.model.users.User;

/**
 * Class whose instances represent a command, that have the point to get all {@link Airship}s info
 * by a given {@link User}. To create this instances, exist a subclass {@link Factory} that
 * implements the {@link CommandFactory}, according to the AbstratFactory design pattern.
 * 
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipsByOwnerCommand extends GetAirshipsCommand {

	/**
	 * Class that implements the {@link CommandFactory}, according to the AbstratFactory design
	 * pattern.
	 */
	public static class Factory implements CommandFactory {

		private final InMemoryAirshipDatabase airshipDataBase;
		private final InMemoryUserDatabase userDatabase;

		public Factory(InMemoryAirshipDatabase airshipDatabase, InMemoryUserDatabase userDatabase) {

			this.airshipDataBase = airshipDatabase;
			this.userDatabase = userDatabase;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) {

			return new GetAirshipsByOwnerCommand(airshipDataBase, userDatabase, parameters);
		}
	}

	private final InMemoryUserDatabase userDatabase;

	/**
	 * Create a {@code GetAirshipsByOwnerCommand}
	 * 
	 * @param airshipsDatabase
	 * @param parameters
	 */
	public GetAirshipsByOwnerCommand(InMemoryAirshipDatabase airshipsDatabase,
			InMemoryUserDatabase userDatabase, Map<String, String> parameters) {

		super(airshipsDatabase, parameters);
		this.userDatabase = userDatabase;
	}

	/**
	 * Get a List of {@link Airship}s that the {@link User}, with the required {@code USERNAME},
	 * have posted. Then the List of {@link Airship} info is passed to the {@link AbstractCommand}
	 * field {@code result}. If this list is empty then is returned a message with the respective
	 * info.
	 * 
	 * @throws NoSuchElementInDatabaseException
	 * @throws WrongLoginPasswordException
	 */
	@Override
	protected void internalExecute() throws MissingRequiredParameterException,
			NoSuchElementInDatabaseException {

		String axiliarUsername = parameters.get(Param.OWNER);

		if (userDatabase.getElementByIdentification(axiliarUsername) == null) {
			result = "User Not Found\n";
			return;
		}

		result = listToString(airshipsDatabase.getAirshipsOfUser(axiliarUsername), "");
	}

	/**
	 * Returns an array of {@code String}s that has the names of the parameters (to be validate in
	 * {@link AbstractCommand}) without whom the command cannot execute: USERNAME
	 * 
	 * @return An array of {@link String}s that has the names of the parameters without whom the
	 *         command cannot execute.
	 */
	@Override
	protected String[] getRequiredParameters() {

		return new String[] {Param.OWNER};
	}
}