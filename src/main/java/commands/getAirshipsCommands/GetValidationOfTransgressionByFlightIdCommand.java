package main.java.commands.getAirshipsCommands;

import java.util.Map;

import main.java.commands.AbstractCommand;
import main.java.commands.Command;
import main.java.commands.CommandFactory;
import main.java.exceptions.commandExecptions.CommandException;
import main.java.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import main.java.model.airships.InMemoryAirshipDatabase;

/**
 * Class that extends {@link GetAirshipsCommand} to obtain the information if one airship
 * transgressed their corridor.
 *
 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
 */
public class GetValidationOfTransgressionByFlightIdCommand extends GetAirshipsCommand {

	private final static String FLIGHTID = "flightId";

	private final static String[] REQUIREDPARAMETERS = {FLIGHTID};

	/**
	 * 
	 * Class that implements the {@link CommandFactory} factory
	 *
	 * @author Daniel Gomes, Eva Gomes, Gonçalo Carvalho, Pedro Antunes
	 */
	public static class Factory implements CommandFactory {

		InMemoryAirshipDatabase airshipDatabaseWhereAirshipDatabase;

		public Factory(InMemoryAirshipDatabase airshipDatabaseWhereToSearch) {

			this.airshipDatabaseWhereAirshipDatabase = airshipDatabaseWhereToSearch;

		}

		public Command newInstance(Map<String, String> parameters) {

			return new GetValidationOfTransgressionByFlightIdCommand(airshipDatabaseWhereAirshipDatabase,
					parameters);
		}

	}

	/**
	 * Constructor
	 * 
	 * store the container parameter
	 * 
	 * @param airshipsDatabase
	 *            - airship Database target of search
	 * @param parameters
	 *            - Container with parameters needed to proceed the search.
	 */
	public GetValidationOfTransgressionByFlightIdCommand(InMemoryAirshipDatabase airshipsDatabase,
			Map<String, String> parameters) {

		super(airshipsDatabase, parameters);
	}

	/**
	 * Override of {@link AbstractCommand}
	 * 
	 * execute the main action associated to this command
	 * 
	 * @throws NoSuchElementInDatabaseException
	 * 
	 */
	@Override
	protected void internalExecute() throws CommandException, NoSuchElementInDatabaseException {

		String flightdID = parameters.get(FLIGHTID);

		if (airshipsDatabase.checkIfThisAirshipIsTransgressing(flightdID))
			result = "It's Transgressing";

		else
			result = "It's Not Transgressing";
	}

	/**
	 * Override of {@link AbstractCommand}
	 * 
	 * Method responsible to get the Obligation parameters associated to this Command
	 * 
	 */
	@Override
	protected String[] getRequiredParameters() {

		return REQUIREDPARAMETERS;
	}
}