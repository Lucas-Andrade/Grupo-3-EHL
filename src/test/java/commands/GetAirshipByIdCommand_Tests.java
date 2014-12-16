package test.java.commands;

import main.java.CommandParser;
import main.java.commands.getAirshipsCommands.GetAirshipByIdCommand;
import main.java.exceptions.commandParserExceptions.InvalidRegisterException;
import main.java.model.airships.Airship;
import main.java.model.airships.CivilAirship;
import main.java.model.airships.InMemoryAirshipDatabase;
import main.java.model.airships.MilitaryAirship;
import main.java.model.users.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GetAirshipByIdCommand_Tests {

	private CommandParser parser = new CommandParser();

	private InMemoryAirshipDatabase airshipDatabase;

	private User user1 = new User("daniel", "pass1", "daniel@gmail.com");
	private static Airship airship1 = new MilitaryAirship(10, 10, 1000, 2000, 200, false);
	private static Airship airship2 = new CivilAirship(10, 10, 3000, 2000, 200, 100);

	@Before
	public void createUserAndAirshipDatabaseAndAddElements() throws InvalidRegisterException {

		airshipDatabase = new InMemoryAirshipDatabase();

		parser.registerCommand("GET", "/airships/{flightId}", new GetAirshipByIdCommand.Factory(
				airshipDatabase));
	}

	@Test
	public void shouldSuccessfullyGetAirshipById() throws Exception {

		// Act
		airshipDatabase.add(airship1, user1);
		airshipDatabase.add(airship2, user1);

		GetAirshipByIdCommand getAirship = (GetAirshipByIdCommand) parser.getCommand("GET",
				"/airships/1");

		getAirship.execute();

		// Assert
		Assert.assertEquals(getAirship.getResult(),

		"Flight ID: 1\nLatitude: 10.0 Longitude: 10.0 Altitude: 1000.0"
				+ "\nMaximum Altitude Permited: 2000.0 Minimum Altitude Permited: 200.0"
				+ "\nIs Outside The Given Corridor: false\nCarries Weapons: false");
	}

	@Test
	public void shouldGetTheCorrectInformationIfTheAirshipDoesNotExist() throws Exception {

		// Act
		GetAirshipByIdCommand getAirship = (GetAirshipByIdCommand) parser.getCommand("GET",
				"/airships/1");

		getAirship.execute();

		// Assert
		Assert.assertEquals(getAirship.getResult(), "Airship Not Found\n");
	}
}