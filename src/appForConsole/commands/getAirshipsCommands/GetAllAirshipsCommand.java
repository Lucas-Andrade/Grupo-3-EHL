package appForConsole.commands.getAirshipsCommands;

import java.util.Collection;
import java.util.Map;

import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.exceptions.commandExecptions.CommandException;
import appForConsole.model.airships.Airship;
import appForConsole.model.airships.InMemoryAirshipDatabase;

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

	public GetAllAirshipsCommand(InMemoryAirshipDatabase airshipsDatabase,
			Map<String, String> parameters) {

		super(airshipsDatabase, parameters);
	}

	@Override
	protected void internalExecute() throws CommandException {

		Collection<Airship> airships = airshipsDatabase.getAll().values();

		if (airships.size() == 0) {
			
			result = "Airships Database is Empty";
			return;
		}
		
		StringBuilder result = new StringBuilder();
		
		for (Airship airship : airships)
			result.append(airship).append("\n");
		
		this.result = result.toString();
	}

	@Override
	protected String[] getRequiredParameters() {

		return null;
	}
}