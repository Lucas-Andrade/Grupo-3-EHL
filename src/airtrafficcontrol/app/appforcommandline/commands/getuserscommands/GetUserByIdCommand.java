package airtrafficcontrol.app.appforcommandline.commands.getuserscommands;

import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.commands.exceptions.CommandException;
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

		Map<String, User> users = usersDatabaseWhereToSearch.getAll();

		
		
		for (User user : users.values())
			if (user.getIdentification().equals(this.parameters.get("username"))) {
				
				this.result = user.toString();
				break;
			}
	}

	@Override
	protected String[] getRequiredParameters() {
		
		return new String [] {"username"};
	}
}