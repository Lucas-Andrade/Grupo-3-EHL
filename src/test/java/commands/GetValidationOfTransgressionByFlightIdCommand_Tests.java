package test.java.commands;

import main.java.CommandParser;
import main.java.commands.getAirshipsCommands.GetValidationOfTransgressionByFlightIdCommand;
import main.java.exceptions.commandExecptions.CommandException;
import main.java.exceptions.commandParserExceptions.DuplicateParametersException;
import main.java.exceptions.commandParserExceptions.InvalidCommandParametersSyntaxException;
import main.java.exceptions.commandParserExceptions.InvalidRegisterException;
import main.java.exceptions.commandParserExceptions.UnknownCommandException;
import main.java.exceptions.dataBaseExceptions.NoSuchElementInDatabaseException;
import main.java.model.airships.InMemoryAirshipDatabase;
import main.java.model.airships.MilitaryAirship;
import main.java.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * Those Tests were created to test the {@link GetValidationOfTransgressionByFlightIdCommand} Class who's a
 * Command of the Air Traffic Project
 *
 */
public class GetValidationOfTransgressionByFlightIdCommand_Tests {

	private CommandParser commandParser = new CommandParser();

	private InMemoryAirshipDatabase airshipsDatabaseWhereToSearch = new InMemoryAirshipDatabase();

	private User pantunes = new User("pantunes", "pass", "pantunes@gmail.com", "Pantunes");

	@Before
	public void BeforeTests() throws InvalidRegisterException {

		commandParser.registerCommand("GET", "/airships/reports/{flightId}",
				new GetValidationOfTransgressionByFlightIdCommand.Factory(airshipsDatabaseWhereToSearch));
	}

	@Test
	public void VerifyThatAirshipTransgressedTheAltitudeParameter() {

		MilitaryAirship bombardeiro = new MilitaryAirship(-60, 120, 8000, 20000, 10000, true);

		airshipsDatabaseWhereToSearch.add(bombardeiro, pantunes);

		try {
			GetValidationOfTransgressionByFlightIdCommand  getReport = (GetValidationOfTransgressionByFlightIdCommand) commandParser
					.getCommand("GET", "/airships/reports/1");
			getReport.execute();

			Assert.assertEquals("It's Transgressing", getReport.getResult());
		} catch (UnknownCommandException | DuplicateParametersException | InvalidCommandParametersSyntaxException | NoSuchElementInDatabaseException | CommandException e) {
		
		}
		

	}

	@Test
	public void VerifyThatAirshipDidNotTransgresTheAltitudeParameter() throws Exception {

		MilitaryAirship f16 = new MilitaryAirship(50, 100, 10000, 15000, 5000, true);

		airshipsDatabaseWhereToSearch.add(f16, pantunes);
		try {
			GetValidationOfTransgressionByFlightIdCommand getReport = (GetValidationOfTransgressionByFlightIdCommand) commandParser
					.getCommand("GET", "/airships/reports/2");
			getReport.execute();

			Assert.assertEquals("It's Not Transgressing", getReport.getResult());
		} catch (UnknownCommandException | DuplicateParametersException | InvalidCommandParametersSyntaxException | NoSuchElementInDatabaseException | CommandException e) {

		}
	}
}