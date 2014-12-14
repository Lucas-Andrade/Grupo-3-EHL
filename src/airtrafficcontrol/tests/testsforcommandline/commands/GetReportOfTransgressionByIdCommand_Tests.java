package airtrafficcontrol.tests.testsforcommandline.commands;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import airtrafficcontrol.app.appforcommandline.CommandParser;
import airtrafficcontrol.app.appforcommandline.commands.getairshipscommands.GetReportOfTransgressionByIdCommand;
import airtrafficcontrol.app.appforcommandline.exceptions.commandparserexceptions.InvalidRegisterException;
import airtrafficcontrol.app.appforcommandline.model.airships.InMemoryAirshipDatabase;
import airtrafficcontrol.app.appforcommandline.model.airships.MilitaryAirship;
import airtrafficcontrol.app.appforcommandline.model.users.User;

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