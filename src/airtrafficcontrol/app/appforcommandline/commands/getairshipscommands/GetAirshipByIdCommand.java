package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.commands.exceptions.CommandException;
import airtrafficcontrol.app.appforcommandline.model.airships.Airship;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;

public class GetAirshipByIdCommand extends GetAirshipsCommand {

	public static class Factory implements CommandFactory {

		private final InMemoryAirshipDatabase airshipDatabase;

		public Factory(InMemoryAirshipDatabase airshipDatabase) {

			this.airshipDatabase = airshipDatabase;
		}

		@Override
		public Command newInstance(Map<String, String> parameters) {

			return new GetAirshipByIdCommand(airshipDatabase, parameters);
		}
	}

	public GetAirshipByIdCommand(InMemoryAirshipDatabase airshipsDatabaseWhereToSearch,
			Map<String, String> parameters) {

		super(airshipsDatabaseWhereToSearch, parameters);
	}

	@Override
	protected void internalExecute() throws CommandException {

		Map<String, Airship> airships = airshipsDatabaseWhereToSearch.getAll();

		for (Airship airship : airships.values())
			if (airship.getIdentification().equals(this.parameters.get("flightId"))) {

				this.result = airship.toString();
				break;
			}
	}

	@Override
	protected String[] getRequiredParameters() {

		return new String[] {"flightId"};
	}
}