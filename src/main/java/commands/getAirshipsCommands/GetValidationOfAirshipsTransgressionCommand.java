package main.java.commands.getAirshipsCommands;

import java.util.Collection;
import java.util.Map;

import main.java.commands.AbstractCommand;
import main.java.commands.Command;
import main.java.commands.CommandFactory;
import main.java.exceptions.commandExecptions.CommandException;
import main.java.model.airships.Airship;
import main.java.model.airships.AirshipPredicates;
import main.java.model.airships.InMemoryAirshipDatabase;

/**
 * Class that extends {@link GetAirshipsCommand} to obtain the information of airships who
 * transgressed the corridor.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetValidationOfAirshipsTransgressionCommand extends GetAirshipsCommand {

	/**
	 * 
	 * Class that implements the {@link CommandFactory} factory
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	public static class Factory implements CommandFactory {

		InMemoryAirshipDatabase airshipsDatabase;

		public Factory(InMemoryAirshipDatabase airshipsDatabase) {

			this.airshipsDatabase = airshipsDatabase;
		}

		public Command newInstance(Map<String, String> parameters) {

			return new GetValidationOfAirshipsTransgressionCommand(airshipsDatabase, parameters);
		}
	}

	/**
	 * Constructor
	 * 
	 * store the container parameter
	 * 
	 * @param airshipsDatabaseWhereToSearch
	 *            - airship Database target of search
	 * @param parameters
	 *            - Container with parameters needed to proceed the search.
	 */
	public GetValidationOfAirshipsTransgressionCommand(
			InMemoryAirshipDatabase airshipsDatabaseWhereToSearch, Map<String, String> parameters) {

		super(airshipsDatabaseWhereToSearch, parameters);
	}

	/**
	 * Override of {@link AbstractCommand}
	 * 
	 * execute the main action associated to this command
	 * 
	 */
	@Override
	protected void internalExecute() throws CommandException {

		Collection<Airship> aircraft = airshipsDatabase
				.getAirshipsThat(new AirshipPredicates.IsTransgressing());

		if (aircraft.isEmpty()) {

			result = "There are no transgressions recorded";
			return;
		}

		StringBuilder flightIDs = new StringBuilder();

		for (Airship element : aircraft)
			flightIDs.append("\n Airship flightID: ").append(element.getIdentification());

		result = flightIDs.toString();
	}

	/**
	 * Override of {@link AbstractCommand}
	 * 
	 * Method responsible to get the Obligation parameters associated to this Command
	 * 
	 */
	@Override
	protected String[] getRequiredParameters() {

		return null;
	}
}