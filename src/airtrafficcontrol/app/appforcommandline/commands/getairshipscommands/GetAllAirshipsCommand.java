package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.commands.exceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.users.User;

public class GetAllAirshipsCommand extends GetAirshipsCommand {

	public static class Factory implements CommandFactory {

		private final InMemoryAirshipDatabase airshipDatabase;

		public Factory(InMemoryAirshipDatabase airshipDatabase) {

			this.airshipDatabase = airshipDatabase;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) {

			return new GetAllAirshipsCommand(airshipDatabase, parameters);
		}
	}

	public GetAllAirshipsCommand(InMemoryAirshipDatabase airshipsDatabaseWhereToSearch,
			Map<String, String> parameters) {

		super(airshipsDatabaseWhereToSearch, parameters);
	}

	@Override
	protected void internalExecute() throws CommandException {

		Map<String, Airship> airships = airshipsDatabaseWhereToSearch.getAll();

		StringBuilder result = new StringBuilder();
		
		for (Airship airship : airships.values())
			result.append(airship).append("\n");
		
		this.result = result.toString();
	}

	@Override
	protected String[] getRequiredParameters() {
		
		return null;
	}

}
