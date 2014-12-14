package testsAppForConsole.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import appForConsole.CommandParser;
import appForConsole.commands.getAirshipsCommands.GetReportOfTransgressionByIdCommand;
import appForConsole.exceptions.commandParserExceptions.InvalidRegisterException;
import appForConsole.model.airships.InMemoryAirshipDatabase;
import appForConsole.model.airships.MilitaryAirship;
import appForConsole.model.users.User;

/**
 * 
 * Those Tests were created to test the {@link GetReportOfTransgressionByIdCommand} Class who's a
 * Command of the Air Traffic Project
 *
 */
public class GetReportOfTransgressionByIdCommand_Tests {

	private CommandParser commandParser = new CommandParser();

	private InMemoryAirshipDatabase airshipsDatabaseWhereToSearch = new InMemoryAirshipDatabase();

	private User pantunes = new User("pantunes", "pass", "pantunes@gmail.com", "Pantunes");

	@Before
	public void BeforeTests() throws InvalidRegisterException {

		commandParser.registerCommand("GET", "/airships/reports/{flightId}",
				new GetReportOfTransgressionByIdCommand.Factory(airshipsDatabaseWhereToSearch));
	}

	@Test
	public void VerifyThatAirshipTransgressedTheAltitudeParameter() throws Exception {

		MilitaryAirship bombardeiro = new MilitaryAirship(-60, 120, 8000, 20000, 10000, true);

		airshipsDatabaseWhereToSearch.add(bombardeiro, pantunes);

		GetReportOfTransgressionByIdCommand getReport = (GetReportOfTransgressionByIdCommand) commandParser
				.getCommand("GET", "/airships/reports/1");
		getReport.execute();

		Assert.assertEquals("It's Transgressing", getReport.getResult());

	}

	@Test
	public void VerifyThatAirshipDidNotTransgresTheAltitudeParameter() throws Exception {

		MilitaryAirship f16 = new MilitaryAirship(50, 100, 10000, 15000, 5000, true);

		airshipsDatabaseWhereToSearch.add(f16, pantunes);

		GetReportOfTransgressionByIdCommand getReport = (GetReportOfTransgressionByIdCommand) commandParser
				.getCommand("GET", "/airships/reports/2");
		getReport.execute();

		Assert.assertEquals("It's Not Transgressing", getReport.getResult());

	}
}