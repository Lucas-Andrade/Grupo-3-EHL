package airtrafficcontrol.app.appforcommandline.commands.getairshipscommands;

import java.util.Collection;
import java.util.Map;

import airtrafficcontrol.app.appforcommandline.commands.Command;
import airtrafficcontrol.app.appforcommandline.commands.CommandFactory;
import airtrafficcontrol.app.appforcommandline.exceptions.commandexceptions.CommandException;
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

		Collection<Airship> airships = airshipsDatabase.getAll().values();

		for (Airship airship : airships)
			if (airship.getIdentification().equals(this.parameters.get("flightId"))) {

				this.result = airship.toString();
				return;
			}

		this.result = "Airship Not Found";
	}

	@Override
	protected String[] getRequiredParameters() {

		return new String[] {"flightId"};
	}
}