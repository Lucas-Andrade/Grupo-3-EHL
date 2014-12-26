package airtrafficcontrol.deprecated.appforcommandline.commands.getuserscommands;

import java.util.Map;
import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.model.users.InMemoryUserDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class GetAllUsersCommand extends GetUsersCommand {

	public static class Factory implements CommandFactory {

		private final InMemoryUserDatabase userDatabase;

		public Factory(InMemoryUserDatabase userDatabase) {

			this.userDatabase = userDatabase;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) {

			return new GetAllUsersCommand(userDatabase, parameters);
		}
	}
	
	public GetAllUsersCommand(InMemoryUserDatabase usersDatabaseWhereToSearch,
			Map<String, String> parameters) {

		super(usersDatabaseWhereToSearch, parameters);
	}

	@Override
	protected void internalExecute() {

		Map<String, User> users = usersDatabaseWhereToSearch.getAll();
		
		StringBuilder result = new StringBuilder();
		
		for(User user : users.values())
			result.append(user).append("\n");
		
		this.result = result.toString();
	}

	@Override
	protected String[] getRequiredParameters() {

		return null;
	}
}