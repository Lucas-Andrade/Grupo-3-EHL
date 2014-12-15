package appForConsole.commands.getAirshipsCommands;

import java.util.Collection;
import java.util.Map;

import appForConsole.commands.AbstractCommand;
import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.exceptions.commandExecptions.CommandException;
import appForConsole.model.airships.Airship;
import appForConsole.model.airships.InMemoryAirshipDatabase;

/**
 * Class whose instances represent the command reponsible for obtaining all the {@link Airship
 * aiships} stored in an {@link InMemoryAirshipDatabase airship database}.</br></br>
 * 
 * This command instances are created and used according to the AbstratFactory design pattern,
 * making use of a class {@link Factory} that implements the {@link CommandFactory} Interface whose
 * only method is the {@link CommandFactory#newInstance(Map) newInstance(Map)} method that will
 * allow new command instances to be created.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAllAirshipsCommand extends GetAirshipsCommand {

	/**
	 * Inner class Factory that implements the {@link CommandFactory} Interface and its responsible
	 * for creating new instances of {@code GetAllAirshipsCommand}, according to the AbstratFactory
	 * design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * {@code airshipDatabase} - Database where all airships are stored.
		 */
		private final InMemoryAirshipDatabase airshipDatabase;

		/**
		 * Creates a Factory for {@link GetAllAirshipsCommand this} command.
		 * 
		 * @param airshipDatabase
		 *            - The database where all the airships are stored.
		 */
		public Factory(InMemoryAirshipDatabase airshipDatabase) {

			this.airshipDatabase = airshipDatabase;
		}

		/**
		 * Implementation of the {@link CommandFactory#newInstance(Map) newInstance(Map)} method. It
		 * will be responsible for creating intances of {@link GetAllAirshipsCommand this} command.
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {

			return new GetAllAirshipsCommand(airshipDatabase, parameters);
		}
	}

	/**
	 * Creates a {@code GetAllAirshipsCommand}.
	 * 
	 * @param airshipsDatabase
	 *            - The database where all the airships are stored.
	 * @param parameters
	 */
	public GetAllAirshipsCommand(InMemoryAirshipDatabase airshipsDatabase,
			Map<String, String> parameters) {

		super(airshipsDatabase, parameters);
	}

	/**
	 * Implementation of the {@link AbstractCommand#internalExecute() internalExecute()} method from
	 * the {@link AbstractCommand}.</br></br>
	 * 
	 * This implementation will obtain all the {@link Airship aiships} stored in the
	 * {@code airshipsDatabase} and save their information in a String. If the database is empty the
	 * String will have an error message.
	 */
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

	/**
	 * @return the {@code requiredParameters}.
	 */
	@Override
	protected String[] getRequiredParameters() {

		return null;
	}
}