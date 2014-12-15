package appForConsole.commands.getAirshipsCommands;

import java.util.Map;

import appForConsole.commands.AbstractCommand;
import appForConsole.commands.Command;
import appForConsole.commands.CommandFactory;
import appForConsole.model.airships.Airship;
import appForConsole.model.airships.InMemoryAirshipDatabase;

/**
 * Class whose instances represent the command reponsible for obtaining a specific {@link Airship}
 * stored in an {@link InMemoryAirshipDatabase airship database} given its {@link Airship#flightId
 * flightId}.</br></br>
 * 
 * This command instances are created and used according to the AbstratFactory design pattern,
 * making use of a class {@link Factory} that implements the {@link CommandFactory} Interface whose
 * only method is the {@link CommandFactory#newInstance(Map) newInstance(Map)} method that will
 * allow new command instances to be created.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetAirshipByIdCommand extends GetAirshipsCommand {

	/**
	 * {@code ID} - The requered parameter correnponding to the {@link Airship airship's}
	 * {@link Airship#flightId flightId}.
	 */
	private static final String ID = "flightId";

	/**
	 * {@code requiredParameters} - String containing all the required parameters for this command.
	 */
	private static final String[] requiredParameters = {ID};

	/**
	 * Inner class Factory that implements the {@link CommandFactory} Interface and its responsible
	 * for creating new instances of {@code GetAirshipByIdCommand}, according to the AbstratFactory
	 * design pattern.
	 */
	public static class Factory implements CommandFactory {

		/**
		 * {@code airshipDatabase} - Database where all airships are stored.
		 */
		private final InMemoryAirshipDatabase airshipDatabase;

		/**
		 * Creates a Factory for {@link GetAirshipByIdCommand this} command.
		 * 
		 * @param airshipDatabase
		 *            - The database where all the airships are stored.
		 */
		public Factory(InMemoryAirshipDatabase airshipDatabase) {

			this.airshipDatabase = airshipDatabase;
		}

		/**
		 * Implementation of the {@link CommandFactory#newInstance(Map) newInstance(Map)} method. It
		 * will be responsible for creating intances of {@link GetAirshipByIdCommand this} command.
		 */
		@Override
		public Command newInstance(Map<String, String> parameters) {

			return new GetAirshipByIdCommand(airshipDatabase, parameters);
		}
	}

	/**
	 * Creates a {@code GetAirshipByIdCommand}.
	 * 
	 * @param airshipsDatabase
	 *            - The database where all the airships are stored.
	 * @param parameters
	 */
	public GetAirshipByIdCommand(InMemoryAirshipDatabase airshipsDatabase,
			Map<String, String> parameters) {

		super(airshipsDatabase, parameters);
	}

	/**
	 * Implementation of the {@link AbstractCommand#internalExecute() internalExecute()} method from
	 * the {@link AbstractCommand}.</br></br>
	 * 
	 * This implementation will search the airships database for the airship corresponding to a
	 * given {@link Airship#flightId flightId} and save its information in a String. If no airship
	 * is found for the given {@code flightID} the String will have an error message.
	 */
	@Override
	protected void internalExecute() {

		Airship airship = airshipsDatabase.getElementByIdentification(this.parameters.get(ID));

		if (airship == null) {

			this.result = "Airship Not Found\n";
			return;
		}

		this.result = airship.toString();
	}

	/**
	 * @return the {@code requiredParameters}.
	 */
	@Override
	protected String[] getRequiredParameters() {

		return requiredParameters;
	}
}