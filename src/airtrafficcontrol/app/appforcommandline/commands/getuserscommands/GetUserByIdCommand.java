package airtrafficcontrol.app.appforcommandline.commands.getuserscommands;

import java.util.Collection;
import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class GetUserByIdCommand extends GetUsersCommand {

	public static class Factory implements CommandFactory {

		private final InMemoryUserDatabase userDatabase;

		public Factory(InMemoryUserDatabase userDatabase) {

			this.userDatabase = userDatabase;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) {

			return new GetUserByIdCommand(userDatabase, parameters);
		}
	}

	public GetUserByIdCommand(InMemoryUserDatabase usersDatabaseWhereToSearch,
			Map<String, String> parameters) {

		super(usersDatabaseWhereToSearch, parameters);
	}

	@Override
	protected void internalExecute() throws CommandException {

		Collection<User> users = usersDatabase.getAll().values();

		for (User user : users)

			if (user.getIdentification().equals(this.parameters.get("username"))) {

				this.result = user.toString();
				return;
			}

		this.result = "User Not Found";
	}

	@Override
	protected String[] getRequiredParameters() {

		return new String[] {"username"};
	}
}