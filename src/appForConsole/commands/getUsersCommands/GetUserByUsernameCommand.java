package appForConsole.commands.getUsersCommands;

import java.util.Map;

import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.exceptions.commandExecptions.CommandException;
import appForConsole.model.users.InMemoryUserDatabase;
import appForConsole.model.users.User;

public class GetUserByUsernameCommand extends GetUsersCommand {

	private static final String USERNAME = "username";
	
	private static final String[] requiredParameters = {USERNAME};

	public static class Factory implements CommandFactory {

		private final InMemoryUserDatabase userDatabase;

		public Factory(InMemoryUserDatabase userDatabase) {

			this.userDatabase = userDatabase;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) {

			return new GetUserByUsernameCommand(userDatabase, parameters);
		}
	}

	public GetUserByUsernameCommand(InMemoryUserDatabase usersDatabase,
			Map<String, String> parameters) {

		super(usersDatabase, parameters);
	}

	@Override
	protected void internalExecute() throws CommandException {

		User user = usersDatabase.getElementByIdentification(this.parameters
				.get(USERNAME));

		if (user == null) {

			this.result = "User Not Found\n";
			return;
		}

		this.result = user.toString();
	}

	@Override
	protected String[] getRequiredParameters() {

		return requiredParameters;
	}
}