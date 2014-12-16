package main.java.commands.getUsersCommands;

import java.util.Map;

import main.java.commands.AbstractCommand;
import main.java.commands.Command;
import main.java.commands.CommandFactory;
import main.java.exceptions.commandExecptions.CommandException;
import main.java.model.users.InMemoryUserDatabase;
import main.java.model.users.User;

/**
 * Class whose instances represent the command reponsible for obtaining a specific {@link User}
 * stored in an {@link InMemoryUserDatabase user database} given its {@link User#username username}
 * .</br></br>
 * 
 * This command instances are created and used according to the AbstratFactory design pattern,
 * making use of a class {@link Factory} that implements the {@link CommandFactory} Interface whose
 * only method is the {@link CommandFactory#newInstance(Map) newInstance(Map)} method that will
 * allow new command instances to be created.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetUserByUsernameCommand extends GetUsersCommand {

	/**
	 * {@code ID} - The requered parameter correnponding to the {@link User user's}
	 * {@link User#username username}.
	 */
	private static final String USERNAME = "username";

	/**
	 * {@code requiredParameters} - String containing all the required parameters for this command.
	 */
	private static final String[] requiredParameters = {USERNAME};

	/**
	 * Inner class Factory that implements the {@link CommandFactory} Interface and its responsible
	 * for creating new instances of {@code GetUserByUsernameCommand}, according to the
	 * AbstratFactory design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * {@code userDatabase} - Database where all the users are stored.
		 */
		private final InMemoryUserDatabase userDatabase;

		/**
		 * Creates a Factory for {@link GetUserByUsernameCommand this} command.
		 * 
		 * @param userDatabase
		 *            - The database where all the users are stored.
		 */
		public Factory(InMemoryUserDatabase userDatabase) {

			this.userDatabase = userDatabase;
		}

		/**
		 * Implementation of the {@link CommandFactory#newInstance(Map) newInstance(Map)} method. It
		 * will be responsible for creating intances of {@link GetUserByUsernameCommand this}
		 * command.
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {

			return new GetUserByUsernameCommand(userDatabase, parameters);
		}
	}

	/**
	 * Creates a {@code GetUserByUsernameCommand}.
	 * 
	 * @param userDatabase
	 *            - The database where all the users are stored.
	 * @param parameters
	 */
	public GetUserByUsernameCommand(InMemoryUserDatabase usersDatabase,
			Map<String, String> parameters) {

		super(usersDatabase, parameters);
	}

	/**
	 * Implementation of the {@link AbstractCommand#internalExecute() internalExecute()} method from
	 * the {@link AbstractCommand}.</br></br>
	 * 
	 * This implementation will search the users database for the user corresponding to a
	 * given {@link User#username username} and save its information in a String. If no user
	 * is found for the given {@code username} the String will have an error message.
	 */
	@Override
	protected void internalExecute() throws CommandException {

		User user = usersDatabase.getElementByIdentification(this.parameters.get(USERNAME));

		if (user == null) {

			this.result = "User Not Found\n";
			return;
		}

		this.result = user.toString();
	}

	/**
	 * @return the {@code requiredParameters}.
	 */
	@Override
	protected String[] getRequiredParameters() {

		return requiredParameters;
	}
}