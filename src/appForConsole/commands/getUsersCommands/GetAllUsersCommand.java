package appForConsole.commands.getUsersCommands;

import java.util.Collection;
import java.util.Map;

import appForConsole.commands.AbstractCommand;
import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.exceptions.commandExecptions.CommandException;
import appForConsole.model.users.InMemoryUserDatabase;
import appForConsole.model.users.User;

/**
 * Class whose instances represent the command responsible for obtaining all the {@link User users}
 * stored in an {@link InMemoryUserDatabase user database}.</br></br>
 * 
 * This command instances are created and used according to the AbstratFactory design pattern,
 * making use of a class {@link Factory} that implements the {@link CommandFactory} Interface whose
 * only method is the {@link CommandFactory#newInstance(Map) newInstance(Map)} method that will
 * allow new command instances to be created.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllUsersCommand extends GetUsersCommand {

	/**
	 * Inner class Factory that implements the {@link CommandFactory} Interface and its responsible
	 * for creating new instances of {@code GetAllUsersCommand}, according to the AbstratFactory
	 * design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * {@code userDatabase} - Database where all the users are stored.
		 */
		private final InMemoryUserDatabase userDatabase;

		/**
		 * Creates a Factory for {@link GetAllUsersCommand this} command.
		 * 
		 * @param userDatabase
		 *            - The database where all the users are stored.
		 */
		public Factory(InMemoryUserDatabase userDatabase) {

			this.userDatabase = userDatabase;
		}

		/**
		 * Implementation of the {@link CommandFactory#newInstance(Map) newInstance(Map)} method. It
		 * will be responsible for creating intances of {@link GetAllUsersCommand this} command.
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {

			return new GetAllUsersCommand(userDatabase, parameters);
		}
	}

	/**
	 * Creates a {@code GetAllUsersCommand}.
	 * 
	 * @param userDatabase
	 *            - The database where all the users are stored.
	 * @param parameters
	 */
	public GetAllUsersCommand(InMemoryUserDatabase userDatabase, Map<String, String> parameters) {

		super(userDatabase, parameters);
	}

	/**
	 * Implementation of the {@link AbstractCommand#internalExecute() internalExecute()} method from
	 * the {@link AbstractCommand}.</br></br>
	 * 
	 * This implementation will obtain all the {@link User users} stored in the {@code userDatabase}
	 * and save their information in a String. If the database is empty the String will have an
	 * error message.
	 */
	@Override
	protected void internalExecute() throws CommandException {

		Collection<User> users = usersDatabase.getAll().values();

		StringBuilder result = new StringBuilder();

		for (User user : users)
			result.append(user).append("\n");

		this.result = result.toString();
	}

	/**
	 * @return the {@code requiredParameters}.
	 */
	@Override
	protected String[] getRequiredParameters() {

		return null;
	}
}